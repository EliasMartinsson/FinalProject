import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Library {

    static Scanner scanner = new Scanner(System.in);
    List<Book> books = new ArrayList<>();

    private static List<Member> members = new ArrayList<>();

    public Library(){

    }



    public void runProgram(){
        Window window = new Window();
        List<Member> members;
        List<Book> books;
    }

    public static void addMember(){
        String name;
        String uniqueID = UUID.randomUUID().toString();
        System.out.println("Name? ");
        name = scanner.next("");
        Member member = new Member(uniqueID, name, null, false);

        members.add(member);
    }

    public static void addBook(){

        String author;
        String title;
        int publicationyear;
        while(true){
            try{
                System.out.println("Authors name? ");
                author = scanner.next("");

                System.out.println("Title? ");
                title = scanner.next("");

                System.out.println("Publication year? ");
                publicationyear = scanner.nextInt();

                if(publicationyear <= 0){
                    continue;
                }
                else{
                    break;
                }
            }
            catch (Exception e){
                System.out.println("Error! The entered information is invalid. Try again!");
            }
        }
    }
}