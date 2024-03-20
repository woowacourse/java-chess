package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.PieceColor;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;

@DisplayName("비숍")
class BishopTest {

    @ParameterizedTest
    @ValueSource(strings = {"a8", "e8", "a4", "h1"})
    @DisplayName("에 대한 이동 루트가 대각선인지 판단한다.")
    void canMove(String target) {
        Board board = new Board(Map.of());
        Bishop bishop = new Bishop(PieceColor.BLACK);

        boolean actual = bishop.canMove(Position.from("c6"), Position.from(target), board);

        assertThat(actual).isTrue();
    }
}
