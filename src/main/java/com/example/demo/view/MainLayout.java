package com.example.demo.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightCondition;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
	
	public MainLayout() {
		createHeader();
		createDrawer();
	}


	private void createHeader() {
		H1 logo = new H1("Dr. Forex 📈");
		logo.addClassNames("text-l", "m-m");
		
		HorizontalLayout header = new HorizontalLayout(new DrawerToggle(),logo); 
		
		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.expand(logo);
		header.setWidthFull();
		header.addClassNames("py-0, px-m");
		
		addToNavbar(header);

	}
	
	   private void createDrawer() {
	        addToDrawer(new VerticalLayout(
	                new RouterLink("List", ListView.class),
	                new RouterLink("Dashboard", DashboardView.class)
	        ));
	    }
	}
