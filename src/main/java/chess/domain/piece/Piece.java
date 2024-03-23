package chess.domain.piece;

import java.util.List;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;

public interface Piece {

    List<Coordinate> findMovablePath(Coordinate start, Coordinate destination);

    void validateMovable(Coordinate start, Coordinate target, Board board);

    PieceType getType();

    Team getTeam();
}
