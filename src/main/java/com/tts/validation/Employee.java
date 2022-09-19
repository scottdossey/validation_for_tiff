package com.tts.validation;

import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

public class Employee {
	//Formatting validation Checks are annotations that you can sprinkle on 
	//model-like objects.... 

	//The Validation api can be checked at many different places....
	
	
	//One place it is checked automatically is validations are always checked 
	//when saving an object via JPA.... an exception will be thrown
	//and the data will not be saved if the employee is not valie...
	
	
	//But you can choose to Validate selectively on controller calls.....
	//Which is another spot.....
	
	
	//FORMATTING VALIDATION CONSTRAINTS COME FROM ONE OF TWO PACKAGES:
	//javax.validation.constraints
	//org.hibernate.validatorconstraints
	
	@NotEmpty
	@NotNull
	private String username;
	
	@NotNull	
	@Size(min=3, max=30)
	private String firstname;
	
	private String lastname;
	
	//
	@AssertFalse
	private boolean value;
	
	@Email
	String email;
	
	@Min(3) //This is a minimum value for the number not a minimum length.
	@Max(30)
	int myNumber;

	@Size(min=8, max=30)
	String password;
	
	@Pattern(regexp="[0-9]{12:12}")
	String employeeId;
	

	@CreditCardNumber	
	String creditCardNumber;
	
	@Past
	private Date createdAt;
	
	@Future
	private Date expirationDate;
	
}
