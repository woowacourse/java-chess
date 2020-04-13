package chess.game;

import chess.result.ChessScores;
import chess.score.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {
    @Test
    void calculateScores() {
        ChessGame chessGame = new ChessGame();
        ChessScores chessScores = chessGame.calculateScores();

        assertThat(chessScores).isEqualTo(new ChessScores(new Score(38), new Score(38)));
    }
}