package chess.domain.piece;

import java.util.List;

import chess.domain.Position;

public interface Piece {

    Piece move();

    List<Position> calculateRouteToMove(final Position source, final Position target);

    List<Position> calculateRouteToAttack(final Position source, final Position target);

    boolean isPawn();

    boolean isKing();

    String getPieceName();

    double getPieceScore();
}