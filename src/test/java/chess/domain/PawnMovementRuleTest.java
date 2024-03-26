package chess.domain;

import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnMovementRuleTest {

    @DisplayName("폰이 직선으로 움직여서 목표 위치까지 도달할 수 있는지 반환한다.")
    @Nested
    class canReachTargetFromInitialPosition {

        @DisplayName("시작 위치가 아니면서 한번 움직여서 도달할 수 있으면 true를 반환한다..")
        @Test
        void isNotPawnStartPosition() {
            PawnMovementRule pawnMovementRule = new PawnMovementRule();
            Position source = Position.of(File.A, Rank.THREE);
            Position target = Position.of(File.A, Rank.FOUR);
            Direction direction = source.calculateDirection(target);

            boolean result = pawnMovementRule.canReachTargetWhenMoveForward(source, target, direction);

            assertThat(result).isTrue();
        }

        @DisplayName("시작 위치에서 한 번 이동해서 목표 위치에 도달하면 true를 반환한다.")
        @Test
        void reachTargetWhenMoveOnce() {
            PawnMovementRule pawnMovementRule = new PawnMovementRule();
            Position source = Position.of(File.A, Rank.TWO);
            Position target = Position.of(File.A, Rank.THREE);
            Direction direction = source.calculateDirection(target);

            boolean result = pawnMovementRule.canReachTargetWhenMoveForward(source, target, direction);

            assertThat(result).isTrue();
        }

        @DisplayName("시작 위치에서 두 번 이동해서 목표 위치에 도달하면 true를 반환한다.")
        @Test
        void reachTargetWhenMoveTwice() {
            PawnMovementRule pawnMovementRule = new PawnMovementRule();
            Position source = Position.of(File.A, Rank.TWO);
            Position target = Position.of(File.A, Rank.FOUR);
            Direction direction = source.calculateDirection(target);

            boolean result = pawnMovementRule.canReachTargetWhenMoveForward(source, target, direction);

            assertThat(result).isTrue();
        }

        @DisplayName("시작 위치에서 두 번 이동했음에도 목표 위치에 도달하지 못하면 false를 반환한다.")
        @Test
        void canNotReachTargetEvenMovedTwice() {
            PawnMovementRule pawnMovementRule = new PawnMovementRule();
            Position source = Position.of(File.A, Rank.TWO);
            Position target = Position.of(File.A, Rank.FIVE);
            Direction direction = source.calculateDirection(target);

            boolean result = pawnMovementRule.canReachTargetWhenMoveForward(source, target, direction);

            assertThat(result).isFalse();
        }
    }

    @DisplayName("폰이 대각선으로 움직일 수 있는지 반환한다.")
    @Nested
    class canMoveTowardDiagonal {

        @DisplayName("대각선 방향이 아니면 false를 반환한다.")
        @Test
        void canNotMoveDiagonalByWrongDirection() {
            PawnMovementRule pawnMovementRule = new PawnMovementRule();
            Piece sourcePiece = Pawn.of(Color.BLACK);
            Piece targetPiece = Pawn.of(Color.WHITE);

            boolean result = pawnMovementRule.canMoveTowardDiagonal(sourcePiece, targetPiece, Direction.KNIGHT_LEFT_UP);

            assertThat(result).isFalse();
        }

        @DisplayName("대각선에 적이 없으면 false를 반환한다.")
        @Test
        void canNotMoveDiagonalByNoEnemy() {
            PawnMovementRule pawnMovementRule = new PawnMovementRule();
            Piece sourcePiece = Pawn.of(Color.BLACK);
            Piece targetPiece = EmptyPiece.of();

            boolean result = pawnMovementRule.canMoveTowardDiagonal(sourcePiece, targetPiece, Direction.LEFT_UP);

            assertThat(result).isFalse();
        }

        @DisplayName("방향이 대각선이고, 적이 있으면 true를 반환한다.")
        @Test
        void canMoveDiagonal() {
            PawnMovementRule pawnMovementRule = new PawnMovementRule();
            Piece sourcePiece = Pawn.of(Color.BLACK);
            Piece targetPiece = Pawn.of(Color.WHITE);

            boolean result = pawnMovementRule.canMoveTowardDiagonal(sourcePiece, targetPiece, Direction.LEFT_UP);

            assertThat(result).isTrue();
        }
    }
}
