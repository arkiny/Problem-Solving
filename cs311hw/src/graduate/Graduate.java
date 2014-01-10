package graduate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Graduate {

	public int numOfDataSet;
	public int maxNumofCourse;
	public Course[] courseData;
	public String[] courseList, dummyCourseList;

	public Graduate(Scanner inFile) {

		while (inFile.hasNext()) {

			numOfDataSet = inFile.nextInt();
			maxNumofCourse = inFile.nextInt();

			// Out of Limit when the input number range is not
			// 1 <= numOfDataSet <= 12
			//
			if (numOfDataSet == -1 && maxNumofCourse == -1) {
				break;
			}

			else if (numOfDataSet < 1 || numOfDataSet > 12
					|| maxNumofCourse < 2 || maxNumofCourse > 6) {
			}

			else {
				dummyCourseList = new String[numOfDataSet];
				courseList = new String[numOfDataSet];
				courseData = new Course[numOfDataSet];

				for (int i = 0; i < numOfDataSet; i++) {
					dummyCourseList[i] = inFile.next();
				}

				for (int i = 0; i < numOfDataSet; i++) {
					String name = inFile.next();
					courseList[i] = name;
					String offer_sem = inFile.next();
					int num_precourse = inFile.nextInt();
					String[] pre_courses = new String[num_precourse];
					for (int j = 0; j < num_precourse; j++) {
						pre_courses[j] = inFile.next();
					}
					courseData[i] = new Course(name, offer_sem, num_precourse,
							pre_courses);
				}
			}

			Permutation(courseList);
			
			
		}

		// numOfDataSet = inFile.nextInt();
		// maxNumofCourse = inFile.nextInt();
		//
		// if (numOfDataSet==-1 && maxNumofCourse==-1){
		// endfile=true;
		// return;
		// }
		//
		// String courses = inFile.nextLine();
		// Scanner CourseArray = new Scanner(courses);
		// courseList = new String[numOfDataSet];
		//
		// int counter=0;
		// while(CourseArray.hasNext()){
		// courseList[counter]=CourseArray.next();
		// }
		//
		// courseData=new Course[numOfDataSet];
		// int courseNum = 0;
		//
		// while(inFile.hasNextLine()){
		// if (inFile.hasNextInt()){
		// break;
		// }
		// else{
		// String courseName= inFile.next();
		// String semster = inFile.next();
		//
		// int prenum = inFile.nextInt();
		//
		// String[] adder = new String[prenum];
		//
		// int index=0;
		//
		// while(inFile.hasNext()){
		// adder[index]=inFile.next();
		// index++;
		// }
		//
		// courseData[courseNum] = new Course(courseName, semster, prenum,
		// adder);
		// }
		// }
	}

	public void Permutation(String[] s) {
		int[] a = new int[s.length];
		int min;
		min = backtrack(a, 0, s);
		System.out.println("The minimum number of semesters required to graduate is "+min+".");
		
	}

	public int backtrack(int[] a, int k, String[] s) {
		int minSem = Integer.MAX_VALUE;
		if (is_a_solution(a, k, s)) {
			int ret;
			ret = process_solution(a, k, s);   //@TODO
			
			if (ret < minSem){
				minSem = ret;
			}
			
			return minSem;
		}

		int[] candidates = construct_candidates(a, k, s);

		for (int i = 0; i < candidates.length; i++) {
			a[k] = candidates[i];
			int[] tmp = Arrays.copyOf(a, s.length);
			int ret = backtrack(a, k + 1, s);
			if (ret < minSem){
				minSem = ret;
			}
			a = Arrays.copyOf(tmp, s.length);
		}
		return minSem;
	}

	public int[] construct_candidates(int[] a, int k, String[] s) {
		int n = s.length;
		boolean[] in_perm = new boolean[n];

		for (int i = 0; i < k; i++)
			in_perm[a[i]] = true;
		int[] ret = new int[n - k];
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (in_perm[i] == false) {
				ret[count++] = i;
			}
		}
		return ret;
	}

	private boolean prereqChecker(ArrayList<String> input, int courseIndex,
			int[] a) {
		boolean preq = true;

		for (int j = 0; j < courseData[a[courseIndex]].pre_course.length; j++) {
			preq = input.contains(courseData[a[courseIndex]].pre_course[j]);
			if (preq == false) {
				break;
			}
		}
		return preq;
	}

	private boolean prereqSame(ArrayList<String> sameSemester, int courseIndex,
			int[] a) {
		boolean exist = false;
		for (int j = 0; j < courseData[a[courseIndex]].pre_course.length; j++) {
			exist = sameSemester
					.contains(courseData[a[courseIndex]].pre_course[j]);
			if (exist == true) {
				break;
			}
		}
		return exist;

	}

	private ArrayList<ArrayList<String>> semesterParser(int[] a, String[] s) {
		ArrayList<ArrayList<String>> semesters = new ArrayList<ArrayList<String>>();
		ArrayList<String> sameSemester = new ArrayList<String>();
		ArrayList<String> checker = new ArrayList<String>();
		boolean success = true;
		// season = 0 ; fall
		// season = 1 ; spring

		int season = 0;

		for (int i = 0; i < s.length; i++) {
			// semester set
			if (!sameSemester.isEmpty()) {
				if (sameSemester.size() >= maxNumofCourse){
					if (courseData[a[i]].offerSemester.equalsIgnoreCase("B")){
						semesters.add(sameSemester);
						sameSemester = new ArrayList<String>();
						if (season == 0) season = 1;
						else season = 2;
					}
					else{
						semesters.add(sameSemester);
						semesters.add(new ArrayList<String>());
						sameSemester = new ArrayList<String>();
					}
				}

				
				if (season == 1
						&& courseData[a[i]].offerSemester.equalsIgnoreCase("S")
						|| courseData[a[i]].offerSemester.equalsIgnoreCase("B")
						|| season == 0
						&& courseData[a[i]].offerSemester.equalsIgnoreCase("F")) {
					

					// prereq check
					if (prereqChecker(checker, i, a)) {
						// same semester check
						if (prereqSame(sameSemester, i, a)) {
							if (courseData[a[i]].offerSemester
									.equalsIgnoreCase("B")) {
								semesters.add(sameSemester);
								sameSemester = new ArrayList<String>();
								sameSemester.add(courseData[a[i]].courseName);
								checker.add(courseData[a[i]].courseName);
							} else {
								semesters.add(sameSemester);
								semesters.add(new ArrayList<String>());

								sameSemester = new ArrayList<String>();
								sameSemester.add(courseData[a[i]].courseName);

								checker.add("empty");
								checker.add(courseData[a[i]].courseName);
							}
						} else {
							sameSemester.add(courseData[a[i]].courseName);
							checker.add(courseData[a[i]].courseName);
						}

					} else {
						checker = new ArrayList<String>();
						success = false;
						break;
					}
				}

				else {
					// prereq check
					if (prereqChecker(checker, i, a)) {
						semesters.add(sameSemester);
						sameSemester = new ArrayList<String>();

						sameSemester.add(courseData[a[i]].courseName);
						checker.add(courseData[a[i]].courseName);
						if (season == 0) season = 1;
						else season = 0;
					} else {
						checker = new ArrayList<String>();
						success = false;
						break;
					}
				}
			} else {
				// when empty add
				if (semesters.size() == 0
						&& courseData[a[i]].offerSemester.equalsIgnoreCase("S")) {
					if (prereqChecker(checker, i, a)) {
						// same semester check
						if (prereqSame(sameSemester, i, a)) {

							semesters.add(sameSemester);
							semesters.add(new ArrayList<String>());
							sameSemester = new ArrayList<String>();
							sameSemester.add(courseData[a[i]].courseName);

							checker.add("empty");
							checker.add(courseData[a[i]].courseName);
						} else {
							semesters.add(new ArrayList<String>());

							sameSemester.add(courseData[a[i]].courseName);
							checker.add("empty");
							checker.add(courseData[a[i]].courseName);
						}

						season = 1;
					} else {
						checker = new ArrayList<String>();
						success = false;
						break;
					}

				} else {
					if (prereqChecker(checker, i, a)) {
						// same semester check
						if (prereqSame(sameSemester, i, a)) {
							semesters.add(sameSemester);
							semesters.add(new ArrayList<String>());

							sameSemester = new ArrayList<String>();
							sameSemester.add(courseData[a[i]].courseName);

							checker.add("empty");
							checker.add(courseData[a[i]].courseName);
						} else {
							sameSemester.add(courseData[a[i]].courseName);
							checker.add(courseData[a[i]].courseName);
						}
						season = 0;
					} else {
						checker = new ArrayList<String>();
						success = false;
						break;
					}
				}
			}
		}
		if (success) {
			semesters.add(sameSemester);
			return semesters;
		} else
			return null;
	}

	public int process_solution(int[] a, int k, String[] s) {

		ArrayList<ArrayList<String>> semesters;
		ArrayList<String> allcheck = new ArrayList<String>();
//		Course predeccessor = new Course("", "", 0, new String[0]);

//		for (int i = 0; i < s.length; i++) {
//			allcheck.add(s[i]);
//		}

		semesters = semesterParser(a, s);

		int semesterSize = Integer.MAX_VALUE;
		
		if (semesters != null) {
			semesterSize = semesters.size();
		}
		
		
		return semesterSize;

		// for (int i = 0; i < s.length; i++) {
		// // semester set
		// if (!sameSemester.isEmpty()) {
		// if (sameSemester.get(0) == courseData[a[i]].offerSemester
		// || courseData[a[i]].offerSemester.equalsIgnoreCase("B")) {
		//
		// if (prereqChecker(checker, i, a)) {
		// sameSemester.add(courseData[a[i]].courseName);
		// checker.add(courseData[a[i]].courseName);
		// } else {
		// break;
		// }
		// } else {
		// semesters.add(sameSemester);
		// sameSemester = new ArrayList<String>();
		//
		// if (prereqChecker(checker, i, a)) {
		// sameSemester.add(courseData[a[i]].courseName);
		// checker.add(courseData[a[i]].courseName);
		// } else {
		// break;
		// }
		// }
		// } else {
		// // when empty add
		// if (semesters.size() == 0
		// && courseData[a[i]].offerSemester.equalsIgnoreCase("S")) {
		// semesters.add(new ArrayList<String>());
		//
		// sameSemester.add(courseData[a[i]].courseName);
		// checker.add("empty");
		//
		// } else {
		//
		// sameSemester.add(courseData[a[i]].courseName);
		// checker.add(courseData[a[i]].courseName);
		//
		// }
		// }
		// }
		// semesters.add(sameSemester);

		// for (int i = 0; i < s.length; i++) {
		// boolean preq = true, same = false;
		// int maxNumofCoursechecker = 0;
		//
		// for (int j = 0; j < courseData[a[i]].pre_course.length; j++) {
		//
		// preq = checker.contains(courseData[a[i]].pre_course[j]);
		// same = sameSemester.contains(courseData[a[i]].pre_course[j]);
		//
		// // if (sameSemester.contains(courseData[a[i]].pre_course[j])){
		// // same = true;
		// // }
		//
		// if (preq == false) {
		// break;
		// }
		// }
		//
		// if (preq == true) {
		// if (predeccessor.offerSemester
		// .equalsIgnoreCase(courseData[a[i]].offerSemester)) {
		// maxNumofCoursechecker++;
		// // sameSemester.add(courseData[a[i]].courseName);
		// }
		//
		// else {
		// maxNumofCoursechecker = 0;
		// }
		//
		// if (maxNumofCoursechecker > maxNumofCourse) {
		// // sameSemester.clear();
		// checker.clear();
		// break;
		// }
		//
		// if (same == true) {
		// checker.add("empty");
		// }
		//
		// predeccessor = courseData[a[i]];
		//
		// checker.add(s[a[i]]);
		//
		// boolean checkall = false;
		// for (int q = 0; q < semesters.size(); q++) {
		//
		// }
		//
		// if (semesters.containsAll(allcheck)) {
		// System.out.println(checker.toString());
		// System.out
		// .println("The minimum number of semesters required to graduate is "
		// + semesters.size());
		// }
		//
		// // System.out.print(s[a[i]]);
		// // System.out.print(" ");
		// } else {
		// break;
		// }
		//
		// }
		// System.out.println();
	}

	public boolean is_a_solution(int[] a, int k, String[] s) {
		int n = s.length;
		return k == n;
	}

	public void print(int[] a, int k) {
		for (int i = 0; i < k; i++, System.out.print(" "))
			System.out.print(a[i]);
		System.out.println();
	}
}

