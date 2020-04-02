package chess.domain;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardFactory;
import chess.domain.chessBoard.ChessCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessCalculatorTest {

    @ParameterizedTest
    @NullSource
    void calculateScoreOf_nullChessBoard_ExceptionThrown(ChessBoard chessBoard) {
        assertThatThrownBy(() -> ChessCalculator.calculateScoreOf(chessBoard))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("체스 보드가 null입니다.");
    }

    @Test
    void calculateScoreOf_ChessBoard_returnScore() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.create());

        assertThat(ChessCalculator.calculateScoreOf(chessBoard)).isEqualTo(34d);
    }
}