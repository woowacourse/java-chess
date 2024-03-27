package chess.domain.piece;

import chess.domain.board.Coordinate;
import chess.domain.board.Pieces;

public interface Piece {

    void validateMovable(Coordinate source, Coordinate target, Pieces pieces);

    boolean isSameTeam(Team team);

    boolean isEnemy(Piece other);

    PieceType getType();

    Team getTeam();
}
