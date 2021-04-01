package chess.domain.piece;

import chess.domain.game.EmptyBoardMap;
import chess.domain.location.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    private Queen queen;

    @BeforeEach
    void setUp() {
        queen = new Queen(Color.BLACK);
    }

    @Test
    @DisplayName("퀸 이동 가능한 위치 값 들 확인")
    void possiblePositions() {
        Position position = Position.from("c5");
        List<Position> positions = queen.movablePositions(position, EmptyBoardMap.create());
        assertThat(positions).containsExactlyInAnyOrder(
                Position.from("b5"), Position.from("a5"),
                Position.from("d5"), Position.from("e5"), Position.from("f5"), Position.from("g5"), Position.from("h5"),
                Position.from("c4"), Position.from("c3"), Position.from("c2"), Position.from("c1"),
                Position.from("c6"), Position.from("c7"), Position.from("c8"),
                Position.from("b4"), Position.from("a3"),
                Position.from("d4"), Position.from("e3"), Position.from("f2"), Position.from("g1"),
                Position.from("b6"), Position.from("a7"),
                Position.from("d6"), Position.from("e7"), Position.from("f8"));
    }
}