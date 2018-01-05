package com.oc.master.controller;

import com.oc.master.model.observer.Observee;
import com.oc.model.Model;

/**
 * Controller class for routing purposes
 * @author bob
 *
 */
public class Controller {
	private Model model;
	
	public Controller(Observee mod) {
		
		this.model = (Model)mod;
	}
	
}
