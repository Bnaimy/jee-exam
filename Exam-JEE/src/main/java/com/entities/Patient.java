package com.entities;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

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
	@NotNull
	@Size(min=5, max=10)
	private String nom ;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_naissance;
	@NotNull
	@Min(3)
	private int score;
	@NotNull
	private boolean malade ;

	public boolean getMalade() {
		return malade ;
	}
}
