package chess.score;

import chess.board.ChessBoard;
import chess.board.ChessBoardCreater;
import chess.game.ChessSet;
import chess.player.Player;
import org.junit.jupiter.api.Test;

import static chess.team.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreCalcultorTest {
    @Test
    void calculate() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        Calculatable scoreCalculator = new ScoreCalcultor();
        Player player = new Player(new ChessSet(chessBoard.giveMyPiece(WHITE)), WHITE);
        Score result = scoreCalculator.calculate(chessBoard, player);
        assertThat(result.getValue()).isEqualTo(new Score(38).getValue());
    }
}