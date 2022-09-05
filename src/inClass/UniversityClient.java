package inClass;

public class UniversityClient{
	
	public static void main(String [] args){
		/* Task: create a faculty object using its default constructor*/
		Faculty fact1 = new Faculty();
		
		/*Task: print out the department of faculty object (private attribute in Faculty)*/
		System.out.println("Faculty department: " + fact1.getAcademicDept());

		
		/* Task: Faculty inherits public attributes and methods of it superclass*/
        /* print out a public attribute of CommunityMember*/		
		System.out.println("Faculty name: " + fact1.getName());
		
		
		/*Task: Create a non-default Constructor for Faculty which takes in the dept name and an empty arry containing classes he is teaching*/
		/*Task: Create a Faculty object using the non-default Constructor*/
		Faculty fact2 = new Faculty("CS", new String[10]);
	
		
		/*Task: what is the ID of this object*/
		System.out.println("Faculty ID: " + fact2.getID());
		
		/*Task: Add another non-default constructor for the Faculty class to take name, age and salary. Force it to use the non-default constructor of community member*/

		
		/*Task: create a faculty object using this non-default constructor*/
		Faculty fact3 = new Faculty("John", 40, 20000f);
		/*Task: Print out the name attribute (private attribute in CommunityMember) of this object*/
		System.out.println("Name: " + fact3.getName());
		
		/*Task: make ID protected in CommunityMember. Can you directly access and print out the value of ID from the client class (which is in a different package)?*/
		
		//Task: Now Faculty member's ID has to start with a letter "F", change the Faculty class constructor appropriately
		System.out.println("ID: " + fact3.ID);
		/*Task: Create an accessor method for ID in CommunityMember and print it out*/
		
		/*Task: Create a toString() method for Faculty class to print out its attributes*/

		/*Task: Comment out the default contructor in CommunityMember. Does it work? Now modify the Faculty class to make it work*/
		
		
	}
}