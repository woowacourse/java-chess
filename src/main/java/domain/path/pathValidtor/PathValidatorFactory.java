package domain.path.pathValidtor;

import domain.path.direction.Direction;
import domain.path.pathValidtor.validateTarget.BlockedValidation;
import domain.path.pathValidtor.validateTarget.DetinationValidation;
import domain.path.pathValidtor.validateTarget.DirectionValidation;
import domain.path.pathValidtor.validateTarget.MoveOnceValidation;
import domain.path.pathValidtor.validateTarget.PawnMoveValidation;
import java.util.List;

public final class PathValidatorFactory {

    private PathValidatorFactory() {
    }

    public static PathValidator rookPathValidator() {
        final DirectionValidation directionValidation = DirectionValidation.of(Direction.rookDirections());
        return new PathValidator(
            List.of(
                directionValidation,
                new BlockedValidation(),
                new DetinationValidation()
            )
        );
    }

    public static PathValidator bishopPathValidator() {
        final DirectionValidation directionValidation = DirectionValidation.of(Direction.bishopDirections());
        return new PathValidator(
            List.of(
                directionValidation,
                new BlockedValidation(),
                new DetinationValidation()
            )
        );
    }

    public static PathValidator queenPathValidator() {
        final DirectionValidation directionValidation = DirectionValidation.of(Direction.queenDirections());

        return new PathValidator(
            List.of(
                directionValidation,
                new BlockedValidation(),
                new DetinationValidation()
            )
        );
    }

    public static PathValidator kingPathValidator() {
        final DirectionValidation directionValidation = DirectionValidation.of(Direction.kingDirections());
        return new PathValidator(
            List.of(
                directionValidation,
                new MoveOnceValidation(),
                new DetinationValidation()
            )
        );
    }

    public static PathValidator knightPathValidator() {
        final DirectionValidation directionValidation = DirectionValidation.of(Direction.knightDirections());
        return new PathValidator(
            List.of(
                directionValidation,
                new MoveOnceValidation(),
                new DetinationValidation()
            )
        );
    }

    public static PathValidator pawnPathValidator() {
        final DirectionValidation directionValidation = DirectionValidation.of(Direction.pawnDirections());
        return new PathValidator(
            List.of(
                directionValidation,
                new PawnMoveValidation()
            )
        );
    }

    public static PathValidator emptyPiecePathValidator() {
        return new PathValidator(List.of(DirectionValidation.of(Direction.emptyDirections())));
    }
}
