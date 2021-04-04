package chess.domain.piece;

import chess.domain.game.EmptyBoardMap;
import chess.domain.location.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    private Knight knight;

    @BeforeEach
    void setUp() {
        knight = new Knight(Color.BLACK);
    }

    @Test
    @DisplayName("나이트 이동 가능한 위치 값 들 확인")
    void possiblePositions() {
        Position position = Position.from("c5");
        List<Position> positions = knight.movablePositions(position, EmptyBoardMap.create());
        assertThat(positions).containsExactlyInAnyOrder(
                Position.from("b7"),
                Position.from("d7"),
                Position.from("e6"),
                Position.from("e4"),
                Position.from("d3"),
                Position.from("b3"),
                Position.from("a4"),
                Position.from("a6")
        );
    }
}