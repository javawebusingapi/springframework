package com.mycompany.springwebapp.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Ch04Form2 {
	private String param21;
	private String param22;
	private String param23;
	private boolean param24;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date param25;
}
