package chess.domain.piece;

import chess.domain.position.Position;

public interface Chessmen {

    void move(Position position);

    void kill(Chessmen targetPiece);

    boolean isPawn();

    boolean isKing();

    double score();

    String display();

    Position getPosition();

    Color getColor();
}
