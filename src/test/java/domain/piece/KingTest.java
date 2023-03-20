package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.fixture.TestFixture;
import domain.position.Position;
import domain.position.Positions;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    @DisplayName("킹은 여덟 방향으로 한 칸 이동할 수 있다.")
    @Test
    void kingMovable() {
        //given
        final King king = TestFixture.BLACK_KING;
        Position source = Positions.from("D4");
        final List<Position> destinations = Positions.of(
                "C5", "D5", "E5", "C4", "E4", "C3", "D3", "E3");

        //when

        //then
        assertThat(destinations).allMatch(destination -> king.isMovable(source, destination));
    }

    @DisplayName("킹은 여덟 방향으로 한 칸이 아니면 이동할 수 없다.")
    @Test
    void kingUnMovable() {
        //given
        final King king = TestFixture.BLACK_KING;
        Position source = Positions.from("D4");
        final List<Position> destinations = Positions.of(
                "B7", "D8", "G6");

        //when

        //then
        assertThat(destinations).noneMatch(destination -> king.isMovable(source, destination));
    }
}
