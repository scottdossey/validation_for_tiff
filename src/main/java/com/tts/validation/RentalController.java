package com.tts.validation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//The RestController annotation works almost exactly the same as the @Controller
//annotation except it effectively adds "@ResponseBody" annotation to each of the controller methods
@RestController
public class RentalController {
	@Autowired
	RentalRepository repository;
	
	@PostMapping("/rentals")
	public ResponseEntity<Void> createRental(@RequestBody @Valid Rental rental,
			                                 BindingResult bindingResult) {
		
		//ResponseEntity is a container class that is used for holding a web response...
		//A web response has three chief things in it:
		//1. The data/page/JSON that you intend to return.
		//2. The HTTP Error code (200 ok, 404....)
		//3. Http response headers -- (any headers you went to send in the response)
		
		//ResponseEntity is written as a Generic that takes one Generic Parameter...
		//That generic parameter is used to specify what type of item we are returning
		// for #1.
		//If you do not wish to send an item back--but just send an error code and headers...
		//you can use Void (capital V void)
		if (repository.findByEmailAddress(rental.getEmailAddress()) != null) {
			bindingResult.rejectValue("emailAddress", "error.email", "Email address is already taken");
		}
		
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		repository.save(rental);
		return new ResponseEntity<>(HttpStatus.CREATED);		
	}
}
