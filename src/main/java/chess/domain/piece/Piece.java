package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.position.Position;
import java.util.List;

public interface Piece {

    List<Position> route(Position from, Position to, Piece targetPiece, Side side);

    boolean isBlank();

    boolean isPawn();

    boolean isKing();

    double score();

    void moved();

    boolean isSideEqualTo(Side side);

    boolean diagonal(Position from, Position to);

    boolean forward(Position from, Position to);

    String getInitial();
}
