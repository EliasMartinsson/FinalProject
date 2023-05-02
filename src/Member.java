import java.util.List;

public class Member {

    private String id;
    private String name;
    List<Book> booksBorrowed;

    List<Loan> loans;

    private boolean adminPermissions = false;

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

    public class Librarian extends Member{

        public Librarian(String name, List<Book> booksBorrowed, boolean adminPermissions) {
            super(id, name, booksBorrowed, adminPermissions, null);
            adminPermissions = true;
        }
    }

}
