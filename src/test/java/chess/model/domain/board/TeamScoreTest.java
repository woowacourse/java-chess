package chess.model.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.domain.piece.Color;
import chess.model.domain.piece.King;
import chess.model.domain.piece.Piece;
import chess.model.domain.piece.Rook;
import chess.model.domain.state.MoveSquare;
import java.util.HashMap;
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
        ChessGame chessGame = new ChessGame();
        TeamScore teamScore = chessGame.getTeamScore();
        Map<Color, Double> teamScores = teamScore.getTeamScore();
        assertThat(teamScores.get(Color.BLACK)).isEqualTo(38);
        assertThat(teamScores.get(Color.WHITE)).isEqualTo(38);

        chessGame.movePieceWhenCanMove(new MoveSquare("c2", "c4"));
        chessGame.movePieceWhenCanMove(new MoveSquare("d7", "d5"));
        chessGame.movePieceWhenCanMove(new MoveSquare("c4", "d5"));

        teamScore = chessGame.getTeamScore();
        teamScores = teamScore.getTeamScore();
        assertThat(teamScores.get(Color.BLACK)).isEqualTo(37);
        assertThat(teamScores.get(Color.WHITE)).isEqualTo(37);
    }

    @Test
    @DisplayName("승자 구하기")
    void getWinnerByScore() {
        ChessGame chessGame = new ChessGame();
        TeamScore teamScore = chessGame.getTeamScore();
        assertThat(teamScore.getWinners().size()).isEqualTo(2);

        chessGame.movePieceWhenCanMove(new MoveSquare("b1", "c3"));
        chessGame.movePieceWhenCanMove(new MoveSquare("d7", "d5"));
        chessGame.movePieceWhenCanMove(new MoveSquare("c3", "d5"));

        teamScore = chessGame.getTeamScore();
        assertThat(teamScore.getWinners().size()).isEqualTo(1);
        assertThat(teamScore.getWinners().get(0)).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("킹 잡혔을 때 0점 처리")
    void noKingZero() {
        Map<BoardSquare, Piece> boardInitial = new HashMap<>();
        boardInitial.put(BoardSquare.of("e1"), King.getPieceInstance(Color.WHITE));
        boardInitial.put(BoardSquare.of("a8"), Rook.getPieceInstance(Color.BLACK));
        boardInitial.put(BoardSquare.of("h8"), Rook.getPieceInstance(Color.BLACK));
        boardInitial.put(BoardSquare.of("a1"), Rook.getPieceInstance(Color.WHITE));
        boardInitial.put(BoardSquare.of("h1"), Rook.getPieceInstance(Color.WHITE));
        ChessGame chessGame = new ChessGame(new BoardInitialTestUse(boardInitial), Color.WHITE,
            CastlingSetting.getCastlingElements());

        TeamScore teamScore = chessGame.getTeamScore();

        assertThat(teamScore.getWinners().contains(Color.WHITE)).isTrue();
        assertThat(teamScore.getWinners().size()).isEqualTo(1);

        assertThat(teamScore.getTeamScore().get(Color.BLACK)).isEqualTo(0.0);
        assertThat(teamScore.getTeamScore().get(Color.WHITE)).isEqualTo(10.0);
    }
}
