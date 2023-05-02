import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import java.time.LocalDate;

public class Library {

    static Scanner scanner = new Scanner(System.in);
    static List<Book> books = new ArrayList<>();

    private static List<Member> members = new ArrayList<>();

    public Library(){

    }

    public void RunProgram(){

        //Creating instances of objects to test program
        Book testbook = new Book("1984", "Orwell", 1948, false);
        books.add(testbook);
        Member test = new Member(UUID.randomUUID().toString(), "Test", null, false, null);
        members.add(test);
        System.out.println(test.getId());


        Window window = new Window();
        List<Member> members;
        List<Book> books;
        while(true){
            System.out.println("Welcome to the library, Choose of the options below: ");
            System.out.println("1. Add a member\n" +
                    "2. Add a book \n" +
                    "3. Borrow a book\n" +
                    "4. Return a book \n" +
                    "5. Print Library members\n" +
                    "6. Admin options\n");
            switch (integerInput()) {
                case 1:
                    addMember();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    printMembers();
                    break;
                case 6:
                    //To be done
            }
        }
            }

    public static void addMember(){
        String name;
        String uniqueID = UUID.randomUUID().toString();
        System.out.println("Name? ");
        name = scanner.next();
        Member member = new Member(uniqueID, name, null, false, null);
        members.add(member);
        System.out.println("Welcome, " + name + " to the library!");
    }

    public static void printMembers(){
        for (Member m: members
        ) {
            System.out.println("Name: " +  m.getName());
            System.out.println("Id: " + m.getId());
            for (Book b: m.getBooksBorrowed()
            ) {
                System.out.println("Book : " + b.title + b.author);
            }

            for (Loan l: m.loans
            ) {
                l.Print();
            }
            System.out.println("------------------------------------");
        }
    }

    public static void addBook(){

        String author;
        String title;
        int publicationYear;
                System.out.println("Authors name? ");
                author = scanner.next();

                System.out.println("Title? ");
                title = scanner.next();
        while(true){
            try{

                System.out.println("Publication year? ");
                publicationYear = scanner.nextInt();

                if(publicationYear <= 0){
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
        books.add(new Book(author, title, publicationYear, false));
    }

    public static int integerInput(){
        int choice;
        while (true)
        {
            try
            {
                System.out.println("\nPlease chose an option:  ");
                choice = scanner.nextInt();
                if (choice <= 0)
                {
                    System.out.println("------------------------------------------------------------\n" +
                            "\nError! Submitted integer is not a valid alternative\n" +
                            "\n------------------------------------------------------------");
                }
                break;
            }
            catch(Exception e)
            {
                System.out.println("------------------------------------------------------------\n" +
                        "\nError! Your answer is not in the form of a integer\n" +
                        "\n------------------------------------------------------------");
            }
        }
        return choice;
    }

    public static void borrowBook(){
        Loan loan;
        Book borrowedBook = null;
        String id;
        Member borrower = null;
        while(borrower == null){
            System.out.println("ID : ");
            id = scanner.next();
            for (Member m :members
            ) {
                if(id.equals(m.getId())){
                    borrower = m;
                }
            }
            if(borrower == null){
                System.out.println("Error! There is no member with that id, please submit your id again");
            }
        }


        while(true){
            System.out.println("Enter title of book you want to borrow: ");
            String submittedTitle = scanner.next();
            for (Book b: books
            ) {
                if(submittedTitle.toLowerCase().equals(b.title)){
                    borrowedBook = b;
                    b.setBorrowed(true);
                    loan = new Loan(borrower, LocalDate.now(), Loan.LoanStatus.ACTIVE, submittedTitle);
                    borrower.loans.add(loan);
                    borrower.booksBorrowed.add(b);
                    break;
                }
                else{
                    continue;
                }
            }
            break;
        }
        System.out.println(borrower.getName() + " successfully borrowed " + borrowedBook.getTitle());
    }

    public static void returnBook(){
        Member borrower = null;
        String id;
        while(borrower == null){
            System.out.println("ID : ");
            id = scanner.nextLine();
            for (Member m :members
            ) {
                if(id.equals(m.getId())){
                    borrower = m;
                }
            }
            if(borrower == null){
                System.out.println("Error! There is no member with that id, please submit your id again");
            }
        }

        String submittedTitle;
        System.out.println("Which book would you like to return? (title) ");
        while(true){
            submittedTitle = scanner.nextLine();
            for (Book b: borrower.getBooksBorrowed()
            ) {
                if(submittedTitle.equals(b.title)){
                    b.setBorrowed(false);
                    for (Loan l: borrower.loans
                    ) {
                        if(l.getBookTitle() == submittedTitle){
                            l.setLoanStatus(Loan.LoanStatus.RETURNED);
                        }
                    }
                }
                //Check later if all is correct!
                break;
            }
            System.out.println("Book has been returned ");
        }
    }
}