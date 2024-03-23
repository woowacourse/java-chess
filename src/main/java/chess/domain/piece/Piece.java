package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;

public interface Piece {

    void validateMovable(Coordinate source, Coordinate target, Board board);

    PieceType getType();

    Team getTeam();

    boolean isSameTeam(Piece other);

    boolean isEnemy(Piece other);
}
