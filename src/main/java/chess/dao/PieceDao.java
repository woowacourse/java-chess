package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Optional;

public interface PieceDao {

    void save(long gameId, Position position, Piece piece);

    List<Piece> findAll(long gameId);

    Optional<Piece> find(long gameId, Position position);

    void updatePosition(long gameId, Position start, Position target);

    void delete(long gameId, Position position);

    void deleteAll(long gameId);
}
