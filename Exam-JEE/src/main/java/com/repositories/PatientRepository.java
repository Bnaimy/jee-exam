package com.repositories;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.entities.Patient;
public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {
	
	 List<Patient>  findByMalade(boolean maladie);
	 public Page<Patient> findByNomContains(String kw ,Pageable pageable);
}
