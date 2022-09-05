package inClass;

public class BaggageWeight {

		   static void baggageWeight(int weight) {  
		      boolean value = false;


		      try {
		         while (!value) {
		            if (weight > 30) {
		               throw new Exception("Double Max Weight");
		            }
		            if (weight > 15) {
		               throw new Exception("Excess Weight");
		            }            
		            value = true;
		            System.out.println("Accepted");
		         }
		      }


		      catch (Exception excpt) {
		         System.out.println(excpt.getMessage());
		      }
		   }


		   public static void main(String[] args) {
		      baggageWeight(42);
		   }
		}

