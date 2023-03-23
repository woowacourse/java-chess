package chess.domain.score;

import chess.domain.Position;
import chess.domain.boardStrategy.InitialBoardStrategy;
import chess.domain.game.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ScoreCalculatorTest {

    @Test
    void calculateScores() {
        //given
        ChessGame chessGame = new ChessGame(new InitialBoardStrategy());
        Map<Position, Piece> chessBoard = chessGame.getChessBoard();
        ScoreCalculator scoreCalculator = new ScoreCalculator();

        //when
        scoreCalculator.calculateScores(chessBoard);
        double blackScore = scoreCalculator.getBlackScore();
        double whiteScore = scoreCalculator.getWhiteScore();

        //then
    }

    @Test
    void checkPawnScore() {
        //given
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        //when

        //then
    }
}
