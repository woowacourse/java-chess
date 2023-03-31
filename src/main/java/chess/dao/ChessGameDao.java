package chess.dao;

import java.util.List;

public interface ChessGameDao {
    void save(String sourceCoordinateRow, String sourceCoordinateColumn, String destinationCoordinateRow,String destinationCoordinateColumn);
    List<List<String>> readFormerGame();

    String getFormerSourceRow(List<String> formerMove);
    String getFormerSourceColumn(List<String> formerMove);
    String getFormerDestinationRow(List<String> formerMove);
    String getFormerDestinationColumn(List<String> formerMove);

    void deleteAll();


}
