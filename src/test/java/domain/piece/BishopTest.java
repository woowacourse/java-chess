package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.fixture.TestFixture;
import domain.position.Position;
import domain.position.Positions;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {
    @DisplayName("비숍은 대각선 네 방향으로 이동할 수 있다.")
    @Test
    void bishopMovable() {
        //given
        final Bishop bishop = TestFixture.BLACK_BISHOP;
        Position source = Positions.from("D4");
        final List<Position> destinations = Positions.of(
                "H8", "A7", "A1", "G1");

        //when

        //then
        assertThat(destinations).allMatch(destination -> bishop.isMovable(source, destination));
    }

    @DisplayName("비숍은 대각선이 아니면 이동할 수 없다.")
    @Test
    void bishopUnMovable() {
        //given
        final Bishop bishop = TestFixture.BLACK_BISHOP;
        Position source = Positions.from("D4");
        final List<Position> destinations = Positions.of(
                "F8", "A6", "A2", "H3");

        //when

        //then
        assertThat(destinations).noneMatch(destination -> bishop.isMovable(source, destination));
    }
}
