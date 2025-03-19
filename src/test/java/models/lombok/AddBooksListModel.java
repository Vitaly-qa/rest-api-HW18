package models.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class AddBooksListModel {

    String userId;
    List<IsbnModel> collectionOfIsbns;
}
