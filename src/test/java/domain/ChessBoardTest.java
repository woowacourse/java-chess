package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Knight;
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

    @Test
    @DisplayName("위치 이동 테스트")
    void change_position_test() {
        chessBoard.move("b1", "c3");
        assertThat(chessBoard.getPiece(Position.valueOf("c3"))).isInstanceOf(Knight.class);
    }

//    @Test
//    @DisplayName("말 객체 갯수 테스트")
//    void team_pieces_count_test() {
//        chessBoard.getWhite
//    }
}
