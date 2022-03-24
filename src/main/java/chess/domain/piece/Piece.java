package chess.domain.piece;

import chess.domain.position.Position;

public interface Piece {

    void move(Position position);

    void kill(Piece targetPiece);

    boolean isPawn();

    boolean isKing();

    double score();

    boolean hasColorOf(Color color);

    boolean isAt(Position position);

    boolean isAtDisplayColumnIdxOf(int colIdx);

    boolean isAtDisplayRowIdxOf(int rowIdx);

    Position position();

    String display();
}
