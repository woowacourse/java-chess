package chess.domain.piece;

import chess.domain.game.EmptyBoardMap;
import chess.domain.location.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    private Rook rook;
    private Map<Position, Piece> map;

    @BeforeEach
    void setUp() {
        rook = new Rook(Color.BLACK);
        map = EmptyBoardMap.create();
    }

    @Test
    @DisplayName("룩 이동 가능한 위치 값 들 확인")
    void possiblePositions() {
        map.put(Position.from("c5"), rook);
        List<Position> positions = rook.movablePositions(Position.from("c5"), map);
        assertThat(positions).containsExactlyInAnyOrder(
                Position.from("b5"), Position.from("a5"),
                Position.from("d5"), Position.from("e5"), Position.from("f5"), Position.from("g5"), Position.from("h5"),
                Position.from("c4"), Position.from("c3"), Position.from("c2"), Position.from("c1"),
                Position.from("c6"), Position.from("c7"), Position.from("c8"));
    }
}