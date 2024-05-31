import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List; // Ensure this import is present
import java.util.Random;

public class Main extends Application {

    private Library library;
    private TextField titleField, editionField, yearField, genreField, authorField, publisherField, isbnField;
    private CheckBox hardCoverBox;
    private TextArea outputArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        library = new Library();
        try {
            library.loadBooksFromFile("books.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Library Management System");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label titleLabel = new Label("Title:");
        titleField = new TextField();
        Label editionLabel = new Label("Edition:");
        editionField = new TextField();
        Label yearLabel = new Label("Publication Year:");
        yearField = new TextField();
        Label genreLabel = new Label("Genre:");
        genreField = new TextField();
        Label authorLabel = new Label("Author:");
        authorField = new TextField();
        Label publisherLabel = new Label("Publisher:");
        publisherField = new TextField();
        Label isbnLabel = new Label("ISBN:");
        isbnField = new TextField();
        Label hardCoverLabel = new Label("Hard Cover:");
        hardCoverBox = new CheckBox();
        Button addButton = new Button("Add Book");
        Button removeButton = new Button("Remove Book");
        Button searchButton = new Button("Search by Genre");
        Button recommendButton = new Button("Recommend Book");
        outputArea = new TextArea();
        outputArea.setEditable(false);

        grid.add(titleLabel, 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(editionLabel, 0, 1);
        grid.add(editionField, 1, 1);
        grid.add(yearLabel, 0, 2);
        grid.add(yearField, 1, 2);
        grid.add(genreLabel, 0, 3);
        grid.add(genreField, 1, 3);
        grid.add(authorLabel, 0, 4);
        grid.add(authorField, 1, 4);
        grid.add(publisherLabel, 0, 5);
        grid.add(publisherField, 1, 5);
        grid.add(isbnLabel, 0, 6);
        grid.add(isbnField, 1, 6);
        grid.add(hardCoverLabel, 0, 7);
        grid.add(hardCoverBox, 1, 7);
        grid.add(addButton, 0, 8);
        grid.add(removeButton, 1, 8);
        grid.add(searchButton, 0, 9);
        grid.add(recommendButton, 1, 9);
        grid.add(outputArea, 0, 10, 2, 1);

        addButton.setOnAction(e -> addBook());
        removeButton.setOnAction(e -> removeBook());
        searchButton.setOnAction(e -> searchByGenre());
        recommendButton.setOnAction(e -> recommendBook());

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            try {
                library.saveBooksToFile("books.csv");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void addBook() {
        String title = titleField.getText();
        String edition = editionField.getText();
        int year = Integer.parseInt(yearField.getText());
        String genre = genreField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();
        String isbn = isbnField.getText();
        boolean isHardCover = hardCoverBox.isSelected();

        Book book = new Book(title, edition, year, genre, author, publisher, isbn, isHardCover);
        library.addBook(book);
        outputArea.appendText("Book added: " + title + "\n");
    }

    private void removeBook() {
        String title = titleField.getText();
        String genre = genreField.getText();
        List<Book> books = library.findBooksByGenre(genre); // Correct use of findBooksByGenre
        if (books != null) {
            for (Book book : books) {
                if (book.getTitle().equals(title)) {
                    library.removeBook(book);
                    outputArea.appendText("Book removed: " + title + "\n");
                    return;
                }
            }
        }
        outputArea.appendText("Book not found: " + title + "\n");
    }

    private void searchByGenre() {
        String genre = genreField.getText();
        List<Book> books = library.findBooksByGenre(genre);
        outputArea.appendText("Books in genre " + genre + ":\n");
        for (Book book : books) {
            outputArea.appendText(book.getTitle() + "\n");
        }
    }

    private void recommendBook() {
        String genre = genreField.getText();
        List<Book> books = library.findBooksByGenre(genre);
        if (!books.isEmpty()) {
            Book book = books.get(new Random().nextInt(books.size()));
            outputArea.appendText("Recommended book: " + book.getTitle() + "\n");
        } else {
            outputArea.appendText("No books found in genre " + genre + "\n");
        }
    }
}
