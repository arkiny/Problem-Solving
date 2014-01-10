package graduate;

public class Course {

	public String courseName;
	public int prereqNum;
	public String[] pre_course;
	public String offerSemester;
	
	public Course(String InputCourse, String offerSem, int Num, String[] preco){
		courseName=InputCourse;
		offerSemester=offerSem;
		prereqNum=Num;
		pre_course=preco;
	}
}
