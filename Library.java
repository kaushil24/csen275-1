import java.io.*;
import java.util.*;

public class Library {
    private Map<String, List<Book>> booksByGenre;

    public Library() {
        booksByGenre = new HashMap<>();
    }

    public void loadBooksFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty() || line.startsWith("title")) {
                // Skip empty lines and header
                continue;
            }
            String[] details = line.split(",");
            String title = details[0];
            String edition = details[1];
            int year = Integer.parseInt(details[2]);
            String genre = details[3];
            String author = details[4];
            String publisher = details[5];
            String isbn = details[6];
            boolean isHardCover = Boolean.parseBoolean(details[7]);

            Book book = new Book(title, edition, year, genre, author, publisher, isbn, isHardCover);
            addBook(book);
        }
        br.close();
    }

    public void saveBooksToFile(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write("title,edition,publicationYear,genre,authorName,publisher,ISBN,isHardCover\n"); // Header
        for (List<Book> books : booksByGenre.values()) {
            for (Book book : books) {
                bw.write(String.join(",", book.getTitle(), book.getEdition(), String.valueOf(book.getPublicationYear()),
                        book.getGenre(), book.getAuthorName(), book.getPublisher(), book.getISBN(), String.valueOf(book.isHardCover())));
                bw.newLine();
            }
        }
        bw.close();
    }

    public List<Book> findBooksByGenre(String genre) {
        return booksByGenre.getOrDefault(genre, new ArrayList<>());
    }

    public void addBook(Book book) {
        booksByGenre.computeIfAbsent(book.getGenre(), k -> new ArrayList<>()).add(book);
    }

    public void removeBook(Book book) {
        List<Book> books = booksByGenre.get(book.getGenre());
        if (books != null) {
            books.remove(book);
        }
    }
}
