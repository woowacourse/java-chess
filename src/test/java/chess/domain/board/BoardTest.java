package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {

    @Test
    @DisplayName("존재하지 않는 말을 선택한 경우 예외발생")
    void move() {
        final Board emptyBoard = new Board(HashMap::new);

        assertThatThrownBy(() -> emptyBoard.move(Position.from("e5"), Position.from("e7")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("보드판에 king 이 없는 경우 예외 발생")
    void moveWithOutKing() {
        final Board hasNoKingBoard = new Board(() -> new HashMap<>(Map.of(Position.from("e5"), new Pawn(Color.BLACK))));

        assertThatThrownBy(() -> hasNoKingBoard.move(Position.from("e5"), Position.from("e4")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 보드판에 king 이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("보드판에 king 이 한 개만 존재하는 경우 예외 발생")
    void moveOnlyOneKing() {
        final Board hasOneKingBoard = new Board(() -> new HashMap<>(Map.of(
                Position.from("e5"), new Pawn(Color.BLACK),
                Position.from("a5"), new King(Color.BLACK)
        )));

        assertThatThrownBy(() -> hasOneKingBoard.move(Position.from("e5"), Position.from("e4")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 보드판에 king 이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("보드판에 king 이 두 개 존재하는 경우")
    void moveHasTwoKing() {
        final Board hasTwoKingBoard = new Board(() -> new HashMap<>(Map.of(
                Position.from("e5"), new Pawn(Color.BLACK),
                Position.from("a8"), new King(Color.WHITE),
                Position.from("a5"), new King(Color.BLACK)
        )));

        assertThatCode(() -> hasTwoKingBoard.move(Position.from("e5"), Position.from("e4")))
                .doesNotThrowAnyException();
    }
}
