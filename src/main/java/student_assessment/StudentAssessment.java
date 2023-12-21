package student_assessment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class StudentAssessment {

	public static void main(String[] args) throws IOException {
		String line = "";

		BufferedReader reader = new BufferedReader(
				new FileReader("C:\\JavaPrograms\\student_assessment\\src\\main\\resources\\sample.file"));

		StringBuilder sbuilderobj = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			sbuilderobj.append(line);

		}
		System.out.println("Original Json : " + sbuilderobj.toString());
		
		System.out.println("**************************************");

		JSONObject jsonobj = new JSONObject(sbuilderobj.toString());
		String username = jsonobj.getString("username");
		System.out.println("Value of username :" + username);
		
		System.out.println("**************************************");

		JSONArray sessionid = jsonobj.getJSONArray("sessionid");
		Iterator i = sessionid.iterator();
		while (i.hasNext()) {
			int sessionID = (int) i.next();
			System.out.println("Value of sessionid : " + sessionID);

		}
		
		System.out.println("**************************************");

		System.out.println("Last Value of sessionid : " + sessionid.get(3));
		
		System.out.println("**************************************");

		JSONArray studentarray = jsonobj.getJSONArray("students");
		System.out.println(studentarray);

		int counter = 0;
		List<String> studentcontacts = new ArrayList<String>();
		Iterator j = studentarray.iterator();
		while (j.hasNext()) {
			JSONObject studentobj = (JSONObject) j.next();
			JSONArray address = studentobj.getJSONArray("adresss");
			JSONArray marks = studentobj.getJSONArray("marks");
			System.out.println(address);
			
			System.out.println("**************************************");
			
			//contacts of all students 
			
			JSONArray contactarray = studentobj.getJSONArray("contact");
			Iterator allcontacts = contactarray.iterator();
			while (allcontacts.hasNext()) {
				String studentcontact = (String) allcontacts.next();
				System.out.println("The contacts of all students : " + studentcontact);
			}
			
			
			System.out.println("**************************************");
			
			// condition check for first student
			if (counter == 0) {
				JSONObject statecity = (JSONObject) address.get(0);
				System.out.println("Address of first student " + statecity);
				
				System.out.println("**************************************");

				for (int l = 0; l < address.length(); l++) {
					if (l == 1) {
						JSONObject state = address.getJSONObject(l);
						System.out.println("second state value of first student :" + state.get("state"));
					}
				}
			}
			
			System.out.println("**************************************");

			// condition check for second student

			if (counter == 1) {
				System.out.println("Marks of second student : " + marks);
				
				System.out.println("**************************************");

				// second contact value of second student
				int m = 0;
				for (Object contact : contactarray) {
					if (m == 1) {

						System.out.println("second contact value of second student :" + contact);
					}
					m++;
				}
				System.out.println("**************************************");

				// all cities of second student
					
				String allcities = "";

				for (int l = 0; l < address.length(); l++) {

					JSONObject cities = address.getJSONObject(l);
					if (allcities == "") {
						allcities = cities.getString("city");

					}
					else {
						allcities += "," + cities.getString("city");
					}

				}
				System.out.println("second city value of first student :" + allcities);
			}

			counter++;

		}

	}

}
