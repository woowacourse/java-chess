package domain.pieces;

import static domain.team.Team.NONE;

import domain.move.PieceDirectionType;
import domain.pieces.exceptions.IsNotMovableException;
import domain.point.Point;
import domain.point.MovePoint;
import domain.team.Team;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pieces {

    private Map<Point, Piece> pieces;

    private Pieces(Map<Point, Piece> pieces) {
        this.pieces = new LinkedHashMap<>(pieces);
    }

    public static Pieces of(Map<Point, Piece> pieces) {
        return new Pieces(pieces);
    }

    public Map<Point, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    public Piece getPiece(Point point) {
        return pieces.get(point);
    }

    public boolean isExistPiece(Point point) {
        return getPiece(point).team != NONE;
    }

    public void move(Team turn, MovePoint movePoint) {
        validateMovable(turn, movePoint);
        pieces.put(movePoint.getTo(), pieces.get(movePoint.getFrom()));
        pieces.put(movePoint.getFrom(), new Empty(NONE));
    }

    private void validateMovable(Team turn, MovePoint movePoint) {
        validateExistPiece(movePoint);
        validateCorrectTurn(turn, movePoint);
        validateSameTeamToTarget(movePoint);
        validatePieceMovable(movePoint);
    }

    private void validateExistPiece(MovePoint movePoint) {
        if (!isExistPiece(movePoint.getFrom())) {
            throw new IsNotMovableException(movePoint.getFrom() + "에 움직일 수 있는 말이 없습니다.");
        }
    }

    private void validateCorrectTurn(Team turn, MovePoint movePoint) {
        if (!getPiece(movePoint.getFrom()).isSameTeam(turn)) {
            throw new IsNotMovableException(turn.toString() + "차례입니다.");
        }
    }

    private void validateSameTeamToTarget(MovePoint movePoint) {
        if (isSameTeamToTarget(movePoint)) {
            throw new IsNotMovableException("움직이려는 곳에 같은 색 말이 있습니다.");
        }
    }

    private boolean isSameTeamToTarget(MovePoint movePoint) {
        return movePoint.isSameTeam(pieces);
    }

    private void validatePieceMovable(MovePoint movePoint) {
        boolean isMovable = PieceDirectionType.find(pieces, movePoint.getFrom()).stream()
            .anyMatch(direction -> pieces.get(movePoint.getFrom())
                .isMovable(direction, pieces, movePoint));
        if (!isMovable) {
            throw new IsNotMovableException(
                pieces.get(movePoint.getFrom()).toString() + "은 그 장소로 못 움직입니다.");
        }
    }
}
