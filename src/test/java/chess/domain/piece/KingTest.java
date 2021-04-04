package chess.domain.piece;

import chess.domain.game.EmptyBoardMap;
import chess.domain.location.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    private King king;

    @BeforeEach
    void setUp() {
        king = new King(Color.BLACK);
    }

    @Test
    @DisplayName("킹 이동 가능한 위치 값 들 확인")
    void possiblePositions() {
        Position position = Position.from("c5");
        List<Position> positions = king.movablePositions(position, EmptyBoardMap.create());
        assertThat(positions).containsExactlyInAnyOrder(
                Position.from("b6"),
                Position.from("c6"),
                Position.from("d6"),
                Position.from("b5"),
                Position.from("d5"),
                Position.from("b4"),
                Position.from("c4"),
                Position.from("d4")
        );
    }
}