public class DeadLock {
	
	 public static void main(String[] args) {
		 
     	Thread danielThread = new Thread() {
     		public void run() {
	    		Diners.Daniel();
	    	}
		};
		
		Thread nirThread = new Thread() {
     		public void run() {
	    		Diners.Nir();
	    	}
		};
		
		int index = 1; 
		try {
			while(index <= 10000){				
				danielThread.start();
				nirThread.start(); 
				System.out.println("index " + index);
				danielThread.join();
				nirThread.join();				
				index++;	
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
	    
    }
}
