package chess.repository;

import chess.dto.ChessRequestDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ChessRepository {
    void initializePieceStatus(final Map<String, String> board) throws SQLException;

    void initializeTurn() throws SQLException;

    List<ChessRequestDto> showAllPieces() throws SQLException;

    void removeAllPieces() throws SQLException;

    void removeTurn() throws SQLException;
}
