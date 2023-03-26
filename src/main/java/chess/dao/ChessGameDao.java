package chess.dao;

import java.util.List;

public interface ChessGameDao {
    void save(String sourceCoordinateRow, String sourceCoordinateColumn, String destinationCoordinateRow,String destinationCoordinateColumn);
    List<List<String>> select();

    void delete();


}
