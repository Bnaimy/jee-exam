package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.entities.Patient;
import com.repositories.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientrepository;
 
    public void save(final Patient patient) {
        patientrepository.save(patient);
    }
    
    @SuppressWarnings("rawtypes")
	public Page getPaginatedPatients(final int pageNumber, final int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return patientrepository.findAll(pageable);
    }

}
