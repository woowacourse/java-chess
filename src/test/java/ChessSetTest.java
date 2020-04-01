import chess.board.ChessBoardCreater;
import chess.game.ChessSet;
import chess.board.ChessBoard;
import chess.score.Score;
import chess.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessSetTest {
    @DisplayName("스코어 계산")
    @Test
    void calculateScore() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        ChessSet chessSet = new ChessSet(chessBoard.giveMyPiece(Team.BLACK));

        Score result = chessSet.calculateScoreExceptPawnReduce();
        assertThat(result).isEqualTo(new Score(38));
    }
}