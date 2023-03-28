package dao;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface PieceDao {

    void createPiece(final Position position, final Piece piece, final String gameId);

    Map<Position, Piece> read(final String gameId);

    void update(final Position from, final Position to, final String gameId);

    void deleteAll(final String gameId);
}
