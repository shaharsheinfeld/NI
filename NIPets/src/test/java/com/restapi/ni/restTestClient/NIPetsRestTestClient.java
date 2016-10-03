package com.restapi.ni.restTestClient;
 
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
 
import org.springframework.web.client.RestTemplate;
 
import com.restapi.ni.model.Animal;


public class NIPetsRestTestClient {
	
	public static final String restApiBaseUri = "http://localhost:8113/NIPets/";
    
    /* GET */
    @SuppressWarnings("unchecked")
    private static void GetAllAnimals(){
        System.out.println("Testing Get All Animals API");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> animals = restTemplate.getForObject(restApiBaseUri+"/animal/", List.class);
         
        if(animals!=null){
            for(LinkedHashMap<String, Object> map : animals){
                System.out.println("Animal : type="+map.get("type")+", name="+map.get("name")+", breed="+map.get("breed")+", age="+map.get("age") +", ownerName="+map.get("ownerName")+", photoLink="+map.get("photoLink"));
            }
        }else{
            System.out.println("No animal exist");
        }
        System.out.println("**************************************************************");
    }
     
    /* GET */
    private static void getAnimal(){
        System.out.println("Testing get Animal API: Name=Mika, Type=Dog");
        RestTemplate restTemplate = new RestTemplate();
        Animal animal = restTemplate.getForObject(restApiBaseUri+"/animal/Mika/Dog", Animal.class);
        if(animal!=null){
        	System.out.println("Animal found: type="+ animal.getType() +", name="+ animal.getName() +", breed="+animal.getBreed()+", age=" + animal.getAge() +", ownerName="+animal.getOwnerName()+", photoLink=" + animal.getPhotoLink());
        }else{
            System.out.println("No animal exist");
        }
        System.out.println("**************************************************************");
    }
     
    /* POST */
    private static void createAnimal(String type, String name, String breed, int age, String OwnerName, String photoLink) {
        System.out.println("Testing create Animal API");
        RestTemplate restTemplate = new RestTemplate();
        Animal animal = new Animal(type, name, breed, age, OwnerName, photoLink);
        URI uri = restTemplate.postForLocation(restApiBaseUri+"/animal/", animal, Animal.class);
        System.out.println("Location : "+uri.toASCIIString());
        System.out.println("**************************************************************");
    }
 
    /* PUT */
    private static void updateAnimal(String type, String name, String breed, int age, String ownerName, String photoLink) {
        System.out.println("Testing update Animal API");
        RestTemplate restTemplate = new RestTemplate();
        Animal animal = new Animal(type, name, breed, age, ownerName, photoLink);
        System.out.println("Updating animal: type="+ type +", name="+ name +", breed="+breed+", age=" + age +", ownerName="+ ownerName+", photoLink=" + photoLink);
        restTemplate.put(restApiBaseUri+"/animal/" +name+ "/" +type , animal);
        System.out.println("**************************************************************");
    }
 
    /* DELETE */
    private static void deleteAnimal(String name, String type) {
        System.out.println("Testing delete Animal API: name="+name+", type=" + type);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(restApiBaseUri+"/animal/"+name+"/"+type);
        System.out.println("**************************************************************");
    }

 
    public static void main(String args[]){
    	
        createAnimal("Dog", "Joey", "Labrador", 9, "Bruno", "https://upload.wikimedia.org/wikipedia/commons/2/26/YellowLabradorLooking_new.jpg");
        createAnimal("Dog", "Snitch", "Toy Terrier", 4, "Zach", "http://petsfans.com/wp-content/uploads/2014/11/sd-1024x576.jpg");
        createAnimal("Goat", "Izza", "Boer", 5, "Mosh", "https://upload.wikimedia.org/wikipedia/commons/9/90/Boerbok.jpg");
        GetAllAnimals();
        
        updateAnimal("Dog", "Snitch", "Toy Terrier", 8, "Zach", "http://petsfans.com/wp-content/uploads/2014/11/sd-1024x576.jpg");
        GetAllAnimals();
        
        updateAnimal("Goat", "Izza", "Boer", 5, "Mushon", "https://upload.wikimedia.org/wikipedia/commons/9/90/Boerbok.jpg");
        GetAllAnimals();
        
        deleteAnimal("Joey", "Dog");
        GetAllAnimals();
    }

}
