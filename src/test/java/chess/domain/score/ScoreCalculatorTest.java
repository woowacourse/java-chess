package chess.domain.score;

import chess.domain.board.strategy.InitialBoardStrategy;
import chess.domain.game.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ScoreCalculatorTest {

    @Test
    void 초기_점수_테스트() {
        //given
        ChessGame chessGame = new ChessGame(new InitialBoardStrategy());
        ChessBoard chessBoard = new ChessBoard(chessGame.getChessBoardMap());
        ScoreCalculator scoreCalculator = new ScoreCalculator();

        //when
        double expectedScore = 38.0;
        double blackScore = scoreCalculator.getScoreByColor(chessBoard, Color.BLACK);
        double whiteScore = scoreCalculator.getScoreByColor(chessBoard, Color.WHITE);

        //then
        assertAll(
                () -> assertEquals(expectedScore, blackScore),
                () -> assertEquals(expectedScore, whiteScore)
        );
    }
}
