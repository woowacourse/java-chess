package chess.domain;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamScoreTest {

    @Test
    @DisplayName("게임 점수 계산")
    void calculateScore() {
        ChessBoard chessBoard = new ChessBoard();
        Map<Color, Double> teamScore = TeamScore.calculateTeamScore(chessBoard.getChessBoard());
        assertThat(teamScore.get(Color.BLACK)).isEqualTo(38);
        assertThat(teamScore.get(Color.WHITE)).isEqualTo(38);

        chessBoard.movePiece(Square.of("c2"), Square.of("c4"));
        chessBoard.movePiece(Square.of("d7"), Square.of("d5"));
        chessBoard.movePiece(Square.of("c4"), Square.of("d5"));

        teamScore = TeamScore.calculateTeamScore(chessBoard.getChessBoard());
        assertThat(teamScore.get(Color.BLACK)).isEqualTo(37);
        assertThat(teamScore.get(Color.WHITE)).isEqualTo(37);
    }

}
