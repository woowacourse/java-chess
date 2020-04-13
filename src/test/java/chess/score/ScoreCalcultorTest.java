package chess.score;

import chess.board.ChessBoard;
import chess.board.ChessBoardCreater;
import chess.game.ChessSet;
import chess.player.Player;
import org.junit.jupiter.api.Test;

import static chess.team.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class ScoreCalcultorTest {
    @Test
    void calculate() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        Calculatable scoreCalculator = new ScoreCalculator();
        ChessSet chessSet = new ChessSet(chessBoard.giveMyPiece(WHITE));
        Score result = scoreCalculator.calculate(chessSet);
        assertThat(result).isEqualTo(new Score(38));
    }
}