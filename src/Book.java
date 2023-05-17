public class Book {


    String title;
    String author;

    int publicationYear;


    boolean borrowed;

    public Book(String title, String author, int publicationYear, boolean borrowed) {

        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.borrowed = borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public String getTitle() {
        return title;
    }


}