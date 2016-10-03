package com.restapi.ni.service;

import java.util.List;

import com.restapi.ni.model.Animal;

public interface AnimalServiceInterface {
	
	Animal GetAnimal(String name, String animalType);
	
	void CreateAnimal(Animal animal);
	
	void UpdateAnimal(Animal animal);
	void DeleteAnimal(String name, String animalType);

	List<Animal> GetAllAnimals(); 
	
}
