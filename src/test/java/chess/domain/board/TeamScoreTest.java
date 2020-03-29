package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.state.MoveSquare;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TeamScoreTest {

    @Test
    @DisplayName("Null이 생성자에 들어갔을 때 예외 발생")
    void validNotNull() {
        assertThatThrownBy(() -> new TeamScore(null, null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Null");
    }

    @Test
    @DisplayName("게임 점수 계산")
    void calculateScore() {
        ChessBoard chessBoard = new ChessBoard();
        TeamScore teamScore = chessBoard.getTeamScore();
        Map<Color, Double> teamScores = teamScore.getTeamScore();
        assertThat(teamScores.get(Color.BLACK)).isEqualTo(38);
        assertThat(teamScores.get(Color.WHITE)).isEqualTo(38);

        chessBoard.movePieceWhenCanMove(new MoveSquare("c2", "c4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("d7", "d5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("c4", "d5"));

        teamScore = chessBoard.getTeamScore();
        teamScores = teamScore.getTeamScore();
        assertThat(teamScores.get(Color.BLACK)).isEqualTo(37);
        assertThat(teamScores.get(Color.WHITE)).isEqualTo(37);
    }

    @Test
    @DisplayName("승자 구하기")
    void getWinnerByScore() {
        ChessBoard chessBoard = new ChessBoard();
        TeamScore teamScore = chessBoard.getTeamScore();
        assertThat(teamScore.getWinners().size()).isEqualTo(2);

        chessBoard.movePieceWhenCanMove(new MoveSquare("b1", "c3"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("d7", "d5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("c3", "d5"));

        teamScore = chessBoard.getTeamScore();
        assertThat(teamScore.getWinners().size()).isEqualTo(1);
        assertThat(teamScore.getWinners().get(0)).isEqualTo(Color.WHITE);
    }
}
