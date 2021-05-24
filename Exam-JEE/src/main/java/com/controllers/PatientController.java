package com.controllers;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.Patient;
import com.repositories.PatientRepository;

import javassist.NotFoundException;
@Controller
public class PatientController {
	
	@SuppressWarnings("unused")
	private final PatientRepository patientrepository;
    
    @Autowired
    public  PatientController(PatientRepository patientrepository) {
        this.patientrepository = patientrepository;
    }
    @GetMapping("/ajouterPatient")
    public String AjouterPatient(Patient patient)
    {
		return "AjouterPatient"; 	
    }
    @PostMapping("/ajouterPatient")
    public String addPatient(@Valid Patient patient, BindingResult result, Model model) {
    	if (result.hasErrors()) {
            return "AjouterPatient";
        } 
        patientrepository.save(patient);
        return "redirect:/patients";
    }
    @GetMapping("/patients")
    public String getPatients(Model model, @RequestParam(name="page",defaultValue ="0")int page,
            @RequestParam(name="size",defaultValue ="3")int size,
            @RequestParam(name="keyword",defaultValue ="")String kw) {
    	Page<Patient> pagepatients =  patientrepository.findByNomContains(kw,PageRequest.of(page,size));
        model.addAttribute("patients",pagepatients.getContent());
        model.addAttribute("previous",pagepatients.hasPrevious());
        model.addAttribute("next",pagepatients.hasNext());
        model.addAttribute("number",pagepatients.getNumber());
        model.addAttribute("totalPages",pagepatients.getTotalPages());
        model.addAttribute("currentpage",page);
        model.addAttribute("keyword",kw);
        model.addAttribute("size",size);
        return "home";
    }
    @GetMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable("id") long id, Model model) {
        Patient patient = patientrepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
        patientrepository.delete(patient);
        return "redirect:/patients";
    }
    @GetMapping("/edit/{id}")
    public String editpatient(Model model , @PathVariable("id") Long id) {
    	try {
    	Optional<Patient> patient = patientrepository.findById(id);
		Patient P = new Patient();
		if (patient.isPresent()) {
			P = patient.get();
			model.addAttribute("patient",P);
			return "editpage";
		}
		else 
		{
			throw new NotFoundException("not found");
		}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
    	return "editpage";
    }
    @PostMapping("/updatePatient/{id}")
    public String updatePatient(@PathVariable("id") long id, @Valid Patient patient, BindingResult result, Model model) {
    	System.out.println(patient.getMalade());
    	System.out.println(patient.getDate_naissance());
    	if (result.hasErrors()) {
        	patient.setId(id);
            return "editpage";
        }
        patientrepository.save(patient);
        return "redirect:/patients";
    }
}
