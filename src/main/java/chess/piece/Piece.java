package chess.piece;

import chess.Direction;
import chess.MoveCommand;
import chess.Position;

public interface Piece {

    Name getName();

    Color getColor();

    double getScore();

    boolean isSameTeam(Piece piece);

    boolean canMove(MoveCommand command);

    Direction getDirection(Position from, Position to);

    boolean isPawn();

    boolean isKnight();

    boolean isKing();
}
