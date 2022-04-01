package chess.domain.board;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class BoardTest {

    @Test
    @DisplayName("king 유무 확인하는 기능")
    void removedKing() {
        final Board board = new Board(() -> new HashMap<>(Map.of(
                Position.from("d4"), new King(Color.WHITE),
                Position.from("a5"), new King(Color.BLACK)
        )));
        assertThat(board.hasKing(Color.BLACK)).isTrue();
        assertThat(board.hasKing(Color.WHITE)).isTrue();
    }

    @Test
    @DisplayName("king 제거하지 못한 경우")
    void moveHasTwoKing() {
        final Board board = new Board(() -> new HashMap<>(Map.of(
                Position.from("e5"), new Pawn(Color.BLACK),
                Position.from("a8"), new King(Color.WHITE),
                Position.from("a5"), new King(Color.BLACK)
        )));

        assertThatCode(() -> board.move(Position.from("e5"), Position.from("e4")))
                .doesNotThrowAnyException();
    }
}
