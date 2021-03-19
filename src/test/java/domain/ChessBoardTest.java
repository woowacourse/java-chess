package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        this.chessBoard = new ChessBoard();
    }

    @Test
    @DisplayName("체스판 생성 테스트")
    void createChessBoard() {
        assertThatCode(() -> new ChessBoard()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("말 이동 실패 테스트")
    void failOutOfBoundary() {
        assertThatThrownBy(() -> {
            chessBoard.move("b2", "b10");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("동일한 위치 금지 테스트")
    void failSamePosition() {
        assertThatThrownBy(() -> {
            chessBoard.move("b2", "b2");
        }).isInstanceOf(IllegalArgumentException.class);
    }




}
