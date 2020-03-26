package domain.pieces;

import static domain.team.Team.BLACK;
import static domain.team.Team.NONE;
import static domain.team.Team.WHITE;

import domain.pieces.exceptions.IsNotMovableException;
import domain.point.Point;
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
        return getPiece(point).isSameTeam(BLACK) || getPiece(point).isSameTeam(WHITE);
    }

    public void move(Team turn, Point from, Point to) {
        validateExistPiece(from);
        validateCorrectTurn(turn, from);
        validatePieceMovable(from, to);
        pieces.put(to, pieces.get(from));
        pieces.put(from, new Empty(NONE));
    }

    private void validateExistPiece(Point from) {
        if (!isExistPiece(from)) {
            throw new IsNotMovableException(from.toString() + "에 움직일 수 있는 말이 없습니다.");
        }
    }

    private void validateCorrectTurn(Team turn, Point from) {
        if(!getPiece(from).isSameTeam(turn)) {
            throw new IsNotMovableException(turn.toString() + "차례입니다.");
        }
    }

    private void validatePieceMovable(Point from, Point to) {
        if (!pieces.get(from).isMovable(pieces, from, to)) {
            throw new IsNotMovableException(pieces.get(from).toString() + "은 그 장소로 못 움직입니다.");
        }
    }
}
