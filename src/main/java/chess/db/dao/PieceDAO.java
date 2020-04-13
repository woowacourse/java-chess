package chess.db.dao;

import java.util.Map;

public interface PieceDAO {
    void deleteTable();

    void insertPiece(String position,String chessPiece);

    Map<String,String> selectBoard();

    void updatePiece(String SourcePosition, String TargetPosition);

    void deleteCaughtPiece(String position);
}
