package com.example.demo.view;

import java.util.Collections;

import com.example.demo.model.Membro;
import com.example.demo.service.MembroService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Scadenze Academy")
@Route(value = "" , layout = MainLayout.class)
public class ListView extends HorizontalLayout { 
	Grid<Membro> grid = new Grid<>(Membro.class);
	TextField filterText = new TextField();
	ContactForm form;
	MembroService service;
	
    public ListView(MembroService service) {
    	this.service = service;
    	addClassName("list-view");
    	setSizeFull();
    	confgureGrid();
    	configureForm();    
    	add(
    		getToolBar(),
    		getContent()
    	);
    	
    	updateList();
    	closeEditor();
    }
    
    private void closeEditor() {
		 form.setMembro(null);
		 form.setVisible(false);
		 removeClassName("editing");
		 
	}

	private void updateList() {
    	grid.setItems(service.findAllMembri(filterText.getValue()));
	}

	private Component getContent() {
    	HorizontalLayout content = new HorizontalLayout(grid, form);
    	content.setFlexGrow(2, grid);
    	content.setFlexGrow(1, form);
    	content.addClassName("content");
    	content.setSizeFull();
    	return content;
    }

	private void configureForm() {
		form = new ContactForm(service.findAllMembri(""));
		form.setWidth("25em"); 
		 form.addSaveListener(this::saveMembro); // <1>
	        form.addDeleteListener(this::deleteContact); // <2>
	        form.addCloseListener(e -> closeEditor()); // <3>
	}
	
	  private void saveMembro(ContactForm.SaveEvent event) {
	        service.saveMembro(event.getMembro());
	        updateList();
	        closeEditor();
	    }

	    private void deleteContact(ContactForm.DeleteEvent event) {
	        service.deleteContact(event.getMembro());
	        updateList();
	        closeEditor();
	    }

	private Component getToolBar() {
		filterText.setPlaceholder("Filtra");
		filterText.setClearButtonVisible(true);
		filterText.setValueChangeMode(ValueChangeMode.LAZY);
		filterText.addValueChangeListener(e -> updateList());
		
		Button addContactButton = new Button("Aggiungi iscritto 👨🏻‍⚕️");
		addContactButton.addClickListener(click -> addMembro());
		
		 var toolbar = new HorizontalLayout(filterText, addContactButton);
	        toolbar.addClassName("toolbar");
	        return toolbar;
	}

	private void addMembro() {
		grid.asSingleSelect().clear();
		editMembro(new Membro());
	}

	private void confgureGrid() {
		 grid.addClassName("contact-grid");
		 grid.setSizeFull();
		 grid.setColumns("nome", "cognome","dataIscrizione");
		 grid.addColumn(membro -> {
			    if (membro.getDataIscrizione() != null) {
			        return membro.getDataIscrizione().plusDays(28);
			    } else {
			        return "";
			    }
			}).setHeader("Data Rinnovo");
		 grid.asSingleSelect().addValueChangeListener(e -> editMembro(e.getValue()));
		}

	 public void editMembro(Membro membro) {
	        if (membro == null) {
	            closeEditor();
	        } else {
	            form.setMembro(membro);
	            form.setVisible(true);
	            addClassName("editing");
	        }
	    }

	}