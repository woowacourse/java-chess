package chess.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.pieces.Empty;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Team;
import chess.factory.BoardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("초기 판의 말의 점수는 38점이다.")
    void init_chessBoard_score_38() {
        // given
        ChessGame chessGame = new ChessGame(BoardFactory.createBoard());

        // when
        Score score = chessGame.calculateScore();

        // then
        assertThat(score.getScoreTable().get(Team.WHITE)).isEqualTo(38);
        assertThat(score.getScoreTable().get(Team.BLACK)).isEqualTo(38);
    }

    @Test
    @DisplayName("같은 열의 같은 팀 Pawn이 존재하면 0.5점으로 계산한다.")
    void calculateScore_when() {
        // given
        Board board = BoardFactory.createBoard();
        board.getBoard().put(Position.from("b3"), new Pawn(Team.WHITE));
        board.getBoard().put(Position.from("b4"), new Pawn(Team.WHITE));
        board.getBoard().put(Position.from("a2"), new Empty(Team.EMPTY));
        board.getBoard().put(Position.from("c2"), new Empty(Team.EMPTY));
        ChessGame chessGame = new ChessGame(board);

        // when
        Score score = chessGame.calculateScore();

        // then
        assertThat(score.getScoreTable().get(Team.WHITE)).isEqualTo(36.5);
        assertThat(score.getScoreTable().get(Team.BLACK)).isEqualTo(38);
    }
}