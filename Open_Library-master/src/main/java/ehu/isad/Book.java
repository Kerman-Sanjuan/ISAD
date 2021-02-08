package ehu.isad;

public class Book {
    String isbn;
    String title;
    String thumbnail_url;
    Details details;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public Details getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return title;
    }

}
