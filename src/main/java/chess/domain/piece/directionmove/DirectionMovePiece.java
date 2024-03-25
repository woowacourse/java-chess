package chess.domain.piece.directionmove;

import chess.domain.board.Coordinate;
import chess.domain.board.Direction;
import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

abstract class DirectionMovePiece extends AbstractPiece {

    DirectionMovePiece(PieceType type, Team team) {
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

    @Override
    public boolean canMove(final Coordinate now, final Coordinate destination,
                           final Map<Coordinate, Piece> boardInformation) {
        List<Coordinate> coordinates = new LinkedList<>(mapUntilEmpty(boardInformation));
        var endPoint = boardInformation.entrySet().stream()
                .filter(entry -> entry.getValue().isNotEmpty())
                .findFirst();
        if (endPoint.isPresent() && endPoint.get().getValue().isNotSameTeam(this)) {
            coordinates.add(endPoint.get().getKey());
        }
        return coordinates.contains(destination);
    }

    private List<Coordinate> mapUntilEmpty(final Map<Coordinate, Piece> boardInformation) {
        return boardInformation.entrySet().stream()
                .takeWhile(entry -> entry.getValue().isEmpty())
                .map(Entry::getKey)
                .toList();
    }

    @Override
    public Piece updateAfterMove() {
        return this;
    }
}
