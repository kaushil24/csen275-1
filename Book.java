public class Book {
    private String title;
    private String edition;
    private int publicationYear;
    private String genre;
    private String authorName;
    private String publisher;
    private String ISBN;
    private boolean isHardCover;

    public Book(String title, String edition, int publicationYear, String genre, String authorName, String publisher, String ISBN, boolean isHardCover) {
        this.title = title;
        this.edition = edition;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.authorName = authorName;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.isHardCover = isHardCover;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isHardCover() {
        return isHardCover;
    }

    public void setHardCover(boolean hardCover) {
        isHardCover = hardCover;
    }
}
