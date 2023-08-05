package com.marketingaap6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingaap6.entity.Lead;
import com.marketingaap6.service.LeadService;
import com.marketingaap6.util.EmailService;

@Controller
public class LeadController {
	
	@Autowired
	private LeadService leadservice;
	
	@Autowired
	private EmailService emailservice;
	
	//http://localhost:8080/view
	@RequestMapping("/view")
	public String viewLeadPage() {
		return "create_lead";  //page name
		
		// request dispatcher
	}
	//http://localhost:8080/saveLead
	@RequestMapping("/saveLead")
	public String saveOneLead(Lead lead, Model model) {
		leadservice.saveLead(lead);
		
		emailservice.sendEmail(lead.getEmail(), "welcome", "monika");
		model.addAttribute("msg", "record is saved!");
		return "create_lead";
	}
	//http://localhost:8080/listall
	@RequestMapping("/listall")
	public String getAllLeads(Model model) {
		List<Lead>leads=leadservice.getLeads();
		model.addAttribute("leads", leads);
		return "list_leads"; 
		
	}
	@RequestMapping("/delete")
	public String deleteLeadById(@RequestParam("id") long id, ModelMap model) {
		leadservice.deleteLead(id);
		
		List<Lead>leads=leadservice.getLeads();
		model.addAttribute("leads", leads);
		return "list_leads"; 
	}
	
	@RequestMapping("/update")
	public String getLeadById(@RequestParam("id") long id, Model model) {
		Lead lead=leadservice.getLeadById(id);
		model.addAttribute("lead", lead);
		
		return "update_lead";
	}
	@RequestMapping("/updateLead")
	public String updateOneLead(Lead lead, Model model) {
		leadservice.saveLead(lead);
		
		List<Lead>leads=leadservice.getLeads();
		model.addAttribute("leads", leads);
		return "list_leads"; 
		
	}
	
	
	
	
}
