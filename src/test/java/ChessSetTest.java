import chess.ChessSet;
import chess.Player;
import chess.board.ChessBoard;
import chess.board.Location;
import chess.score.Score;
import chess.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChessSetTest {
    @DisplayName("스코어 계산")
    @Test
    void calculateScore() {
        ChessBoard chessBoard = new ChessBoard();
        ChessSet chessSet = new ChessSet(chessBoard.giveMyPiece(Team.BLACK.isBlack()));

        Score result = chessSet.calculateScoreExceptPawnReduce();
        assertThat(result).isEqualTo(new Score(38));
        System.out.println(chessSet.remove(new Location(7, 'a')));

        Score result2 = chessSet.calculateScoreExceptPawnReduce();
        assertThat(result2.getValue()).isEqualTo(new Score(37).getValue());
    }
}