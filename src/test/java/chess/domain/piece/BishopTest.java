package chess.domain.piece;

import chess.domain.location.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    private Bishop bishop;

    @BeforeEach
    void setUp() {
        bishop = new Bishop(Color.BLACK);
    }

    @Test
    @DisplayName("비숍 이동 가능한 위치 값 들 확인")
    void possiblePositions() {
        Position position = Position.from("c5");
        List<List<Position>> positions = bishop.movablePositions(position);
        assertThat(positions).contains(
                Arrays.asList(Position.from("b4"), Position.from("a3")),
                Arrays.asList(Position.from("d4"), Position.from("e3"), Position.from("f2"), Position.from("g1")),
                Arrays.asList(Position.from("b6"), Position.from("a7")),
                Arrays.asList(Position.from("d6"), Position.from("e7"), Position.from("f8"))
        );
    }
}