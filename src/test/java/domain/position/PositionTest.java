package domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import domain.movement.Direction;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

class PositionTest {
    @Nested
    class 직선 {
        @ParameterizedTest
        @EnumSource(value = Rank.class, names = {"TWO", "EIGHT"})
        void UP_방향으로_움직인다(Rank rank) {
            Position resource = new Position(File.A, Rank.ONE);
            Position target = new Position(File.A, rank);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.UP);
        }

        @Test
        void UP_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.A, Rank.ONE);
            Position nextPosition = position.next(Direction.UP);

            assertThat(nextPosition).isEqualTo(new Position(File.A, Rank.TWO));
        }

        @ParameterizedTest
        @EnumSource(value = Rank.class, names = {"SEVEN", "ONE"})
        void DOWN_방향으로_움직인다(Rank rank) {
            Position resource = new Position(File.A, Rank.EIGHT);
            Position target = new Position(File.A, rank);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.DOWN);
        }

        @Test
        void DOWN_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.A, Rank.EIGHT);
            Position nextPosition = position.next(Direction.DOWN);

            assertThat(nextPosition).isEqualTo(new Position(File.A, Rank.SEVEN));
        }

        @ParameterizedTest
        @EnumSource(value = File.class, names = {"B", "H"})
        void RIGHT_방향으로_움직인다(File file) {
            Position resource = new Position(File.A, Rank.ONE);
            Position target = new Position(file, Rank.ONE);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.RIGHT);
        }

        @Test
        void RIGHT_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.A, Rank.ONE);
            Position nextPosition = position.next(Direction.RIGHT);

            assertThat(nextPosition).isEqualTo(new Position(File.B, Rank.ONE));
        }

        @ParameterizedTest
        @EnumSource(value = File.class, names = {"G", "A"})
        void LEFT_방향으로_움직인다(File file) {
            Position resource = new Position(File.H, Rank.ONE);
            Position target = new Position(file, Rank.ONE);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.LEFT);
        }

        @Test
        void LEFT_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.H, Rank.ONE);
            Position nextPosition = position.next(Direction.LEFT);

            assertThat(nextPosition).isEqualTo(new Position(File.G, Rank.ONE));
        }
    }

    @Nested
    class 대각선 {
        @ParameterizedTest
        @CsvSource(value = {"G, SEVEN", "A, ONE"})
        void DOWN_LEFT_방향으로_움직인다(File file, Rank rank) {
            Position resource = new Position(File.H, Rank.EIGHT);
            Position target = new Position(file, rank);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.DOWN_LEFT);
        }

        @Test
        void DOWN_LEFT_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.H, Rank.EIGHT);
            Position nextPosition = position.next(Direction.DOWN_LEFT);

            assertThat(nextPosition).isEqualTo(new Position(File.G, Rank.SEVEN));
        }

        @ParameterizedTest
        @CsvSource(value = {"G, TWO", "A, EIGHT"})
        void UP_LEFT_방향으로_움직인다(File file, Rank rank) {
            Position resource = new Position(File.H, Rank.ONE);
            Position target = new Position(file, rank);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.UP_LEFT);
        }

        @Test
        void UP_LEFT_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.H, Rank.ONE);
            Position nextPosition = position.next(Direction.UP_LEFT);

            assertThat(nextPosition).isEqualTo(new Position(File.G, Rank.TWO));
        }

        @ParameterizedTest
        @CsvSource(value = {"B, TWO", "H, EIGHT"})
        void UP_RIGHT_방향으로_움직인다(File file, Rank rank) {
            Position resource = new Position(File.A, Rank.ONE);
            Position target = new Position(file, rank);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.UP_RIGHT);
        }

        @Test
        void UP_RIGHT_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.A, Rank.ONE);
            Position nextPosition = position.next(Direction.UP_RIGHT);

            assertThat(nextPosition).isEqualTo(new Position(File.B, Rank.TWO));
        }

        @ParameterizedTest
        @CsvSource(value = {"B, SEVEN", "H, ONE"})
        void DOWN_RIGHT_방향으로_움직인다(File file, Rank rank) {
            Position resource = new Position(File.A, Rank.EIGHT);
            Position target = new Position(file, rank);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.DOWN_RIGHT);
        }

        @Test
        void DOWN_RIGHT_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.A, Rank.EIGHT);
            Position nextPosition = position.next(Direction.DOWN_RIGHT);

            assertThat(nextPosition).isEqualTo(new Position(File.B, Rank.SEVEN));
        }
    }

    @Nested
    class 나이트 {
        @Test
        void KNIGHT_UP_LEFT_방향으로_움직인다() {
            Position resource = new Position(File.C, Rank.FOUR);
            Position target = new Position(File.B, Rank.SIX);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.KNIGHT_UP_LEFT);
        }

        @Test
        void KNIGHT_UP_LEFT_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.C, Rank.FOUR);
            Position nextPosition = position.next(Direction.KNIGHT_UP_LEFT);

            assertThat(nextPosition).isEqualTo(new Position(File.B, Rank.SIX));
        }

        @Test
        void KNIGHT_UP_RIGHT_방향으로_움직인다() {
            Position resource = new Position(File.C, Rank.FOUR);
            Position target = new Position(File.D, Rank.SIX);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.KNIGHT_UP_RIGHT);
        }

        @Test
        void KNIGHT_UP_RIGHT_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.C, Rank.FOUR);
            Position nextPosition = position.next(Direction.KNIGHT_UP_RIGHT);

            assertThat(nextPosition).isEqualTo(new Position(File.D, Rank.SIX));
        }

        @Test
        void KNIGHT_RIGHT_UP_방향으로_움직인다() {
            Position resource = new Position(File.C, Rank.FOUR);
            Position target = new Position(File.E, Rank.FIVE);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.KNIGHT_RIGHT_UP);
        }

        @Test
        void KNIGHT_RIGHT_UP_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.C, Rank.FOUR);
            Position nextPosition = position.next(Direction.KNIGHT_RIGHT_UP);

            assertThat(nextPosition).isEqualTo(new Position(File.E, Rank.FIVE));
        }

        @Test
        void KNIGHT_RIGHT_DOWN_방향으로_움직인다() {
            Position resource = new Position(File.C, Rank.FOUR);
            Position target = new Position(File.E, Rank.THREE);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.KNIGHT_RIGHT_DOWN);
        }

        @Test
        void KNIGHT_RIGHT_DOWN_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.C, Rank.FOUR);
            Position nextPosition = position.next(Direction.KNIGHT_RIGHT_DOWN);

            assertThat(nextPosition).isEqualTo(new Position(File.E, Rank.THREE));
        }

        @Test
        void KNIGHT_DOWN_RIGHT_방향으로_움직인다() {
            Position resource = new Position(File.C, Rank.FOUR);
            Position target = new Position(File.D, Rank.TWO);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.KNIGHT_DOWN_RIGHT);
        }

        @Test
        void KNIGHT_DOWN_RIGHT_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.C, Rank.FOUR);
            Position nextPosition = position.next(Direction.KNIGHT_DOWN_RIGHT);

            assertThat(nextPosition).isEqualTo(new Position(File.D, Rank.TWO));
        }

        @Test
        void KNIGHT_DOWN_LEFT_방향으로_움직인다() {
            Position resource = new Position(File.C, Rank.FOUR);
            Position target = new Position(File.B, Rank.TWO);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.KNIGHT_DOWN_LEFT);
        }

        @Test
        void KNIGHT_DOWN_LEFT_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.C, Rank.FOUR);
            Position nextPosition = position.next(Direction.KNIGHT_DOWN_LEFT);

            assertThat(nextPosition).isEqualTo(new Position(File.B, Rank.TWO));
        }

        @Test
        void KNIGHT_LEFT_DOWN_방향으로_움직인다() {
            Position resource = new Position(File.C, Rank.FOUR);
            Position target = new Position(File.A, Rank.THREE);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.KNIGHT_LEFT_DOWN);
        }

        @Test
        void KNIGHT_LEFT_DOWN_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.C, Rank.FOUR);
            Position nextPosition = position.next(Direction.KNIGHT_LEFT_DOWN);

            assertThat(nextPosition).isEqualTo(new Position(File.A, Rank.THREE));
        }

        @Test
        void KNIGHT_LEFT_UP_방향으로_움직인다() {
            Position resource = new Position(File.C, Rank.FOUR);
            Position target = new Position(File.A, Rank.FIVE);

            assertThat(resource.getDirection(target)).isEqualTo(Direction.KNIGHT_LEFT_UP);
        }

        @Test
        void KNIGHT_LEFT_UP_방향으로_움직인_포지션을_반환한다() {
            Position position = new Position(File.C, Rank.FOUR);
            Position nextPosition = position.next(Direction.KNIGHT_LEFT_UP);

            assertThat(nextPosition).isEqualTo(new Position(File.A, Rank.FIVE));
        }
    }
}
