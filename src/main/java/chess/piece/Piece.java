package chess.piece;

import chess.game.Position;

public interface Piece {

    boolean isSameTeam(Piece piece);

    boolean canMove(Position from, Position to);

    boolean isPawn();

    boolean isKnight();

    boolean isKing();

    boolean isEqualColor(Color color);

    Name getName();

    Color getColor();

    double getScore();
}
