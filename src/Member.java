import java.util.List;

public class Member {

    private String id;
    private String name;
    List<Book> booksBorrowed;

    List<Loan> loans;

    public boolean adminPermissions = false;

    public Member(String id, String name, List<Book> booksBorrowed, boolean adminPermissions, List<Loan> loans){
        this.id = id;
        this.name = name;
        this.booksBorrowed= booksBorrowed;
        this.adminPermissions = adminPermissions;
        this.loans = loans;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public List<Book> getBooksBorrowed() {
        return booksBorrowed;
    }

    public void print(){
        //Print user data
        System.out.println("Name: " +  this.name);
        System.out.println("Id: " + this.id);
        for (Book b: this.getBooksBorrowed()
        ) {
            System.out.println("Book : " + b.title + b.author);
        }

        //Print all the users loans
        for (Loan l: this.loans
        ) {
            l.print();
        }
        System.out.println("------------------------------------");
    }
}