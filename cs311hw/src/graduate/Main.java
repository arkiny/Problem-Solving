package graduate;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

;

public class Main {

	/**
	 * 
	 * @author Arkiny
	 * 
	 *         I will using backtrack algorithm and, its permutation for this
	 *         problem.
	 * 
	 *         1. get all permutation subset from the courses from dataSet. -
	 *         permutation data should include semester data (like f1, s2, s1) -
	 *         permutation solution set should looks like - [course1] [course2]
	 *         [course3] - [courseName, semester] - ex) [cs321, fall, 1] [cs222,
	 *         spring, 1] [cs330, fall, 2]
	 * 
	 *         2. Check its pre-requisition is right if all pre-requisition are
	 *         right, - pre-requisition should not in same semester -
	 *         pre-requisition should be in the previous semester check next
	 *         condition else move to next solution.
	 * 
	 *         3. Check offered semester - case First of all semester -if fall
	 *         add empty semester move to next -if not fall, add empty semester
	 *         to head -
	 * 
	 *         3. check its max semester is right if right(all semester is less
	 *         than maximum course number), else move to next solution
	 * 
	 * 
	 * 
	 *         4. return minimum semester number
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("graduate.in");
		Scanner inFile = new Scanner(fr);
		Graduate a = new Graduate(inFile);
		inFile.close();
		fr.close();
	}

}

//

