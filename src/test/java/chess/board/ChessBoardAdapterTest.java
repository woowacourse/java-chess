package chess.board;

import chess.manager.ChessManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardAdapterTest {

    @DisplayName("체스말이 움직였는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"b1,c3,true", "b1,c4,false", "a1,a3,false", "a7,a6,true"})
    void moveTest(String sourceKey, String targetKey, boolean expect) {
        //given
        ChessBoardAdapter chessBoard = BoardGenerator.create();
        ChessManager chessManager = new ChessManager(chessBoard);

        //when
        boolean actual = chessBoard.move(sourceKey, targetKey);

        //then
        assertThat(actual).isEqualTo(expect);
    }

}