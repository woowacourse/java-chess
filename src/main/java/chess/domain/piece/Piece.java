package chess.domain.piece;

import java.util.List;
import chess.domain.board.Coordinate;

public interface Piece {

    List<Coordinate> findMovablePath(Coordinate start, Coordinate destination);

    PieceType getType();

    Team getTeam();
}
