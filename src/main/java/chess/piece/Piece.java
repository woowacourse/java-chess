package chess.piece;

import chess.MoveCommand;

public interface Piece {

    Name getName();

    Color getColor();

    double getScore();

    boolean isSameTeam(Piece piece);

    boolean canMove(MoveCommand command);

    boolean isPawn();

    boolean isKnight();

    boolean isKing();
}
