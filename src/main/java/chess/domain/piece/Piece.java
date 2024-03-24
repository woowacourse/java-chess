package chess.domain.piece;

import chess.domain.board.Coordinate;
import java.util.List;

public interface Piece {

    List<Coordinate> legalNextCoordinates(Coordinate now, Coordinate destination);

    PieceType getType();

    Team getTeam();

    boolean isSameTeam(Piece piece);

    boolean isNotSameTeam(Piece piece);
}
