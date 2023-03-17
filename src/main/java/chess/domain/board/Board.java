package chess.domain.board;

import chess.domain.direction.Direction;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Place;
import chess.exception.PieceMessage;
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

    public Piece findPieceFromPosition(final Position position) {
        return board.get(position);
    }

    public void switchPosition(final Position start, final Position end) {
        validateMove(start, end);
        board.replace(end, findPieceFromPosition(start));
        board.replace(start, new Place());
    }

    private void validateMove(final Position start, final Position end) {
        Piece piece = findPieceFromPosition(start);
        validateMoveSamePosition(start, end);
        if (!piece.isKnight()) {
            validateObstacle(start, end);
        }
        piece.canMove(start, end);
        validatePawnMove(piece, start, end);
        validateMoveMyTeam(start, end);
    }

    private void validateMoveSamePosition(final Position start, final Position end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException(PieceMessage.NOT_MOVE.getMessage());
        }
    }

    private void validateObstacle(final Position start, final Position end) {
        List<String> routes = Direction.getRoute(start, end);

        boolean isObstacleExist = routes.stream()
                .map(route -> findPieceFromPosition(Position.from(route)))
                .anyMatch(piece -> !(piece.isPlace()));

        if (isObstacleExist) {
            throw new IllegalArgumentException(PieceMessage.MOVE_NOT_TO_OBSTACLE.getMessage());
        }
    }

    private void validatePawnMove(final Piece piece, final Position start, final Position end) {
        if (!(piece.isPawn())) {
            return;
        }

        if (piece.isNameLowerCase()) {
            validateLowercasePawnMove(start, end);
            return;
        }

        validateUppercasePawnMove(start, end);
    }

    private void validateLowercasePawnMove(final Position start, final Position end) {
        if (Direction.isLowerPawnDiagonal(start, end)) {
            validateLowercasePawnAttack(end);
            return;
        }
        validateLowercasePawnMoveForward(end);
    }

    private void validateLowercasePawnAttack(final Position end) {
        Piece upperEnemy = findPieceFromPosition(end);
        if (upperEnemy.isNameLowerCase() || upperEnemy.isPlace()) {
            throw new IllegalArgumentException(PieceMessage.PAWN_INVALID_MOVE.getMessage());
        }
    }

    private void validateLowercasePawnMoveForward(final Position end) {
        Piece destination = findPieceFromPosition(end);
        if (!(destination.isPlace())) {
            throw new IllegalArgumentException(PieceMessage.PAWN_INVALID_MOVE.getMessage());
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
        Piece selectedPiece = findPieceFromPosition(start);
        Piece destinationPiece = findPieceFromPosition(end);
        if (isSameTeam(selectedPiece, destinationPiece) && !destinationPiece.isPlace()) {
            throw new IllegalArgumentException(PieceMessage.MOVE_NOT_TO_TEAM.getMessage());
        }
    }

    private boolean isSameTeam(final Piece selectedPiece, final Piece destinationPiece) {
        return selectedPiece.isNameLowerCase() == destinationPiece.isNameLowerCase();
    }

    private void validateUppercasePawnAttack(final Position end) {
        Piece lowerEnemy = findPieceFromPosition(end);
        if (lowerEnemy.isNameUpperCase() || lowerEnemy.isPlace()) {
            throw new IllegalArgumentException(PieceMessage.PAWN_INVALID_MOVE.getMessage());
        }
    }

    private void validateUppercasePawnMoveForward(final Position end) {
        Piece destination = findPieceFromPosition(end);
        if (!(destination.isPlace())) {
            throw new IllegalArgumentException(PieceMessage.PAWN_INVALID_MOVE.getMessage());
        }
    }
}
