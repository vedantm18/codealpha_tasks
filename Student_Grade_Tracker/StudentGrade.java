// ● Build a Java program to input and manage student grades.
// ● Calculate average, highest, and lowest scores.
// ● Use arrays or ArrayLists to store and manage data.
// ● Display a summary report of all students.
// ● Make the interface console-based or GUI-based as desired.
import java.util.Scanner;
class Student{
    static final int MAX=50;
    static final int subject=5;
    static String studname[]=new String[MAX];
    static int marks[][]=new int[MAX][subject];
    static int count=0;
 Scanner sc=new Scanner(System.in);
   
   static void addStudent(Scanner sc){
         if(count>=MAX){
            System.out.println("Student Limit Reached");
         }else{
            System.out.println("Enter Student Name : ");
            sc.nextLine();
            studname[count]=sc.nextLine();
             for(int i=0;i<subject;i++){
                System.out.println("Subject : "+(i+1)+" : ");
                marks[count][i]=sc.nextInt();
             }

             count++;
         }
    }
   static void calculate_highest(){
    if(count == 0){
    System.out.println("No data available");
    return;
}
        int highest=marks[0][0];
        for(int i=0;i<count;i++){
            for(int j=0;j<subject;j++){
            if(marks[i][j]>highest){
                highest=marks[i][j];
            }
        }
    }
        System.out.println("Highest Marks is :"+highest);
        System.out.println();
    }
    static void calculate_lowest(){
        if(count == 0){
    System.out.println("No data available");
    return;
}
        int lowest=marks[0][0];
        for(int i=0;i<count;i++){
            for(int j=0;j<subject;j++){
            if(marks[i][j]<lowest){
                lowest=marks[i][j];
            }
        }
    }
        System.out.println("Lowest Marks is :"+lowest);
        System.out.println();
    }
   static void displayStudents(){
    if(count==0){
        System.out.println("No records found");
    }else{
        for(int i=0;i<count;i++){
            int total=0;
           System.out.println("Name : "+studname[i]);
            for(int j=0;j<subject;j++){
                System.out.println("Marks : "+marks[i][j]);
                total+=marks[i][j];
            }
            double avg=(double)total/subject;
            System.out.println("Average marks : "+avg);
        }
    }
    System.out.println();
   }
}
public class StudentGrade{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);

        int choice;
        do{
        System.out.println("1.Enter Student Info");
        System.out.println("2.Display all Students");
        System.out.println("3.Get Highest mark of Student");
        System.out.println("4.Get lowest mark of Student");
        System.out.println("Exit");
     
        System.out.println("Enter  your choice : ");
         choice =sc.nextInt();
         switch(choice){
            case 1:
                 Student.addStudent(sc);
                break;
            case 2:
                Student.displayStudents();
                break;
            case 3:
                Student.calculate_highest();
                break;

            case 4:
                Student.calculate_lowest();
                break;
            
            default:
                System.out.println("Ivalid Input");
                break;
                
         }


        }while(choice!=0);

      sc.close();
    }
}