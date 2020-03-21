import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * This program uses ADT's to get student info.
 * 
 * @author Amin Zeina
 * @version 1.0
 * @since 2020-03-20
 */



public class StudentDT {
  
  enum Grades {
  
    TWELVE("12"),
    ELEVEN("11"),
    TEN("10"),
    NINE("9"),
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1"),
    SK("Senior Kindergarten"),
    JK("Junior Kindergarten");
  
    private String grade;

    Grades(String grade) {
      this.grade = grade;
    }
    
    public String getGrade() {
      return this.grade;
    }
  }
  
  /**
  * This class is the student data type.
  */
  
  class Student {
    
    public String firstNamePublic;
    public String lastNamePublic;
    public String middleInitialPublic;
    public String dateOfBirthPublic; //dd/mm/yyyy
    public Grades gradePublic;
    public boolean identifiedPublic;
  
    //constructor
    public Student(String firstName, 
                String lastName, 
                String middleInitial, 
                String dateOfBirth, 
                Grades grade, 
                boolean identified) {
    
      this.firstNamePublic = firstName;
      this.lastNamePublic = lastName;
      this.middleInitialPublic = middleInitial;
      this.dateOfBirthPublic = dateOfBirth;
      this.gradePublic = grade;
      this.identifiedPublic = identified;
    }
  }
  /**
  * This method checks if date's are formatted correctly.
  * Used this as a guide: https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/
  */
 
  public static boolean checkDate(String dateToValidate, String dateFromat) {
  
    if (dateToValidate == null) {
      return false;
    }
    
    SimpleDateFormat dateFormat = new SimpleDateFormat(dateFromat);
    dateFormat.setLenient(false);
    
    try {
      // If not valid, it will throw ParseException
      Date date = dateFormat.parse(dateToValidate);
    
    } catch (ParseException e) {
      // Date invalid
      return false;
    }
    // Date Valid
    return true;
  }

  /**
  * This class gets student info and then returns it to the user.
  */
 
  public static void main(String[] args)  {
    
    Scanner userInput = new Scanner(System.in);
    
    // Delcare and initaliazing necessary variables
    Grades grade = Grades.JK;
    boolean identified = false;
    boolean moreStudents = true;
    boolean validGrade = false;
    boolean validDate = false; 
    String gradeString;
    String dateEntered;
    
    for (int counter = 0; moreStudents == true; counter++) {
      
      validGrade = false;
      validDate = false; 
      String[] studentInfo = new String[6];
      
      System.out.print("Enter a first name for student #" + (counter + 1) + ": ");
      studentInfo[0] = userInput.nextLine();
        
      System.out.print("Enter a last name for student #" + (counter + 1) + ": ");
      studentInfo[1] = userInput.nextLine();
      
      System.out.print("Enter a middle initial for student #" + (counter + 1) + ": ");
      studentInfo[2] = userInput.nextLine();
      
      // Loop to repeat getting date until valid entry
      while (validDate == false) {
        System.out.print("Enter a date of birth (DD/MM/YYYY) for student #" + (counter + 1) + ": ");
        dateEntered = userInput.nextLine();
        if (checkDate(dateEntered, "dd/MM/yyyy") == true) {
          validDate = true;
          studentInfo[3] = dateEntered;
        } else {
          System.err.println("That date is invalid, please try again.");
          validDate = false;
        }
      }
      
      // Loop to repeat getting grade until valid entry
      while (validGrade == false) {
        try {
          System.out.print("Enter a grade (as plain text: JK-TWELVE) for student #" 
              + (counter + 1) + ": ");
          gradeString = userInput.nextLine();
          grade = Grades.valueOf(gradeString.toUpperCase());
          validGrade = true;
        } catch (IllegalArgumentException e) {
          System.err.println("That is not a grade, please try again.");
          validGrade = false;
        }   
      }
      
      System.out.print("Is student #" + (counter + 1) + " identified? (yes/no): ");
      String identifiedInput = userInput.nextLine().toLowerCase();  
      if (identifiedInput.equals("yes")) {
        identified = true;
      } else {
        identified = false;
      }
      
      // Create the student type object
      Student currentStudent = new Student(studentInfo[0], studentInfo[1], studentInfo[2], 
          studentInfo[3], grade, identified);
      
      // Print values of the student type object    
      System.out.println(currentStudent.firstNamePublic + " " + currentStudent.middleInitialPublic
          + " " + currentStudent.lastNamePublic + ", born " + currentStudent.dateOfBirthPublic 
          + ", is in Grade " + currentStudent.gradePublic.getGrade() + ".");
      
      // Check if the user needs to enter another student
      System.out.print("Is there another student's info to enter? (yes/no): ");
      String enterAgain = userInput.nextLine().toLowerCase();
      if (enterAgain.equals("yes")) {
        moreStudents = true;
      } else {
        moreStudents = false;
      }
    }
    System.out.println("Program End.");
  }
}