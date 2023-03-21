package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.fixture.TestFixture;
import domain.position.Position;
import domain.position.Positions;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @DisplayName("퀸은 여덟 방향으로 이동할 수 있다.")
    @Test
    void queenMovable() {
        //given
        final Queen queen = TestFixture.BLACK_QUEEN;
        Position source = Positions.from("D4");
        final List<Position> destinations = Positions.of(
                "A7", "D5", "A1", "C4", "D1", "C3", "G7", "E3");

        //when

        //then
        assertThat(destinations).allMatch(destination -> queen.isMovable(source, destination));
    }

    @DisplayName("퀸은 여덟 방향이 아니면 이동할 수 없다.")
    @Test
    void queenUnMovable() {
        //given
        final Queen queen = TestFixture.BLACK_QUEEN;
        Position source = Positions.from("D4");
        final List<Position> destinations = Positions.of(
                "A8", "B5", "G6");

        //when

        //then
        assertThat(destinations).noneMatch(destination -> queen.isMovable(source, destination));
    }
}
