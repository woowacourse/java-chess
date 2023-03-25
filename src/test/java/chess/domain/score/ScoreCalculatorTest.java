package chess.domain.score;

import chess.domain.Position;
import chess.domain.boardStrategy.InitialBoardStrategy;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ScoreCalculatorTest {

    @Test
    void 초기_점수_테스트() {
        //given
        ChessGame chessGame = new ChessGame(new InitialBoardStrategy());
        Map<Position, Piece> chessBoard = chessGame.getChessBoard();
        ScoreCalculator scoreCalculator = new ScoreCalculator();

        //when
        scoreCalculator.calculateScores(chessBoard);
        double expectedScore = 38.0;
        double blackScore = scoreCalculator.getBlackScore();
        double whiteScore = scoreCalculator.getWhiteScore();

        //then
        assertEquals(expectedScore, blackScore);
        assertEquals(expectedScore, whiteScore);
    }
}
