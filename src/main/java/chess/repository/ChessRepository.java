package chess.repository;

import java.sql.SQLException;
import java.util.Map;

public interface ChessRepository {
    void initializePieceStatus(final Map<String, String> board) throws SQLException;

    void initializeTurn() throws SQLException;

    void removeAllPieces() throws SQLException;

    void removeTurn() throws SQLException;
}
