package chess.domain.piece;

import java.util.List;
import chess.domain.Coordinate;

public interface Piece {

    List<Coordinate> findAllPossibleCoordinate(Coordinate start);

    PieceType getType();

    Team getTeam();
}
