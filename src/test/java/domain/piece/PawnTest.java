package domain.piece;

import static domain.position.File.C;
import static domain.position.File.D;
import static domain.position.File.E;
import static domain.position.Rank.FIVE;
import static domain.position.Rank.FOUR;
import static domain.position.Rank.SEVEN;
import static domain.position.Rank.SIX;
import static domain.position.Rank.THREE;
import static domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.fixture.PositionFixture;
import domain.position.Position;
import domain.position.PositionGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PawnTest {

    @Nested
    class WhitePawnTest {

        private static List<Position> invalidPositions_WhiteNotFirstMove() {
            Position position = PositionGenerator.generate(D, FIVE);
            return PositionFixture.otherPositions(position);
        }

        private static List<Position> invalidPositions_WhiteAttack() {
            Position position1 = PositionGenerator.generate(E, THREE);
            Position position2 = PositionGenerator.generate(C, THREE);
            return PositionFixture.otherPositions(List.of(position1, position2));
        }

        /*
            ........
            ........
            ........
            ........
            ...*....
            ...*....
            ...S....
            ........
         */
        @Test
        @DisplayName("하얀 기물이 첫 이동일 경우 한 칸 또는 두 칸 위로 움직일 수 있다.")
        void canMove_WhiteFirstMove_True() {
            Piece piece = new Pawn(Color.WHITE);
            Position source = PositionGenerator.generate(D, TWO);
            Position target1 = PositionGenerator.generate(D, THREE);
            Position target2 = PositionGenerator.generate(D, FOUR);

            boolean actual1 = piece.canMove(source, target1);
            boolean actual2 = piece.canMove(source, target2);

            assertThat(actual1).isTrue();
            assertThat(actual2).isTrue();
        }

        /*
            ........
            ........
            ...*....
            ...S....
            ........
            ........
            ........
            ........
         */
        @Test
        @DisplayName("하얀 기물이 첫 이동이 아닐 경우 한 칸 위로 움직일 수 있다.")
        void canMove_WhiteNotFirstMove_True() {
            Piece piece = new Pawn(Color.WHITE);
            Position source = PositionGenerator.generate(D, FOUR);
            Position target = PositionGenerator.generate(D, FIVE);

            boolean actual = piece.canMove(source, target);

            assertThat(actual).isTrue();
        }

        @ParameterizedTest
        @MethodSource("invalidPositions_WhiteNotFirstMove")
        @DisplayName("하얀 기물이 첫 이동이 아닐 경우 한 칸 위가 아닌 곳으로 움직일 수 없다.")
        void canMove_WhiteNotFirstMove_False(Position target) {
            Piece piece = new Pawn(Color.WHITE);
            Position source = PositionGenerator.generate(D, FOUR);

            boolean actual = piece.canMove(source, target);

            assertThat(actual).isFalse();
        }

        /*
            ........
            ........
            ........
            ........
            ........
            ..*.*...
            ...S....
            ........
         */
        @Test
        @DisplayName("하얀 기물이 오른쪽위 또는 왼쪽위 한 칸을 공격할 수 있다.")
        void canAttack_White_True() {
            Piece piece = new Pawn(Color.WHITE);
            Position source = PositionGenerator.generate(D, TWO);
            Position target1 = PositionGenerator.generate(E, THREE);
            Position target2 = PositionGenerator.generate(C, THREE);

            boolean actual1 = piece.canAttack(source, target1);
            boolean actual2 = piece.canAttack(source, target2);

            assertThat(actual1).isTrue();
            assertThat(actual2).isTrue();
        }

        @ParameterizedTest
        @MethodSource("invalidPositions_WhiteAttack")
        @DisplayName("하얀 기물이 오른쪽위 또는 왼쪽위 한 칸이 아닌 곳을 공격할 수 없다.")
        void canAttack_White_False(Position target) {
            Piece piece = new Pawn(Color.WHITE);
            Position source = PositionGenerator.generate(D, TWO);

            boolean actual = piece.canAttack(source, target);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class BlackPawnTest {

        /*
            ........
            ...S....
            ...*....
            ...*....
            ........
            ........
            ........
            ........
         */

        private static List<Position> invalidPositions_BlackNotFirstMove() {
            Position position = PositionGenerator.generate(D, THREE);
            return PositionFixture.otherPositions(position);
        }

        private static List<Position> invalidPositions_BlackAttack() {
            Position position1 = PositionGenerator.generate(E, SIX);
            Position position2 = PositionGenerator.generate(C, SIX);
            return PositionFixture.otherPositions(List.of(position1, position2));
        }

        @Test
        @DisplayName("검정 기물이 첫 이동일 경우 한 칸 또는 두 칸 아래로 움직일 수 있다.")
        void canMove_BlackFirstMove_True() {
            Piece piece = new Pawn(Color.BLACK);
            Position source = PositionGenerator.generate(D, SEVEN);
            Position target1 = PositionGenerator.generate(D, SIX);
            Position target2 = PositionGenerator.generate(D, FIVE);

            boolean actual1 = piece.canMove(source, target1);
            boolean actual2 = piece.canMove(source, target2);

            assertThat(actual1).isTrue();
            assertThat(actual2).isTrue();
        }

        /*
            ........
            ........
            ........
            ........
            ........
            ...S....
            ...*....
            ........
         */
        @Test
        @DisplayName("검정 기물이 첫 이동이 아닐 경우 한 칸 아래로 움직일 수 있다.")
        void canMove_BlackNotFirstMove_True() {
            Piece piece = new Pawn(Color.BLACK);
            Position source = PositionGenerator.generate(D, FOUR);
            Position target = PositionGenerator.generate(D, THREE);

            boolean actual = piece.canMove(source, target);

            assertThat(actual).isTrue();
        }

        @ParameterizedTest
        @MethodSource("invalidPositions_BlackNotFirstMove")
        @DisplayName("검정 기물이 첫 이동이 아닐 경우 한 칸 아래가 아닌 곳으로 움직일 수 없다.")
        void canMove_BlackNotFirstMove_False(Position target) {
            Piece piece = new Pawn(Color.BLACK);
            Position source = PositionGenerator.generate(D, FOUR);

            boolean actual = piece.canMove(source, target);

            assertThat(actual).isFalse();
        }

        /*
            ........
            ...S....
            ..*.*...
            ........
            ........
            ........
            ........
            ........
         */
        @Test
        @DisplayName("검정 기물이 오른쪽아래 또는 왼쪽아래 한 칸을 공격할 수 있다.")
        void canAttack_Black_True() {
            Piece piece = new Pawn(Color.BLACK);
            Position source = PositionGenerator.generate(D, SEVEN);
            Position target1 = PositionGenerator.generate(E, SIX);
            Position target2 = PositionGenerator.generate(C, SIX);

            boolean actual1 = piece.canAttack(source, target1);
            boolean actual2 = piece.canAttack(source, target2);

            assertThat(actual1).isTrue();
            assertThat(actual2).isTrue();
        }

        @ParameterizedTest
        @MethodSource("invalidPositions_BlackAttack")
        @DisplayName("검정 기물이 오른쪽아래 또는 왼쪽아래 한 칸이 아닌 곳을 공격할 수 없다.")
        void canAttack_Black_False(Position target) {
            Piece piece = new Pawn(Color.BLACK);
            Position source = PositionGenerator.generate(D, SEVEN);

            boolean actual = piece.canAttack(source, target);

            assertThat(actual).isFalse();
        }
    }
}
