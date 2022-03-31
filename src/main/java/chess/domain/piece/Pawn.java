package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.List;

public class Pawn extends Piece {

    private static final Row BLACK_FIRST_ROW = Row.RANK_7;
    private static final Row WHITE_FIRST_ROW = Row.RANK_2;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    protected List<Movement> chooseMovements() {
        if (color.isBlack()) {
            return List.of(
                    Movement.SOUTH_1STEP,
                    Movement.SOUTH_2STEP,
                    Movement.SOUTH_EAST_1STEP,
                    Movement.SOUTH_WEST_1STEP
            );
        }
        if (color.isWhite()) {
            return List.of(
                    Movement.NORTH_1STEP,
                    Movement.NORTH_2STEP,
                    Movement.NORTH_EAST_1STEP,
                    Movement.NORTH_WEST_1STEP
            );
        }
        return List.of();
    }

    @Override
    protected String baseSignature() {
        return "p";
    }

    @Override
    public boolean isCorrectMovement(Position source, Position target, Piece targetPiece) {
        int columnDifference = source.calculateColumnDifferenceTo(target);
        int rowDifference = source.calculateRowDifferenceTo(target);
        Movement movement = Movement.find(columnDifference, rowDifference);

        if (!movements.contains(movement)) {
            return false;
        }
        if (movement.isDiagonal()) {
            return isEnemyColorPiece(targetPiece);
        }
        if (movement.is2Step()) {
            return targetPiece.isBlank() && isCorrect2StepMovement(source);
        }
        return targetPiece.isBlank();
    }

    private boolean isCorrect2StepMovement(Position source) {
        if (color.isBlack()) {
            return source.isSameRow(BLACK_FIRST_ROW);
        }
        if (color.isWhite()) {
            return source.isSameRow(WHITE_FIRST_ROW);
        }
        return false;
    }

    @Override
    public boolean canJumpOverPieces() {
        return false;
    }

    @Override
    public double score() {
        return 1;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}

