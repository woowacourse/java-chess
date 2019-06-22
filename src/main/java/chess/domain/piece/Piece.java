package chess.domain.piece;

import chess.domain.board.Position;

import java.util.List;

public interface Piece {
    boolean isSameTeam(Piece piece);
    List<Position> makePossiblePositions(Position source, PositionChecker positionChecker);
}