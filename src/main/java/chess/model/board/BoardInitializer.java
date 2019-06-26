package chess.model.board;

import chess.model.Square;
import chess.model.unit.Piece;

import java.sql.SQLException;
import java.util.Map;

public interface BoardInitializer {
    Map<Square, Piece> initialize() throws SQLException;
}