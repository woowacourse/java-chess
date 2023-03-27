package domain.path.pathValidtor;

import domain.board.piece.PieceType;
import domain.path.pathValidtor.validateTarget.BlockedValidation;
import domain.path.pathValidtor.validateTarget.DetinationValidation;
import domain.path.pathValidtor.validateTarget.DirectionValidation;
import domain.path.pathValidtor.validateTarget.MoveOnceValidation;
import domain.path.pathValidtor.validateTarget.PawnMoveValidation;
import java.util.List;

public final class PathValidatorFactory {

    private PathValidatorFactory() {
    }

    public static PathValidator findByPieceType(PieceType pieceType) {
        if (pieceType == PieceType.ROOK) {
            return rookPathValidator();
        }
        if (pieceType == PieceType.BISHOP) {
            return bishopPathValidator();
        }
        if (pieceType == PieceType.QUEEN) {
            return queenPathValidator();
        }
        if (pieceType == PieceType.KING) {
            return kingPathValidator();
        }
        if (pieceType == PieceType.KNIGHT) {
            return knightPathValidator();
        }
        if (pieceType == PieceType.PAWN) {
            return pawnPathValidator();
        }
        return emptyPiecePathValidator();
    }

    public static PathValidator rookPathValidator() {
        final DirectionValidation directionValidation = DirectionValidation.of(
            PieceType.ROOK.getPossibleDirections()
        );
        return new PathValidator(
            List.of(
                directionValidation,
                new BlockedValidation(),
                new DetinationValidation()
            )
        );
    }

    public static PathValidator bishopPathValidator() {
        final DirectionValidation directionValidation = DirectionValidation.of(
            PieceType.BISHOP.getPossibleDirections()
        );

        return new PathValidator(
            List.of(
                directionValidation,
                new BlockedValidation(),
                new DetinationValidation()
            )
        );
    }

    public static PathValidator queenPathValidator() {
        final DirectionValidation directionValidation = DirectionValidation.of(
            PieceType.QUEEN.getPossibleDirections()
        );

        return new PathValidator(
            List.of(
                directionValidation,
                new BlockedValidation(),
                new DetinationValidation()
            )
        );
    }

    public static PathValidator kingPathValidator() {
        final DirectionValidation directionValidation = DirectionValidation.of(
            PieceType.KING.getPossibleDirections()
        );

        return new PathValidator(
            List.of(
                directionValidation,
                new MoveOnceValidation(),
                new DetinationValidation()
            )
        );
    }

    public static PathValidator knightPathValidator() {
        final DirectionValidation directionValidation = DirectionValidation.of(
            PieceType.KNIGHT.getPossibleDirections()
        );

        return new PathValidator(
            List.of(
                directionValidation,
                new MoveOnceValidation(),
                new DetinationValidation()
            )
        );
    }

    public static PathValidator pawnPathValidator() {
        final DirectionValidation directionValidation = DirectionValidation.of(
            PieceType.PAWN.getPossibleDirections()
        );
        return new PathValidator(
            List.of(
                directionValidation,
                new PawnMoveValidation()
            )
        );
    }

    public static PathValidator emptyPiecePathValidator() {
        return new PathValidator(
            List.of(
                DirectionValidation.of(PieceType.EMPTY.getPossibleDirections())
            )
        );
    }
}
