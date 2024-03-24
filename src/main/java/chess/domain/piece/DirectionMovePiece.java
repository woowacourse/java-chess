package chess.domain.piece;

import chess.domain.board.Coordinate;
import chess.domain.board.Direction;
import java.util.List;
import java.util.Set;

abstract class DirectionMovePiece extends AbstractPiece {

    public DirectionMovePiece(PieceType type, Team team) {
        super(type, team);
    }

    abstract Set<Direction> legalDirections();

    @Override
    public List<Coordinate> legalNextCoordinates(final Coordinate now, final Coordinate destination) {
        return legalDirections().stream()
                .map(direction -> direction.createPath(now))
                .filter(coordinates -> coordinates.contains(destination))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("해당 기물은 목적지 좌표에 갈 수 없습니다."));
    }
}
