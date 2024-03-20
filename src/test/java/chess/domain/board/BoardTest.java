package chess.domain.board;

import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("체스판")
public class BoardTest {

    @DisplayName("체스판을 생성한다.")
    @Test
    void createBoard() {
        // when & then
        assertThatCode(Board::new).doesNotThrowAnyException();
    }

    @DisplayName("체스판은 목적지에 같은 색의 말이 있으면 예외를 반환한다.")
    @Test
    void checkPieceSameColor() {
        // given
        Board board = new Board();
        Square source = Square.of(File.a, Rank.EIGHT);
        Square destination = Square.of(File.a, Rank.SEVEN);

        // when & then
        assertThatThrownBy(() -> board.move(source, destination)).isInstanceOf(IllegalArgumentException.class);
    }
}
