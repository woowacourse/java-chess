package chess.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.dto.BoardDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("체스판에 기물을 초기화 한다")
    @Test
    void createPiecesOnBoard() {
        Board board = Board.createInitialBoard();
        BoardDto boardDto = BoardDto.from(board);

        String expected = """
                RNBQKBNR
                PPPPPPPP
                ........
                ........
                ........
                ........
                pppppppp
                rnbqkbnr""";

        assertThat(boardDto).hasToString(expected);
    }

    @DisplayName("이동 시 source 위치에 기물이 없으면 예외가 발생한다")
    @Test
    void pieceNotExistsOnSourceCoordinate() {
        Board board = Board.createInitialBoard();
        assertThatThrownBy(() -> board.move("c4", "c5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("source위치에 기물이 존재하지 않습니다.");
    }

    @DisplayName("이동 시 target 위치에 내 기물이 있으면 예외가 발생한다")
    @Test
    void pieceExistsOnTargetCoordinate() {
        Board board = Board.createInitialBoard();
        assertThatThrownBy(() -> board.move("c2", "b2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("target위치에 내 기물이 존재합니다.");
    }

    @DisplayName("이동 시 target 위치로 이동할 수 없는 기물이면 예외가 발생한다")
    @Test
    void pieceCanNotMoveOnTargetCoordinate() {
        Board board = Board.createInitialBoard();
        assertThatThrownBy(() -> board.move("c2", "c5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("target위치로 기물을 이동할 수 없습니다.");
    }
}
