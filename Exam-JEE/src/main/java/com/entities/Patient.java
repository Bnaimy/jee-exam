package com.entities;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Patient")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Patient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id ;
	private String nom ;
	private Date date_naissance;
	private int score;
	private boolean malade ;

	public boolean getMalade() {
		return malade ;
	}
}
