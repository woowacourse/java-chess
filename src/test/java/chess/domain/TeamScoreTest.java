package chess.domain;

import chess.domain.piece.Color;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamScoreTest {
    @Test
    @DisplayName("게임 점수 계산")
    void calculateScore() {
        ChessBoard chessBoard = new ChessBoard();
        TeamScore teamScore = new TeamScore();
        teamScore.updateTeamScore(chessBoard);
        Map<Color, Double> currentScore = teamScore.getTeamScore();
        assertThat(currentScore.get(Color.BLACK)).isEqualTo(38);
        assertThat(currentScore.get(Color.WHITE)).isEqualTo(38);

        chessBoard.movePiece(Arrays.asList(Square.of("c2"), Square.of("c4")));
        chessBoard.movePiece(Arrays.asList(Square.of("d7"), Square.of("d5")));
        chessBoard.movePiece(Arrays.asList(Square.of("c4"), Square.of("d5")));

        teamScore.updateTeamScore(chessBoard);
        currentScore = teamScore.getTeamScore();
        assertThat(currentScore.get(Color.BLACK)).isEqualTo(37);
        assertThat(currentScore.get(Color.WHITE)).isEqualTo(37);
    }

    @Test
    @DisplayName("승자 구하기")
    void calculateWinnerByScore() {
        ChessBoard chessBoard = new ChessBoard();
        TeamScore teamScore = new TeamScore();
        teamScore.updateTeamScore(chessBoard);
        assertThat(teamScore.getWinners().size()).isEqualTo(2);

        chessBoard.movePiece(Arrays.asList(Square.of("b1"), Square.of("c3")));
        chessBoard.movePiece(Arrays.asList(Square.of("d7"), Square.of("d5")));
        chessBoard.movePiece(Arrays.asList(Square.of("c3"), Square.of("d5")));

        teamScore.updateTeamScore(chessBoard);
        assertThat(teamScore.getWinners().size()).isEqualTo(1);
        assertThat(teamScore.getWinners().get(0)).isEqualTo(Color.WHITE);
    }
}
