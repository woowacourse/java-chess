package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.position.Position;
import java.util.List;

public interface Piece {

    List<Position> route(Position from, Position to, Piece targetPiece, Side side);

    void moved();

    double score();

    boolean isSideEqualTo(Side side);

    boolean isBlank();

    boolean isPawn();

    boolean isKing();

    String getInitial();
}
