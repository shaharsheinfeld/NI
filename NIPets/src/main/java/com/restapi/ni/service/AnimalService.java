package com.restapi.ni.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restapi.ni.model.Animal;

@Service("animalService")
@Transactional
public class AnimalService implements AnimalServiceInterface {

	// private static final AtomicLong counter = new AtomicLong();

	private static List<Animal> animals;

	static {
		animals = populateDummyUsers();
	}

	public Animal GetAnimal(String name, String type) {
		for (Animal animal : animals) {
			if (animal.getName().equalsIgnoreCase(name) && animal.getType().equalsIgnoreCase(type)) {
				return animal;
			}
		}
		return null;
	}

	public void CreateAnimal(Animal animal) {
		animals.add(animal);
	}

	public void UpdateAnimal(Animal animal) {
		int index = animals.indexOf(animal);
		animals.set(index, animal);
	}

	public void DeleteAnimal(String name, String type) {
		for (Iterator<Animal> iterator = animals.iterator(); iterator.hasNext();) {
			Animal animal = iterator.next();
			if (animal.getName().equalsIgnoreCase(name) && animal.getType().equalsIgnoreCase(type)) {
				iterator.remove();
			}
		}
	}

	public List<Animal> GetAllAnimals() {
		return animals;
	} 

	private static List<Animal> populateDummyUsers() {
		List<Animal> animals = new ArrayList<Animal>();
		/*animals.add(new Animal("Dog", "Mika", "Lab", 9, "Shahar", ""));
		animals.add(new Animal("Dog", "Lola", "Cane Corso", 1, "Liat", "https://www.google.co.il/url?sa=i&rct=j&q=&esrc=s&source=imgres&cd=&cad=rja&uact=8&ved=0ahUKEwib9_6DtL7PAhVCrRQKHbRWC6AQjRwIBw&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FCane_Corso&psig=AFQjCNFKvr6MIZwZ8QJv1YarzPPIHdZAzA&ust=1475576251300707"));
		animals.add(new Animal("Cat", "Murky", "Persian", 4, "Hila", "https://www.google.co.il/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwim-8uPtL7PAhVDVhQKHSPLB6MQjRwIBw&url=http%3A%2F%2Fwww.tapuz.co.il%2Fforums%2Fviewmsg%2F1222%2F154135677%2F%25D7%2591%25D7%25A2%25D7%259C%25D7%2599_%25D7%2597%25D7%2599%25D7%2599%25D7%259D%2F%25D7%2590%25D7%2599%25D7%259E%25D7%2595%25D7%25A5_%25D7%2591%25D7%25A2%25D7%2597_%25D7%2595%25D7%2594%25D7%25AA%25D7%25A0%25D7%2593%25D7%2591%25D7%2595%25D7%25AA&psig=AFQjCNEjTHreWYoaCS0QaXrpE4iYNU2Uvg&ust=1475576273254726"));
		animals.add(new Animal("Goat", "Asher", "Goaty", 5, "David", ""));*/
		return animals;
	}

	public void deleteAllUsers() {
		animals.clear();
	}

	public boolean isAnimalExist(Animal animal) {
		return GetAnimal(animal.getName(), animal.getType()) != null;
	}

}
