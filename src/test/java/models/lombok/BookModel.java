package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class BookModel {
        String isbn, title;

        public BookModel(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

