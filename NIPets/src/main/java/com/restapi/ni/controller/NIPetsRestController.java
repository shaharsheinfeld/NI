package com.restapi.ni.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.restapi.ni.model.Animal;
import com.restapi.ni.service.AnimalService;

@RestController
public class NIPetsRestController {

	@Autowired
	AnimalService animalService; 
	
	@RequestMapping(value = "/animal/", method = RequestMethod.GET)
	public ResponseEntity<List<Animal>> GetAllAnimals() {
		List<Animal> animals = animalService.GetAllAnimals();
		if(animals.isEmpty()){
			return new ResponseEntity<List<Animal>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Animal>>(animals, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/animal/{name}/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Animal> GetAnimal(@PathVariable("name") String name, @PathVariable("type") String type) {
		System.out.println("Get " + type + " by name " + name);
		Animal animal = animalService.GetAnimal(name, type);
		if (animal == null) {
			System.out.println(type + " named " + name + " does not exists.");
			return new ResponseEntity<Animal>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Animal>(animal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/animal/", method = RequestMethod.POST)
	public ResponseEntity<Void> createAnimal(@RequestBody Animal animal, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Animal " + animal.getName());

		if (animalService.isAnimalExist(animal)) {
			System.out.println(animal.getType() + " named " + animal.getName() + " already exists.");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		else {
			animalService.CreateAnimal(animal);
		}
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}/{type}").buildAndExpand(animal.getName(), animal.getType()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/animal/{name}/{type}", method = RequestMethod.PUT)
	public ResponseEntity<Animal> updateAnimal(@PathVariable("name") String name, @PathVariable("type") String type, @RequestBody Animal animal) {
		System.out.println("Updating " + type + " named " + name);
		
		Animal currentAnimal = animalService.GetAnimal(name, type);
		if (currentAnimal==null) {
			System.out.println(type + " named " + name + " does not exists.");
			return new ResponseEntity<Animal>(HttpStatus.NOT_FOUND);
		}

		currentAnimal.setName(animal.getName());
		currentAnimal.setAge(animal.getAge());
		currentAnimal.setType(animal.getType());
		currentAnimal.setBreed(animal.getBreed());
		currentAnimal.setOwnerName(animal.getOwnerName());
		currentAnimal.setPhotoLink(animal.getPhotoLink());
		
		animalService.UpdateAnimal(currentAnimal);
		return new ResponseEntity<Animal>(currentAnimal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/animal/{name}/{type}", method = RequestMethod.DELETE)
	public ResponseEntity<Animal> deleteAnimal(@PathVariable("name") String name, @PathVariable("type") String type) {
		System.out.println("Deleting " + type + " named " + name);

		Animal animal = animalService.GetAnimal(name, type);
		if (animal == null) {
			System.out.println("Unable to delete. " + type + " named " + name + " does not exists.");
			return new ResponseEntity<Animal>(HttpStatus.NOT_FOUND);
		}

		animalService.DeleteAnimal(name, type);
		return new ResponseEntity<Animal>(HttpStatus.NO_CONTENT);
	}

}
