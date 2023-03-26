package chess.domain;

import static java.util.stream.Collectors.*;

import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class Board {
    private static final String DUPLICATE_POSITION_EXCEPTION_MESSAGE = "[ERROR] 같은 위치로 움직일 수 없습니다.";
    private static final String SAME_TEAM_EXCEPTION_MESSAGE = "[ERROR] 목적지에 아군 말이 존재합니다.";
    private static final String MOVE_FAIL_EXCEPTION_MESSAGE = "[ERROR] 해당 목적지로 이동할 수 없습니다.";
    private static final String ATTACK_KING_EXCEPTION_MESSAGE = "[ERROR] 킹을 직접 공격할 수 없습니다.";

    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = squares;
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
        validateSameTeam(sourcePiece, targetPiece);
        validateAttackKing(targetPiece);
        validateMovement(source, target);
    }

    private void validateAttackKing(Piece piece) {
        if (piece.isRoleOf(Role.KING)) {
            throw new IllegalArgumentException(ATTACK_KING_EXCEPTION_MESSAGE);
        }
    }

    private void validateDuplicate(Position source, Position target) {
        if (Objects.equals(source, target)) {
            throw new IllegalArgumentException(DUPLICATE_POSITION_EXCEPTION_MESSAGE);
        }
    }

    private void validateSameTeam(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameTeamWith(targetPiece)) {
            throw new IllegalArgumentException(SAME_TEAM_EXCEPTION_MESSAGE);
        }
    }

    private void validateMovement(Position source, Position target) {
        if (!canMove(source, target)) {
            throw new IllegalArgumentException(MOVE_FAIL_EXCEPTION_MESSAGE);
        }
    }

    private boolean canMove(Position source, Position target) {
        Piece sourcePiece = squares.get(source);
        if (sourcePiece.isRoleOf(Role.KNIGHT)) {
            return sourcePiece.canMove(source, target);
        }
        if (sourcePiece.isRoleOf(Role.PAWN)) {
            return sourcePiece.canMove(source, target) && canPawnMove(source, target);
        }
        return sourcePiece.canMove(source, target) && isEmptyRoute(source, target);
    }

    private boolean canPawnMove(Position source, Position target) {
        Piece targetPiece = squares.get(target);
        if (targetPiece.isRoleOf(Role.EMPTY)) {
            return source.isSameXAs(target);
        }
        return !source.isSameXAs(target);
    }

    private boolean isEmptyRoute(Position source, Position target) {
        List<Position> routes = source.getBetweenPositions(target);
        return routes.stream()
                .map(squares::get)
                .allMatch(piece -> piece.isRoleOf(Role.EMPTY));
    }

    public Map<Position, Piece> getTeamSquares(Team team) {
        return squares.entrySet().stream()
                .filter(entry -> entry.getValue().isSameTeam(team))
                .collect(toMap(Entry::getKey, Entry::getValue));
    }

    public Map<Integer, Long> getPawnCountByX(Team team) {
        return squares.entrySet().stream()
                .filter(entry -> entry.getValue().isSameTeam(team))
                .filter(entry -> entry.getValue().isRoleOf(Role.PAWN))
                .collect(groupingBy(entry -> entry.getKey().getX(), counting()));
    }

    public boolean isChecked(Team team) {
        Position kingPosition = getKingPosition(team);
        return isAnyMovableTo(team.opposite(), kingPosition);
    }

    private Position getKingPosition(Team team) {
        return squares.entrySet().stream()
                .filter(entry -> entry.getValue() == King.of(team))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 체스판에 킹이 존재하지 않습니다."))
                .getKey();
    }

    private boolean isAnyMovableTo(Team team, Position target) {
        return squares.entrySet().stream()
                .filter(entry -> entry.getValue().isSameTeam(team))
                .anyMatch(entry -> canMove(entry.getKey(), target));
    }

    public Team getPieceTeam(Position target) {
        return squares.get(target).getTeam();
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(squares);
    }
}
