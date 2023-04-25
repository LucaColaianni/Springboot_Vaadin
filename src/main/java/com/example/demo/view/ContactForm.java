package com.example.demo.view;

import java.util.List;

import com.example.demo.model.Membro;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class ContactForm extends FormLayout {
	
	Binder<Membro> binder = new BeanValidationBinder<>(Membro.class);
	
	TextField nome = new TextField("Nome");
	TextField cognome = new TextField("Cognome");
	DatePicker dataIscrizione = new DatePicker("Data iscrizone");
	DatePicker dataRinnovo = new DatePicker("Data rinnovo");
	
	Button save = new Button("Salva");
	Button delete = new Button("Elimina");
	Button cancel = new Button("Cancella");

	private Membro membro ;
	
	public ContactForm(List<Membro> membri) {
		addClassName("contact-form");
		binder.bindInstanceFields(this);
		
		add(
			nome,
			cognome,
			dataIscrizione,
			dataRinnovo,
			createButtonLayout()
			);
	}

	public void setMembro(Membro membro) {
		binder.setBean(membro);
		dataRinnovo.setEnabled(false);
	}

	private Component createButtonLayout() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
		cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		
		save.addClickListener(event -> validateAndSave());
		delete.addClickListener(event -> fireEvent(new DeleteEvent(this,binder.getBean())));
		cancel.addClickListener(event -> fireEvent(new CloseEvent(this))); 

		
		save.addClickShortcut(Key.ENTER);
		cancel.addClickShortcut(Key.ESCAPE);

	    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid())); // <4>
		return new HorizontalLayout(save,delete,cancel);
	}
	
	  private void validateAndSave() {
		    if(binder.isValid()) {
		      fireEvent(new SaveEvent(this, binder.getBean())); // <6>
		    }
		  }
	  
	// Events
	  public static abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
	    private Membro membro;

	    protected ContactFormEvent(ContactForm source, Membro membro) {
	      super(source, false);
	      this.membro = membro;
	    }

	    public Membro getMembro() {
	      return membro;
	    }
	  }

	  public static class SaveEvent extends ContactFormEvent {
		    SaveEvent(ContactForm source, Membro membro) {
		      super(source, membro);
		    }
		  }

	  public static class DeleteEvent extends ContactFormEvent {
	    DeleteEvent(ContactForm source, Membro membro) {
	      super(source, membro);
	    }

	  }

	  public static class CloseEvent extends ContactFormEvent {
	    CloseEvent(ContactForm source) {
	      super(source, null);
	    }
	  }

	  public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
	    return addListener(DeleteEvent.class, listener);
	  }

	  public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
	    return addListener(SaveEvent.class, listener);
	  }
	  public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
	    return addListener(CloseEvent.class, listener);
	  }


	}