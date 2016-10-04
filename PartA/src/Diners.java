public class Diners {
	static String plate = "plate";  
	static String soy = "soy"; 		

	public static void Daniel() { 
		synchronized(plate) {
			System.out.println("Daniel took plate, waiting for soy...");
			synchronized(soy) { 
				System.out.println("Daniel took soy");					
			}
		}
		System.out.println("*************************************");
	}

	public static void Nir() {		
		synchronized(soy) {   //to solve deadlock: synchronized(plate) {
			System.out.println("Nir took soy, waiting for plate...");
			synchronized(plate) { //to solve deadlock: synchronized(soy) {
				System.out.println("Nir took plate");
			}
		}
		System.out.println("*************************************");
	}
}
