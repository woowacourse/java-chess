package chess.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.dto.BoardDto;
import chess.model.piece.Color;
import chess.model.piece.Piece;
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

    @DisplayName("White 차례에 Black 기물 이동 시 예외가 발생한다.")
    @Test
    void notBlackTurn() {
        Board board = Board.createInitialBoard();
        assertThatThrownBy(() -> board.move("b7", "b6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지금은 White 차례입니다.");
    }

    @DisplayName("Black 차례에 White 기물 이동 시 예외가 발생한다.")
    @Test
    void notWhiteTurn() {
        Board board = Board.createInitialBoard();
        board.move("b2", "b4");
        assertThatThrownBy(() -> board.move("b4", "b5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지금은 Black 차례입니다.");
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

    @DisplayName("이동 경로 상에 기물이 존재하면 예외가 발생한다.")
    @Test
    void obstacleOnRoute() {
        Board board = Board.createInitialBoard();
        assertThatThrownBy(() -> board.move("c1", "e3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로 상에 다른 기물이 존재합니다.");
    }

    @DisplayName("knight는 이동 경로 상에 기물이 존재해도 예외가 발생하지 않는다")
    @Test
    void obstacleOnRouteButKnight() {
        Board board = Board.createInitialBoard();
        assertThatCode(() -> board.move("b1", "c3"))
                .doesNotThrowAnyException();
    }

    @DisplayName("b1에서 c3로 기물이 이동한다")
    @Test
    void moveSuccess() {
        Board board = Board.createInitialBoard();
        Position sourcePosition = Position.from("b1");
        Piece sourcePiece = board.findPiece(sourcePosition);

        board.move("b1", "c3");

        Position targetPosition = Position.from("c3");
        Piece targetPiece = board.findPiece(targetPosition);

        Piece emptyPiece = board.findPiece(sourcePosition);

        assertAll(
                () -> assertThat(targetPiece).isEqualTo(sourcePiece),
                () -> assertThat(emptyPiece.isAlly(Color.NONE)).isTrue()
        );
    }
}
