package chess.domain.board;

import chess.domain.direction.Direction;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Empty;
import chess.domain.pieces.Team;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Piece findPiece(final Position position) {
        return board.get(position);
    }

    public void switchPosition(final Position source, final Position destination) {
        validateMove(source, destination);
        board.replace(destination, findPiece(source));
        board.replace(source, new Empty(Team.EMPTY));
    }

    private void validateMove(final Position source, final Position destination) {
        Piece piece = findPiece(source);
        validateMoveSamePosition(source, destination);
        if (!(piece instanceof Knight)) {
            validateObstacle(source, destination);
        }
        piece.canMove(source, destination);
        validatePawnMove(piece, source, destination);
        validateMoveMyTeam(source, destination);
    }

    private void validateMoveSamePosition(final Position source, final Position destination) {
        if (source.equals(destination)) {
            throw new IllegalArgumentException("같은 위치로 움직일 수 없습니다.");
        }
    }

    private void validateObstacle(final Position source, final Position destination) {
        List<Position> routes = Direction.getRoute(source, destination);

        boolean isObstacleExist = routes.stream()
                .map(this::findPiece)
                .anyMatch(piece -> !(piece instanceof Empty));

        if (isObstacleExist) {
            throw new IllegalArgumentException("장애물이 존재합니다.");
        }
    }

    private void validatePawnMove(final Piece piece, final Position source, final Position destination) {
        if (!(piece instanceof Pawn)) {
            return;
        }

        if (piece.isWhiteTeam()) {
            validateLowercasePawnMove(source, destination);
            return;
        }

        validateUppercasePawnMove(source, destination);
    }

    private void validateLowercasePawnMove(final Position source, final Position destination) {
        if (Direction.isLowerPawnDiagonal(source, destination)) {
            validateLowercasePawnAttack(destination);
            return;
        }
        validateLowercasePawnMoveForward(destination);
    }

    private void validateLowercasePawnAttack(final Position end) {
        Piece upperEnemy = findPiece(end);
        if (upperEnemy.isWhiteTeam() || upperEnemy instanceof Empty) {
            throw new IllegalArgumentException("폰의 잘못된 이동입니다.");
        }
    }

    private void validateLowercasePawnMoveForward(final Position end) {
        Piece destination = findPiece(end);
        if (!(destination instanceof Empty)) {
            throw new IllegalArgumentException("폰의 잘못된 이동입니다.");
        }
    }

    private void validateUppercasePawnMove(final Position start, final Position end) {
        if (Direction.isUpperPawnDiagonal(start, end)) {
            validateUppercasePawnAttack(end);
            return;
        }
        validateUppercasePawnMoveForward(end);
    }

    private void validateMoveMyTeam(final Position start, final Position end) {
        Piece selectedPiece = findPiece(start);
        Piece destinationPiece = findPiece(end);
        if (isSameTeam(selectedPiece, destinationPiece) && !destinationPiece.isEmpty()) {
            throw new IllegalArgumentException("우리팀 말에게 이동할 수 없습니다.");
        }
    }

    private boolean isSameTeam(final Piece selectedPiece, final Piece destinationPiece) {
        return selectedPiece.isWhiteTeam() == destinationPiece.isWhiteTeam()
            || selectedPiece.isBlackTeam() == destinationPiece.isBlackTeam();
    }

    private void validateUppercasePawnAttack(final Position end) {
        Piece lowerEnemy = findPiece(end);
        if (lowerEnemy.isBlackTeam() || lowerEnemy instanceof Empty) {
            throw new IllegalArgumentException("폰의 잘못된 이동입니다.");
        }
    }

    private void validateUppercasePawnMoveForward(final Position end) {
        Piece destination = findPiece(end);
        if (!(destination instanceof Empty)) {
            throw new IllegalArgumentException("폰의 잘못된 이동입니다.");
        }
    }
}
