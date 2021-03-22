package chess.domain.piece;

import chess.domain.board.Board;

public interface ChessPiece {
    boolean isKing();

    boolean isPawn();

    int getRow();

    int getColumn();

    boolean isSamePosition(final Position position);

    void move(final Position target, final Board board);

    boolean isSameColor(final ChessPiece piece);

    Position getPosition();

    String getNotation();

    double getScore();
}
