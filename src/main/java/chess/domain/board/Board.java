package chess.domain.board;

import chess.domain.direction.Direction;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Place;
import chess.exception.PieceMessage;
import java.util.List;
import java.util.Map;

public class Board {

    private static final int ONE_OF_KING_IS_DEAD = 1;
    private static final int PAWNS_ARE_BEING_SAME_COLUMN = 2;

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

    public double getScoreOfLowerTeam() {
        board.keySet().stream()
                .filter(this::isLowerTeamOfPawn)
                .map(Position::getCol)
                .distinct()
                .forEach(column -> calculatePawnScoreOfLowerTeam(Column.fromByInput(column)));

        return board.values().stream()
                .filter(Piece::isNameLowerCase)
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private void calculatePawnScoreOfLowerTeam(final Column column) {
        List<Position> positions = getPositionsByColumn(column);

        int count = (int) positions.stream()
                .filter(this::isLowerTeamOfPawn)
                .count();

        if (count >= PAWNS_ARE_BEING_SAME_COLUMN) {
            positions.stream()
                    .filter(position -> findPieceFromPosition(position).isPawn())
                    .forEach(position -> findPieceFromPosition(position).updateScoreByOtherPawnsBeingWithSameColumn());
        }
    }

    private List<Position> getPositionsByColumn(final Column column) {
        List<Position> positions = Position.getAllPositionsByColumn(column);
        return positions;
    }

    private boolean isLowerTeamOfPawn(final Position position) {
        return board.get(position).isNameLowerCase() && board.get(position).isPawn();
    }

    public double getScoreOfUpperTeam() {
        board.keySet().stream()
                .filter(this::isUpperTeamOfPawn)
                .map(Position::getCol)
                .distinct()
                .forEach(column -> calculatePawnScoreOfUpperTeam(Column.fromByInput(column)));

        return board.values().stream()
                .filter(Piece::isNameUpperCase)
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private void calculatePawnScoreOfUpperTeam(final Column column) {
        List<Position> positions = Position.getAllPositionsByColumn(column);

        int count = (int) positions.stream()
                .filter(this::isUpperTeamOfPawn)
                .count();

        if (count >= PAWNS_ARE_BEING_SAME_COLUMN) {
            positions.stream()
                    .filter(position -> findPieceFromPosition(position).isPawn())
                    .forEach(position -> findPieceFromPosition(position).updateScoreByOtherPawnsBeingWithSameColumn());
        }
    }

    private boolean isUpperTeamOfPawn(final Position position) {
        return board.get(position).isNameUpperCase() && board.get(position).isPawn();
    }

    public boolean isKingDead() {
        int countOfKing = (int) board.values().stream()
                .filter(piece -> piece.isKing())
                .count();

        return countOfKing == ONE_OF_KING_IS_DEAD;
    }

    public boolean isUpperTeamWin() {
        Piece kingOfWinner = board.values().stream()
                .filter(piece -> piece.isKing())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return kingOfWinner.isNameUpperCase();
    }
}
