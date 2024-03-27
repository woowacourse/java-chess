package domain.piece;

import static domain.piece.PositionFixture.C3;
import static domain.piece.PositionFixture.C6;
import static domain.piece.PositionFixture.D2;
import static domain.piece.PositionFixture.D3;
import static domain.piece.PositionFixture.D4;
import static domain.piece.PositionFixture.D5;
import static domain.piece.PositionFixture.D6;
import static domain.piece.PositionFixture.D7;
import static domain.piece.PositionFixture.E3;
import static domain.piece.PositionFixture.E6;
import static domain.piece.PositionFixture.otherPositions;
import static domain.position.File.C;
import static domain.position.File.D;
import static domain.position.File.E;
import static domain.position.Rank.FIVE;
import static domain.position.Rank.SIX;
import static domain.position.Rank.THREE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.position.Position;
import domain.position.PositionGenerator;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PawnTest {

    @Nested
    class WhitePawnTest {

        private static Set<Position> invalidPositions_WhiteNotFirstMove() {
            Position position = PositionGenerator.generate(D, FIVE);
            return otherPositions(position);
        }

        private static Set<Position> invalidPositions_WhiteAttack() {
            Position position1 = PositionGenerator.generate(E, THREE);
            Position position2 = PositionGenerator.generate(C, THREE);
            return otherPositions(Set.of(position1, position2));
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
            Position source = D2;

            boolean actual1 = piece.canMove(source, D3);
            boolean actual2 = piece.canMove(source, D4);

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

            boolean actual = piece.canMove(D4, D5);

            assertThat(actual).isTrue();
        }

        @ParameterizedTest
        @MethodSource("invalidPositions_WhiteNotFirstMove")
        @DisplayName("하얀 기물이 첫 이동이 아닐 경우 한 칸 위가 아닌 곳으로 움직일 수 없다.")
        void canMove_WhiteNotFirstMove_False(Position target) {
            Piece piece = new Pawn(Color.WHITE);

            boolean actual = piece.canMove(D4, target);

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

            boolean actual1 = piece.canAttack(D2, E3);
            boolean actual2 = piece.canAttack(D2, C3);

            assertThat(actual1).isTrue();
            assertThat(actual2).isTrue();
        }

        @ParameterizedTest
        @MethodSource("invalidPositions_WhiteAttack")
        @DisplayName("하얀 기물이 오른쪽위 또는 왼쪽위 한 칸이 아닌 곳을 공격할 수 없다.")
        void canAttack_White_False(Position target) {
            Piece piece = new Pawn(Color.WHITE);

            boolean actual = piece.canAttack(D2, target);

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

        private static Set<Position> invalidPositions_BlackNotFirstMove() {
            Position position = PositionGenerator.generate(D, THREE);
            return otherPositions(position);
        }

        private static Set<Position> invalidPositions_BlackAttack() {
            Position position1 = PositionGenerator.generate(E, SIX);
            Position position2 = PositionGenerator.generate(C, SIX);
            return otherPositions(Set.of(position1, position2));
        }

        @Test
        @DisplayName("검정 기물이 첫 이동일 경우 한 칸 또는 두 칸 아래로 움직일 수 있다.")
        void canMove_BlackFirstMove_True() {
            Piece piece = new Pawn(Color.BLACK);

            boolean actual1 = piece.canMove(D7, D6);
            boolean actual2 = piece.canMove(D7, D5);

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

            boolean actual = piece.canMove(D4, D3);

            assertThat(actual).isTrue();
        }

        @ParameterizedTest
        @MethodSource("invalidPositions_BlackNotFirstMove")
        @DisplayName("검정 기물이 첫 이동이 아닐 경우 한 칸 아래가 아닌 곳으로 움직일 수 없다.")
        void canMove_BlackNotFirstMove_False(Position target) {
            Piece piece = new Pawn(Color.BLACK);

            boolean actual = piece.canMove(D4, target);

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

            boolean actual1 = piece.canAttack(D7, E6);
            boolean actual2 = piece.canAttack(D7, C6);

            assertThat(actual1).isTrue();
            assertThat(actual2).isTrue();
        }

        @ParameterizedTest
        @MethodSource("invalidPositions_BlackAttack")
        @DisplayName("검정 기물이 오른쪽아래 또는 왼쪽아래 한 칸이 아닌 곳을 공격할 수 없다.")
        void canAttack_Black_False(Position target) {
            Piece piece = new Pawn(Color.BLACK);

            boolean actual = piece.canAttack(D7, target);

            assertThat(actual).isFalse();
        }
    }
}