// private boolean finished=false;
//
//
// public void Backtrack(int a[], int k, data input)
// {
// int c[MAXcandidates];
// int ncandidates;
// int i;
//
// if (is_a_solution(a,k,input)){
// process_solution(a,k,input);
// }
// else{
// k=k+1;
// construct_candidates(a,k,input,c,&ncandidates);
// for (i=0; i<ncandidates;i++){
// a[k]=c[i];
// make_move(a,k,input);
// backtrack(a,k,input);
// unmake_move(a,k,input);
// if (finished) return;
// }
// }
// }
//
// private void process_solution(int a[], int k){
//
// //check its real solution for this problem.
// //
// solution_count++;
// }
//
// private Boolean is_a_solution(int a[], int k, int t)
// {
// return (a[k]==t);
// }
//
// private void construct_candidtates(in a[], int k, int n, int c[], int
// *ncandidates)
// {
// int i;
// bool in_sol[NMAX];
// edgenode *p;
// int last;
//
// for (i=1; i<NMZX; i++) in_sol[i] = false;
// for (i=1; i<k; i++) in_sol[a[i]]=true;
//
// if (k==1){
// c[0]=1;
// ncandidates=1;
// }
// else{
// ncandidates=0;
// last = a[k-1];
// p=p.edges[last];
// while (p!=null){
// if(!in_sol[p->y]){
// c[*ncandidates]=p->y;
// *ncandidates=*ncandidates+1;
// }
// p=p->next;
// }
// }
// }
//
// }

