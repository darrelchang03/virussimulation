package inClass;
public class CommunityMember{
   private static int counter=10000;//static variable
   protected String ID;
   private String name;
   private int age;   
   private float salary;
   
   public CommunityMember(){//default constructor
	   this.name="no-name";	   
	   this.ID=""+this.counter;
	   this.counter++;
   } 
  public CommunityMember(String myName, int myAge, float mySalary) { //non-default contructor
	  this.setName(myName);
	  this.setAge(myAge);
	  this.setSalary(mySalary);
	  this.setID("" +this.counter);
	  ++this.counter;
	  
  }
   //accessor for name
   public String getName(){
	   return this.name;
   }
   //accessor for age
   public int getAge(){
	   return this.age;
   }
   //accessor for salary
   public float getSalary(){
	   return this.salary;	   
   }
   //accessor for ID
   public String getID(){
	   return this.ID;
   }
   //mutator for name
   public void setName(String name){
	   this.name=name;
   }
   //mutator for age
   public void setAge(int age){
	   this.age=age;
   }
   //mutator for salary
   public void setSalary(float salary){
	   this.salary=salary;
   }
   //mutator for ID
   public void setID(String ID) {
	   this.ID = ID;
   }
   public String toString(){
	   String retString;
	   retString="==============================\n";
	   retString=retString + "Name: " + this.getName() +"\n";
	   retString=retString + "Name: " + this.ID +"\n";
	   retString=retString + "==============================";
	   return retString;
   }   
}