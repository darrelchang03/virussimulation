package inClass;


public class Faculty extends CommunityMember{
    private String academicDept;
	private String[] courses;
	
	//default constructor
	public Faculty(){
	   
	   this.academicDept="academic";
	   this.courses=null;//empty list of courses
	   
    }
	public Faculty(String myDept, String[] myCourses) {
		this.academicDept = myDept;
		if(myCourses==null)
			this.courses=null;
		else {
		this.courses = new String[myCourses.length];
		for(int i=0; i<myCourses.length; i++) {
			this.courses[i] = myCourses[i];
			}
		}
	}
	public Faculty(String myName, int myAge, float mySalary) {
		super(myName, myAge, mySalary);
		this.academicDept="academic";
		this.courses=null;//empty list of courses
	}
	//accessor methods
	public String getAcademicDept(){
		return this.academicDept;
	}
	
	public String[] getCourses(){
		String []classes= new String[this.courses.length];
		for(int i=0;i<this.courses.length;++i)
			classes[i]=this.courses[i];
		return classes;
	}
	
	//mutator methods
	
	public void setAcademicDept(String dept){
		this.academicDept=dept;
	}
	public void setCourses(String[] classes){
		this.courses= new String[classes.length];
		for(int i=0;i<this.courses.length;++i)
			this.courses[i] = classes[i];
	}
	
	//other methods 
	public String listCourses(){
		String retString="";
		String[] courses = this.getCourses();
		if(courses==null){
			retString="No courses taught\n";
			
		}else if(courses.length==0){
			retString="No courses taught\n";
		}else{			
			for(int i=0;i<courses.length;++i){
				retString = retString + courses[i] + "\n";
			}
		}
		
		return retString;
	}
	public String toString(){
		String retString="";
		retString=retString + super.toString()+"\n";
		retString=retString + this.getAcademicDept() + "\n";
		retString=retString + this.listCourses() + "\n";
		
		return retString;
	}
}