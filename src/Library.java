import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import java.time.LocalDate;

public class Library {

    static Scanner scanner = new Scanner(System.in);
    static List<Book> books = new ArrayList<>();

    private static List<Member> members = new ArrayList<>();

    public Library() {

    }

    public void RunProgram() {

        //Creating instances of objects to test program
        Book testBook = new Book("1984", "Orwell", 1948, false);
        Magazine testMagazine = new Magazine("Spiderman", "Stan Lee", 1962, false, 1);
        books.add(testMagazine);
        books.add(testBook);
        Librarian librarian = new Librarian(UUID.randomUUID().toString(), "Admin", new ArrayList<>(), true, new ArrayList<>());
        Member test = new Member(UUID.randomUUID().toString(), "Test", new ArrayList<>(), false, new ArrayList<>());
        members.add(test);
        members.add(librarian);
        System.out.println(test.getId());
        System.out.println(librarian.getId());

        EventBooker eventSystem = new EventBooker();
        List<Book> books;
        while (true) {
            System.out.println("Welcome to the library, Choose of the options below: ");
            System.out.println("1. Add a member\n" +
                    "2. Add a book \n" +
                    "3. Borrow a book\n" +
                    "4. Return a book \n" +
                    "5. Print Library members\n" +
                    "6. Admin options\n");
            switch (integerInput(1, 6)) {
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
                    boolean hasPermission = false;
                    System.out.println("ID? ");
                    String submittedID = scanner.next();
                    for (Member m : members
                    ) {
                        if (m.adminPermissions == true && m.getId().equals(submittedID)) {
                            hasPermission = true;
                        }
                    }
                    if(hasPermission) {
                        System.out.println("1. Remove member");
                        System.out.println("2. Remove book");
                        while (true) {
                            int choice = integerInput(1, 2);
                            if (choice == 1) {
                                deleteMember();
                                break;
                            } else if (choice == 2) {
                                removeBook();
                                break;
                            }
                        }
                    }
                    else{
                        System.out.println("Error! That member is not registered as a librarian");
                    }
            }
        }
    }

    public static void addMember(){
        //Method for creating new instances of the class member
        String name;
        String uniqueID = UUID.randomUUID().toString();
        System.out.println("Name? ");
        name = scanner.next();
        Member member = new Member(uniqueID, name, new ArrayList<>(), false, new ArrayList<>());
        members.add(member);
        System.out.println("Welcome, " + name + " to the library!");
    }

    public static void printMembers(){
        for (Member m: members
        ) {
            m.print();
        }
    }

    //Method that creates new instances of the class book
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

    public static int integerInput(int lowerLimit, int upperLimit){
        //Method for recieving integerInput for a certain range of values
        int choice;
        while (true)
        {
            try
            {
                System.out.println("\nPlease chose an option:  ");
                choice = scanner.nextInt();
                if (choice <= 0 || (choice > upperLimit || choice < lowerLimit))
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
            scanner.next();
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
                if(submittedTitle.equalsIgnoreCase(b.title)){
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
        //Method for returning book by finding member via ID
        Boolean finishedReturn = false;
        Member borrower = null;
        String id;
        while(borrower == null){
            System.out.println("ID : ");
            id = scanner.next();
            for (Member m :members
            ) {
                if(id.equals(m.getId())){
                    borrower = m;
                    break;
                }
            }
            if(borrower == null){
                System.out.println("Error! There is no member with that id, please submit your id again");
            }
        }

        //Finding the book to be returned and changing its loaned status
        String submittedTitle;
        System.out.println("Which book would you like to return? (title) ");
        while(true){
            submittedTitle = scanner.next();
            for (Book b: borrower.getBooksBorrowed()
            ) {
                if(submittedTitle.equalsIgnoreCase(b.title) && b.isBorrowed()==true){
                    b.setBorrowed(false);
                    for (Loan l: borrower.loans
                    ) {
                        if(l.getBookTitle().equalsIgnoreCase(submittedTitle)){
                            l.setLoanStatus(Loan.LoanStatus.RETURNED);
                        }
                    }
                    finishedReturn =true;
                }
            }
            if(finishedReturn.equals(true)){
                System.out.println("Book has been returned ");
                break;
            }
            System.out.println("Error! Book is not borrowed or title is incorrect");
            System.out.println("\nTry again: ");
        }
    }

    public static void removeBook(){
        //Method for removing book by finding the book via title
        System.out.println("Title of book you want to remove? ");
        while(true){
            String deletedBook = scanner.next();
            for (Book b: books
            ) {
                if(b.getTitle().equalsIgnoreCase(deletedBook)){
                    books.remove(b);
                    System.out.println("You have successfully remove " + b.getTitle());
                }
            }

        }

    }
    public static void deleteMember(){
        //Method for removing a member through id
        System.out.println("ID of member you want to delete? ");
        String id = scanner.next();
        for (Member m: members
        ) {
            if(m.getId().equals(id)){
                members.remove(m);
                System.out.println("You have successfully deleted " + m.getName());
            }
        }
    }
}