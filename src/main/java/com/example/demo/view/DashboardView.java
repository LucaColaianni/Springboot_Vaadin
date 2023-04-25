package com.example.demo.view;

import com.example.demo.service.MembroService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard" , layout = MainLayout.class)
@PageTitle("Dashboard")
public class DashboardView extends VerticalLayout {
	private MembroService service;
	
	public DashboardView(MembroService service) {
		this.service =  service;
		addClassName("dashboard-view");
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		add(getMembriStats());
	}

	private Component getMembriStats() {
	Span stats =	new Span(service.countMembri() + " Iscritti");
	stats.addClassNames("text-xl" , "mt-m");
		return stats;
	}

}
