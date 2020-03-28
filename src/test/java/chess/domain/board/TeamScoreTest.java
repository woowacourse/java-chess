package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TeamScoreTest {

    @Test
    @DisplayName("게임 점수 계산")
    void calculateScore() {
        ChessBoard chessBoard = new ChessBoard();
        TeamScore teamScore = chessBoard.getTeamScore();
        Map<Color, Double> teamScores = teamScore.getTeamScore();
        assertThat(teamScores.get(Color.BLACK)).isEqualTo(38);
        assertThat(teamScores.get(Color.WHITE)).isEqualTo(38);

        chessBoard.movePiece(Arrays.asList(BoardSquare.of("c2"), BoardSquare.of("c4")));
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("d7"), BoardSquare.of("d5")));
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("c4"), BoardSquare.of("d5")));

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

        chessBoard.movePiece(Arrays.asList(BoardSquare.of("b1"), BoardSquare.of("c3")));
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("d7"), BoardSquare.of("d5")));
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("c3"), BoardSquare.of("d5")));
        teamScore = chessBoard.getTeamScore();
        assertThat(teamScore.getWinners().size()).isEqualTo(1);
        assertThat(teamScore.getWinners().get(0)).isEqualTo(Color.WHITE);
    }
}
