import java.util.List;

public class Member {

    private String id;
    private String name;
    private List<Book> booksBorrowed;

    private boolean adminPermissions = false;

    public Member(String id, String name, List<Book> booksBorrowed, boolean adminPermissions){
        id = this.id;
        name = this.name;
        booksBorrowed = this.booksBorrowed;
        adminPermissions = this.adminPermissions;
    }

    public class Librarian extends Member{

        public Librarian(String name, List<Book> booksBorrowed, boolean adminPermissions) {
            super(id, name, booksBorrowed, adminPermissions);
            adminPermissions = true;
        }
    }
}