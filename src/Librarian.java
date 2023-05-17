import java.util.List;

public class Librarian extends Member{

    public Librarian(String id, String name, List<Book> booksBorrowed, boolean adminPermissions, List<Loan> loans) {
        super(id, name, booksBorrowed, adminPermissions, loans);
    }
}