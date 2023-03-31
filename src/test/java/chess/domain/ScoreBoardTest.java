package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.board.Board;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreBoardTest {

    @Test
    @DisplayName("점수를 계산한다")
    void BasicPoint() {
        Board board = Board.create();
        board.replace(Position.from("a2"), Position.from("a8"));
        board.replace(Position.from("a8"), Position.from("b8"));
        board.replace(Position.from("b8"), Position.from("c8"));
        board.replace(Position.from("c8"), Position.from("g8"));

            /*
            ...QKBpR
            PPPPPPPP
            ........
            ........
            ........
            ........
            .ppppppp
            rnbqkbnr
             */
        ScoreBoard scoreBoard = board.getScoreBoard();
        Map<Color, Double> score = scoreBoard.getScore();
        double blackScore = score.get(Color.BLACK);
        assertThat(blackScore).isEqualTo(25);
    }

    @Test
    @DisplayName("같은 세로줄에 같은 색의 폰이 있는 경우의 점수를 계산한다")
    void DoublePawnPoint() {
        Board board = Board.create();
        board.replace(Position.from("a2"), Position.from("b3"));

            /*
            RNBQKBNR
            PPPPPPPP
            ........
            ........
            ........
            .p......
            .ppppppp
            rnbqkbnr
            */
        ScoreBoard scoreBoard = board.getScoreBoard();
        Map<Color, Double> score = scoreBoard.getScore();
        double whiteScore = score.get(Color.WHITE);
        assertThat(whiteScore).isEqualTo(37);
    }
}
