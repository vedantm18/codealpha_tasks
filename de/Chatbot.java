import java.util.Scanner;

public class Chatbot {
  public static void main(String args[]){
    Scanner sc=new Scanner(System.in);
    
    String input="";
    do{
        input=sc.nextLine();

        if(input.equalsIgnoreCase("Hello")){
            System.out.println("Hello Welcome to Chatbot how can i help you today : ");
        }
        else if(input.equalsIgnoreCase("What items does company have : ")){
            System.out.println("The company have products like laptop,mobile phone ,tablet");
        }
        else if(input.equalsIgnoreCase("price of laptop")){
            System.out.println("\tHP- 75000  \tDELL- 85000  \tApple -100000");
        }
        else if(input.equalsIgnoreCase("Which brands of mobile do you have : ")){
            System.out.println("\tIphone  \tSamsung \tOnePlus \tOppo");
        }else{
            if(input.equalsIgnoreCase("END")){
                System.out.println("Thaks for visiting");
            }
        }
    }while(!input.equalsIgnoreCase("END"));


  }
}
