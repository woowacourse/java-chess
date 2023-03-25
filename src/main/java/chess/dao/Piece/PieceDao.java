package chess.dao.Piece;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public interface PieceDao {

    void insert(long chessGameId, Position position, Piece piece);
    void delete(long chessGameId, Position position);
    Map<Position, Piece> findAllById(long chessGameId);
    void deleteAll();
}
