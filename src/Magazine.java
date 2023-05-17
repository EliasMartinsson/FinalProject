public class Magazine extends Book {
    private int issueNumber;

    public Magazine(String title, String author, int publicationYear, boolean borrowed, int issueNumber) {
        super(title, author, publicationYear, borrowed);
        this.issueNumber = issueNumber;
    }
}