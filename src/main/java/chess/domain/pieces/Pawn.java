package chess.domain.pieces;

import chess.domain.board.Position;
import chess.domain.strategy.PawnDirection;
import java.util.List;

public class Pawn extends Piece {

    private static final List<PawnDirection> WHITE_PAWN_FIRST_MOVE_DIRECTION = List.of(
        PawnDirection.WHITE_PAWN_MOVE,
        PawnDirection.WHITE_PAWN_DOUBLE_MOVE,
        PawnDirection.WHITE_PAWN_RIGHT_DIAGONAL_ATTACK,
        PawnDirection.WHITE_PAWN_LEFT_DIAGONAL_ATTACK
    );

    private static final List<PawnDirection> WHITE_PAWN_MOVE_DIRECTION = List.of(
        PawnDirection.WHITE_PAWN_MOVE,
        PawnDirection.WHITE_PAWN_RIGHT_DIAGONAL_ATTACK,
        PawnDirection.WHITE_PAWN_LEFT_DIAGONAL_ATTACK
    );

    private static final List<PawnDirection> BLACK_PAWN_FIRST_MOVE_DIRECTION = List.of(
        PawnDirection.BLACK_PAWN_MOVE,
        PawnDirection.BLACK_PAWN_DOUBLE_MOVE,
        PawnDirection.BLACK_PAWN_RIGHT_DIAGONAL_ATTACK,
        PawnDirection.BLACK_PAWN_LEFT_DIAGONAL_ATTACK
    );

    private static final List<PawnDirection> BLACK_PAWN_MOVE_DIRECTION = List.of(
        PawnDirection.BLACK_PAWN_MOVE,
        PawnDirection.BLACK_PAWN_RIGHT_DIAGONAL_ATTACK,
        PawnDirection.BLACK_PAWN_LEFT_DIAGONAL_ATTACK
    );

    private boolean isFirstMove;

    public Pawn(final Team team) {
        super(team);
        this.isFirstMove = true;
    }

    @Override
    public void canMove(final Position source, final Position destination) {
        if (!this.isFirstMove) {
            validateAfterMove(source, destination);
        }
        if (this.isFirstMove) {
            this.isFirstMove = false;
            validateFirstMove(source, destination);
        }
    }

    private void validateFirstMove(final Position source, final Position destination) {
        if (isWhiteTeam()) {
            validateWhiteTeamFirstMoveDirection(source, destination);
        }
        if (isBlackTeam()) {
            validateBlackTeamFirstMoveDirection(source, destination);
        }
    }

    private void validateAfterMove(final Position source, final Position destination) {
        if (isWhiteTeam()) {
            validateWhiteTeamMoveDirectionAfterFirst(source, destination);
        }
        if (isBlackTeam()) {
            validateBlackTeamMoveDirectionAfterFirst(source, destination);
        }
    }

    private void validateWhiteTeamFirstMoveDirection(final Position source, final Position destination) {
        WHITE_PAWN_FIRST_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("White Pawn의 첫 움직임으로 올바르지 않습니다."));
    }

    private void validateWhiteTeamMoveDirectionAfterFirst(final Position source, final Position destination) {
        WHITE_PAWN_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("White Pawn의 움직임으로 올바르지 않습니다."));
    }

    private void validateBlackTeamFirstMoveDirection(final Position source, final Position destination) {
        BLACK_PAWN_FIRST_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Black Pawn의 첫 움직임으로 올바르지 않습니다."));
    }

    private void validateBlackTeamMoveDirectionAfterFirst(final Position source, final Position destination) {
        BLACK_PAWN_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Black Pawn의 움직임으로 올바르지 않습니다."));
    }
}
