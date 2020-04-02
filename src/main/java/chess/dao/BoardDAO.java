package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public interface BoardDAO {
    void placePieceOn(Position position, Piece piece) throws SQLException;

    Optional<Piece> findPieceOn(Position position) throws SQLException;

    Map<Position, Piece> findAllPieces() throws SQLException;

    void removePieceOn(Position position) throws SQLException;
}
