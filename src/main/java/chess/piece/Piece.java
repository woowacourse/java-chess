package chess.piece;

import chess.Color;
import chess.Position;
import java.util.List;
import java.util.Optional;

public interface Piece {
    void move(Position wantedPosition, List<Piece> wantedPositionExistPiece);
    Position getPosition();
    boolean isOpposite(Piece piece);
    Color getColor();
}
