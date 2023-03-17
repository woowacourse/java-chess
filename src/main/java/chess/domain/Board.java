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

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(squares);
    }

    public void move(Position source, Position target) {
        validate(source, target);
        squares.put(target, squares.get(source));
        squares.put(source, Empty.INSTANCE);
    }

    private void validate(Position source, Position target) {
        validateDuplicate(source, target);
        Piece sourcePiece = squares.get(source);
        Piece targetPiece = squares.get(target);
        validateEmptySquare(sourcePiece);
        validateSameTeam(sourcePiece, targetPiece);
        validateMovement(source, target);
        validatePieceRole(source, target);
    }

    private void validateDuplicate(Position source, Position target) {
        if (Objects.equals(source, target)) {
            throw new IllegalArgumentException("[ERROR] 같은 위치로 움직일 수 없습니다.");
        }
    }

    private void validateEmptySquare(Piece sourcePiece) {
        if (sourcePiece.isRoleOf(Role.EMPTY)) {
            throw new IllegalArgumentException("[ERROR] 빈 칸은 움직일 수 없습니다.");
        }
    }

    private void validateSameTeam(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameTeamWith(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 목적지에 아군 말이 존재합니다.");
        }
    }

    private void validateMovement(Position source, Position target) {
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
        if (validatePawnAttack(source, target) || validatePawnForward(source, target)) {
            throw new IllegalArgumentException("[ERROR] 해당 목적지로 이동할 수 없습니다.");
        }
    }

    private boolean validatePawnAttack(Position source, Position target) {
        Piece targetPiece = squares.get(target);
        return !source.isSameXAs(target) && targetPiece.isRoleOf(Role.EMPTY);
    }

    private boolean validatePawnForward(Position source, Position target) {
        Piece targetPiece = squares.get(target);
        return source.isSameXAs(target) && !targetPiece.isRoleOf(Role.EMPTY);
    }
}
