package chess.board;

import chess.manager.ChessManager;
import chess.piece.Piece;
import chess.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessBoardAdapterTest {

    @DisplayName("체스말이 움직였는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"b1,c3,BLANK", "a7,a6,BLANK"})
    void moveTest(String sourceKey, String targetKey, Pieces expect) {
        //given
        ChessBoardAdapter chessBoard = BoardGenerator.create();
        ChessManager chessManager = new ChessManager(chessBoard);

        //when
        Piece actual = chessBoard.move(sourceKey, targetKey);

        //then
        assertThat(actual).isEqualTo(expect.getPiece());
    }

    @DisplayName("체스말이 움직일 수 없는 곳으로 움직이려 할 경우 Exception")
    @Test
    void name() {
        String sourceKey = "b1";
        String targetKey = "c4";

        ChessBoardAdapter chessBoard = BoardGenerator.create();
        ChessManager chessManager = new ChessManager(chessBoard);

        assertThatThrownBy(() -> chessManager.move(sourceKey, targetKey))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%s 에 도달할 수 없습니다.", targetKey);
    }

    @DisplayName("체스말이 움직이는 경로에 장애물이 있는경우 Exception")
    @Test
    void name1() {
        String sourceKey = "a1";
        String targetKey = "a3";

        ChessBoardAdapter chessBoard = BoardGenerator.create();
        ChessManager chessManager = new ChessManager(chessBoard);

        assertThatThrownBy(() -> chessManager.move(sourceKey, targetKey))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 체스말이 존재합니다.");
    }
}