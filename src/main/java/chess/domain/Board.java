package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Board {

    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public void move(Position source, Position target) {
        checkMoveCondition(source, target);
        squares.put(target, squares.get(source));
        squares.put(source, Empty.INSTANCE);

    }

    public Piece findByPosition(Position position) {
        return squares.get(position);
    }

    private void checkMoveCondition(Position source, Position target) {
        isSamePosition(source, target);
        Piece sourcePiece = squares.get(source);
        Piece targetPiece = squares.get(target);
        isEmptyPiece(sourcePiece);
        isSameTeamPieces(sourcePiece, targetPiece);
        isWrongMoveCondition(source, target);
        validatePieceRole(source, target);
    }

    private void isSamePosition(Position source, Position target) {
        if (Objects.equals(source, target)) {
            throw new IllegalArgumentException("[ERROR] 같은 위치로 움직일 수 없습니다.");
        }
    }

    private void isEmptyPiece(Piece sourcePiece) {
        if (sourcePiece.isRoleOf(Role.EMPTY)) {
            throw new IllegalArgumentException("[ERROR] 빈 칸은 움직일 수 없습니다.");
        }
    }

    private void isSameTeamPieces(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameTeamWith(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 목적지에 아군 말이 존재합니다.");
        }
    }

    private void isWrongMoveCondition(Position source, Position target) {
        Piece sourcePiece = squares.get(source);
        if (!sourcePiece.canMove(source, target)) {
            throw new IllegalArgumentException("[ERROR] 해당 목적지로 이동할 수 없습니다.");
        }
    }

    private void validatePieceRole(Position source, Position target) {
        Piece sourcePiece = squares.get(source);
        if (!sourcePiece.isRoleOf(Role.KNIGHT)) {
            validateCollision(source, target);
        }
        if (sourcePiece.isRoleOf(Role.PAWN)) {
            validatePawnMove(source, target);
        }
    }

    private void validateCollision(Position source, Position target) {
        List<Position> routes = source.getBetweenPositions(target);
        boolean isEmptyRoute = routes.stream()
                .map(squares::get)
                .allMatch(piece -> piece.isRoleOf(Role.EMPTY));
        if (!isEmptyRoute) {
            throw new IllegalArgumentException("[ERROR] 해당 경로에 말이 있습니다.");
        }
    }

    private void validatePawnMove(Position source, Position target) {
        Piece targetPiece = squares.get(target);
        if (source.isSameXAs(target) && !targetPiece.isRoleOf(Role.EMPTY)) {
            throw new IllegalArgumentException("[ERROR] 해당 목적지로 이동할 수 없습니다.");
        }
        if (!source.isSameXAs(target) && targetPiece.isRoleOf(Role.EMPTY)) {
            throw new IllegalArgumentException("[ERROR] 해당 목적지로 이동할 수 없습니다.");
        }
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(squares);
    }
}
