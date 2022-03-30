package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.notation.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RunningTest {

    @Test
    @DisplayName("출발 위치에 기물이 없는 경우 예외발생")
    void invalid() {
        final var state = new Running(Color.WHITE, new Board(HashMap::new));

        assertThatThrownBy(() -> state.move(Position.from("e5"), Position.from("e6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 말이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("출발 위치에 기물이 나의 색과 다른 경우 예외 발생")
    void invalid2() {
        final Map<Position, Piece> board = new HashMap<>(Map.of(
                Position.from("e5"), new Pawn(Color.BLACK)
        ));

        final var state = new Running(Color.WHITE, new Board(() -> board));

        assertThatThrownBy(() -> state.move(Position.from("e5"), Position.from("e6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Color.WHITE + "차례 입니다.");
    }

    @Test
    @DisplayName("도착 위치에 나의 색과 같은 기물이 있는 경우 예외 발생")
    void invalid3() {
        final Map<Position, Piece> board = new HashMap<>(Map.of(
                Position.from("e5"), new Pawn(Color.WHITE),
                Position.from("e6"), new Pawn(Color.WHITE)
        ));

        final var state = new Running(Color.WHITE, new Board(() -> board));

        assertThatThrownBy(() -> state.move(Position.from("e5"), Position.from("e6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착지에 같은색의 기물이 존재합니다.");
    }
}
