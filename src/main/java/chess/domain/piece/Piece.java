package chess.domain.piece;

import chess.domain.position.Position;

public interface Piece {

    void move(Position position);

    void kill(Piece targetPiece);

    boolean isPawn();

    boolean isKing();

    double score();

    String display();

    Position getPosition();

    Color getColor();
}
