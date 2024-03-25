package chess.domain.piece;

import chess.domain.board.Coordinate;
import java.util.List;
import java.util.Map;

public interface Piece {

    List<Coordinate> legalNextCoordinates(Coordinate now, Coordinate destination);

    boolean canMove(Coordinate now, Coordinate destination, Map<Coordinate, Piece> boardInformation);

    Piece updateAfterMove();

    PieceType getType();

    Team getTeam();

    boolean isEmpty();

    boolean isNotEmpty();

    boolean isSameTeam(Piece piece);

    boolean isNotSameTeam(Piece piece);
}
