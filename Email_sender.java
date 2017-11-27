/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// SENDER EMAIL nahuel.java.program@gmail.com
package email_sender;



import java.util.*; // Creates Array lists
import java.io.*; // Reads and creates file objects
import javax.swing.*; // Allows to build dialog windows

// Libraries for sending emails:
import javax.mail.*; 
import javax.mail.internet.*; 


// BEGINNING public class travel_expense
public class Email_sender {

	// BEGINNING public static void...
	public static void main(String[] args){

// ------------ BEGINNING opening window ------------------------------------	

int opening_window = JOptionPane.showConfirmDialog(null,
        "Click Yes to send the assignments via E-Mail",
        "Email Sender", JOptionPane.YES_NO_CANCEL_OPTION);
if (opening_window == JOptionPane.NO_OPTION) System.exit(0);
if (opening_window == JOptionPane.CANCEL_OPTION) System.exit(0);
if (opening_window == JOptionPane.CLOSED_OPTION) System.exit(0);

// ------------ END opening window ------------------------------------------

// ------------ BEGINNING READING data from "assignments.csv" ---------------	

String assignments_db = "/The/Directory/of/your/csv/file/email_list.csv/assignments.csv"; // paste here the directory of your csv file
File file_assignments_db = new File(assignments_db);
ArrayList<String> lines_of_assignments = new ArrayList<String>();

try {
	BufferedReader reader = new BufferedReader(new FileReader(assignments_db));
	
	String line = null;
	
	try {

	while ((line = reader.readLine()) != null) {
	    lines_of_assignments.add(line);
		}

	} 
	catch (IOException e) { e.printStackTrace(); }
} catch (FileNotFoundException e) { e.printStackTrace(); }

ArrayList<String> line1 = new ArrayList<String>(Arrays.asList(lines_of_assignments.get(1).split(",")));
ArrayList<String> line2 = new ArrayList<String>(Arrays.asList(lines_of_assignments.get(2).split(",")));
ArrayList<String> line3 = new ArrayList<String>(Arrays.asList(lines_of_assignments.get(3).split(",")));
ArrayList<String> line4 = new ArrayList<String>(Arrays.asList(lines_of_assignments.get(4).split(",")));

ArrayList<ArrayList<String>> md_lines = new ArrayList<ArrayList<String>>();

md_lines.addAll(Arrays.asList(line1, line2, line3, line4));

// ------------ END READING data from "assignments.csv" ---------------------

// ------------ BEGINNING READING data from "email_list.csv" ---------------- 

String email_db = "/The/Directory/of/your/csv/file/email_list.csv"; // paste here the directory of your csv file
File file_email_db = new File(email_db);
ArrayList<String> lines_of_email_list = new ArrayList<String>();

 try {
	BufferedReader reader = new BufferedReader(new FileReader(email_db));
	
	String line = null;
	
	try {

	while ((line = reader.readLine()) != null) {
	    lines_of_email_list.add(line);
		}

	} 
	catch (IOException e) { e.printStackTrace(); }
} catch (FileNotFoundException e) { e.printStackTrace(); }

ArrayList<String> student1 = new ArrayList<String>(Arrays.asList(lines_of_email_list.get(1).split(",")));
ArrayList<String> student2 = new ArrayList<String>(Arrays.asList(lines_of_email_list.get(2).split(",")));
ArrayList<String> student3 = new ArrayList<String>(Arrays.asList(lines_of_email_list.get(3).split(",")));
ArrayList<String> student4 = new ArrayList<String>(Arrays.asList(lines_of_email_list.get(4).split(",")));
ArrayList<String> student5 = new ArrayList<String>(Arrays.asList(lines_of_email_list.get(5).split(",")));
ArrayList<String> student6 = new ArrayList<String>(Arrays.asList(lines_of_email_list.get(6).split(",")));
ArrayList<String> student7 = new ArrayList<String>(Arrays.asList(lines_of_email_list.get(7).split(",")));

ArrayList<ArrayList<String>> md_students = new ArrayList<ArrayList<String>>();

md_students.addAll(Arrays.asList(student1, student2, student3, student4, student5, student6, student7));

// ------------ END READING data from "email_list.csv" ----------------------

// ------------ BEGINNING comparing ----------------------------------------- 

int name_in_email = 0;
int n_of_line = 0;

while(n_of_line < md_lines.size()){

		// finds the name
		while (name_in_email <= md_students.size()) {	
			if(md_lines.get(n_of_line).get(0).contains(md_students.get(name_in_email).get(0))){
				md_lines.get(n_of_line).add(md_students.get(name_in_email).get(1));
				break;
			}
			else{ name_in_email++; } 
		}	

		name_in_email = 0;

		// finds the assitant
		while (name_in_email <= md_students.size()) {
			if(md_lines.get(n_of_line).get(1).contains("NoNe")){
				break;
			}
			else if(md_lines.get(n_of_line).get(1).contains(md_students.get(name_in_email).get(0))){
				md_lines.get(n_of_line).add(md_students.get(name_in_email).get(1));
				break;
			}
			else{ name_in_email++; }
		}

n_of_line++;
}

// ------------ END comparing -----------------------------------------------

// ------------ BEGINNING email sender --------------------------------------

final String username = "your.email.account@gmail.com"; // The account used to send the email
final String password = "**********"; // The password of the gmail account

Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.port", "587"); // Port used for gmail

Session session = Session.getInstance(props,
	new javax.mail.Authenticator() {

		protected PasswordAuthentication getPasswordAuthentication() { // this function is used to authenticate 
			return new PasswordAuthentication(username, password);

		}
	});

// ------------ END email sender --------------------------------------------

// ------------ BEGINNING email loop ----------------------------------------

ArrayList<String> report = new ArrayList<String>();

int line = 0;

// BEGINNING while loop
while(line < md_lines.size()){ 

	// WITHOUT assistant
	if(md_lines.get(line).size() == 6) {

	 	//System.out.println("size 6"); // NO ASSISTANT
		try { 

			String mail_name = md_lines.get(line).get(5);
			String subject = String.format("Assignment %s", md_lines.get(line).get(2));
			String email = String.format("<html><body style='font-family:Helvetica;'>"
							+ "<p>Ciao,<br>"
							+ "<br>"
							+ "You have an assignment.<br>"
							+ "<br>"
							+ "Name: <span style='font-weight:bold;'>%s</span><br>"
							+ "Date: <span style='font-weight:bold;'>%s</span><br>"
							+ "Study Number: <span style='font-weight:bold;'>%s</span><br>"
							+ "Assignment: <span style='font-weight:bold;'>%s</span><br>"
							+ "<br>"
							+ "In case you cannot do the part, please contact the Chairman for the night of the assignment or J*** D*****.<br>" 
							+ "<br>"
							+ "Joel Dylong:<br>"
							+ "<br>"
							+ "<span style='margin-left: 30px;'>email: j***.d*****@gmail.com</span><br>"
							+ "<span style='margin-left: 30px;'>phone: +1(***)***.****</span><br>"
							+ "<br>"
							+ "If you are the assistant please speak with the householder.<br>"
							+ "<br>"
							+ "Buona Preparazione!<br>"
							+ "Nahuel Pregot."
							+ "</p></body></html>",  
							md_lines.get(line).get(0),  
							md_lines.get(line).get(2), 
							md_lines.get(line).get(3), 
							md_lines.get(line).get(4));

			Message message = new MimeMessage(session); // for comments regarding this part check "else if" statement
			message.setFrom(new InternetAddress(username)); 
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mail_name)); 
			message.setSubject(subject);
			message.setContent(email, "text/html; charset=utf-8"); 
			Transport.send(message); // sends the entire message object

			System.out.format("Email sent to: %s, %s %n", md_lines.get(line).get(0) ,md_lines.get(line).get(5));
			report.add(String.format("Email sent to %s at %s %n", md_lines.get(line).get(0) ,md_lines.get(line).get(5)));


		} catch (MessagingException e) { 
			throw new RuntimeException(e); // if the address of the receiver is wrong
	 	  }
				} // END "if" statement

	
	// WITH assistant
	else if(md_lines.get(line).size() == 7) {

		//System.out.println("size 7");
		try { 

			String mail_name = md_lines.get(line).get(5);
			String mail_assistant = md_lines.get(line).get(6);
			String subject = String.format("Assignment %s", md_lines.get(line).get(2));
			String email = String.format("<html><body style='font-family:Helvetica;'>"
							+ "<p>Ciao,<br>"
							+ "<br>"
							+ "You have an assignment.<br>"
							+ "<br>"
							+ "Name: <span style='font-weight:bold;'>%s</span><br>"
							+ "Assistant: <span style='font-weight:bold;'>%s</span><br>"
							+ "Date: <span style='font-weight:bold;'>%s</span><br>"
							+ "Study Number: <span style='font-weight:bold;'>%s</span><br>"
							+ "Assignment: <span style='font-weight:bold;'>%s</span><br>"
							+ "<br>"
							+ "In case you cannot do the part, please contact the Chairman for the night of the assignment or J*** D*****.<br>" 
							+ "<br>"
							+ "Joel Dylong:<br>"
							+ "<br>"
							+ "<span style='margin-left: 30px;'>email: j***.d*****@gmail.com</span><br>"
							+ "<span style='margin-left: 30px;'>phone: +1(***)***.****</span><br>"
							+ "<br>"
							+ "If you are the assistant please speak with the householder.<br>"
							+ "<br>"
							+ "Buona Preparazione!<br>"
							+ "Nahuel Pregot."
							+ "</p></body></html>",  
							md_lines.get(line).get(0), 
							md_lines.get(line).get(1), 
							md_lines.get(line).get(2), 
							md_lines.get(line).get(3), 
							md_lines.get(line).get(4));

			Message message = new MimeMessage(session); // message object that is the email
			message.setFrom(new InternetAddress(username)); // where the email is sent from (nahuel.java.program@gmail.com)
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mail_name)); // where the email is sent
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(mail_assistant)); // email address of the assistant
			message.setSubject(subject); // the subject of the email
			message.setContent(email, "text/html; charset=utf-8"); // the parameters to interpret the content as html code
			Transport.send(message); // sends the entire message object

			System.out.format("Email sent to: %s, %s %n", md_lines.get(line).get(0) ,md_lines.get(line).get(5));
			report.add(String.format("Email sent to %s at %s %n", md_lines.get(line).get(0) ,md_lines.get(line).get(5)));

			System.out.format("Email sent to: %s, %s %n", md_lines.get(line).get(1) ,md_lines.get(line).get(6));
			report.add(String.format("Email sent to %s at %s %n", md_lines.get(line).get(1) ,md_lines.get(line).get(6)));


		} catch (MessagingException e) { 
			throw new RuntimeException(e); // if the address of the receiver is wrong
	 }
		 
	} // END "else if" statement

	line++;

} // END while loop

// ------------ END email loop ----------------------------------------------

// ------------ BEGINNING final window --------------------------------------

String output = "";
for(int i = 0; i<report.size(); i++){
    String everything = report.get(i).toString();
    output += everything;       
}

int resultzzz = JOptionPane.showConfirmDialog(null, output, "Email Sender", JOptionPane.PLAIN_MESSAGE);

// ------------ END final window --------------------------------------------

					}; // END public static void...
}; // END public class travel_expense
