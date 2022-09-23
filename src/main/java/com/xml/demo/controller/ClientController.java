package com.xml.demo.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xml.demo.repository.Db;
import com.xml.model.SystemModel;

@RestController
@RequestMapping(value = "/api")
public class ClientController {

	@GetMapping("/getbyid/{sysId}/{domainId}/{productId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getById(@PathVariable String sysId, @PathVariable String domainId, @PathVariable String productId) {
		Db connection = new Db();
		SystemModel query = connection.getById(sysId, domainId, productId);
		System.out.println(query);
		return ResponseEntity.ok(query);
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public SystemModel save(@RequestBody SystemModel data) {
		Db connection = new Db();
		boolean query = connection.save(data);
		
		if (query) {
			return data;
		}
		
		return data;
	}
	
	@GetMapping("/list/{domainId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> list(@PathVariable String domainId) {
		Db connection = new Db();
		ArrayList<SystemModel> query = connection.list(domainId);
		System.out.println(query);
		return ResponseEntity.ok(query);
	}
	

}
