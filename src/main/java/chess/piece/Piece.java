package chess.piece;

import chess.game.MoveCommand;
import chess.piece.detail.Color;
import chess.piece.detail.Direction;
import chess.piece.detail.Name;
import chess.position.Position;

public interface Piece {

    boolean isSameTeam(Piece piece);

    boolean canMove(MoveCommand command);

    Direction getDirection(Position from, Position to);

    boolean isPawn();

    boolean isKnight();

    boolean isKing();

    Name getName();

    Color getColor();

    double getScore();
}
