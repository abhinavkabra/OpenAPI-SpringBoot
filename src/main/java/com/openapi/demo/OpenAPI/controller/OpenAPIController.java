package com.openapi.demo.OpenAPI.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/")
public class OpenAPIController {

	List<Person> persons = new ArrayList<>();

	@GetMapping("/hello/{name}")
	public String openAPI(@PathVariable String name) {
		return "hello " + name;
	}

	@PostMapping("person")
	@ResponseBody
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Person created", content = { @
				Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))}),
		@ApiResponse(responseCode = "404", description = "Bad request", content = @Content) })
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		persons.add(person);
		return new ResponseEntity<>(person, HttpStatus.CREATED);
	}


	@Operation(summary = "Get all persons list")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Person Returns", content = { 
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class)))})})
	@GetMapping("person")
	public ResponseEntity<List<Person>> person() {
		return ResponseEntity.ok().body(persons);
	}

}
