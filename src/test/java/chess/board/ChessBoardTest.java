package chess.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardTest {

    @DisplayName("체스말이 움직였는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"b1,c3,true", "b1,c4,false", "a1,a3,false", "a7,a6,true"})
    void moveTest(String sourceKey, String targetKey, boolean expect) {
        //given
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator(BoardGenerator.Template.BASIC_BOARD.getTemplate()));

        //when
        boolean actual = chessBoard.move(sourceKey, targetKey);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("체스판 계산 확인")
    @ParameterizedTest
    @CsvSource(value = {"b1,c3,true", "b1,c4,false", "a1,a3,false", "a7,a6,true"})
    void calcalateScore(String sourceKey, String targetKey, boolean expect) {
        //given
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator(BoardGenerator.Template.EMPTY_BOARD.getTemplate()));

        //when
        boolean actual = chessBoard.move(sourceKey, targetKey);

        //then
        assertThat(actual).isEqualTo(expect);
    }
}