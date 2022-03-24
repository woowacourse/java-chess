package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @DisplayName("체스 보드를 생성한다.")
    @Test
    void 체스보드를_생성한다() {
        assertDoesNotThrow(() -> new ChessBoard());
    }

    @DisplayName("잘못된 위치 정보인 경우 예외를 던진다.")
    @Test
    void 잘못된_위치_정보인_경우_예외를_던진다() {
        ChessBoard chessBoard = new ChessBoard();

        assertThatThrownBy(() -> chessBoard.move("i1", "a1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source 위치에 기물이 존재하지 않으면 예외를 던진다.")
    @Test
    void source_위치에_기물이_존재하지_않으면_예외를_던진다() {
        ChessBoard chessBoard = new ChessBoard();

        assertThatThrownBy(() -> chessBoard.move("b1", "c3"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
