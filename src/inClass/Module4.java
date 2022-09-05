package inClass;

public class Module4 {

	public static void main(String[] args) {

		try {
			 
			int inputVal=0;

			   if (inputVal < 0) {
			      throw new Exception("Input should be positive");
			   }


			   System.out.print("Input squared: " + inputVal * inputVal);
			}
			catch (Exception e) {
			   System.out.print(e.getMessage());
			}
	}

}
