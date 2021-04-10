package chess.domain.player;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Owner;
import chess.domain.piece.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoresTest {

    private ChessGame chessGame;
    private Board board;

    @BeforeEach
    void initGame() {
        chessGame = new ChessGame();
        board = chessGame.board();
    }

    @DisplayName("시작 점수는 38점이다.")
    @Test
    void testStartScore() {
        final Scores scores = chessGame.scores();
        for (final Player player : scores.players()) {
            assertThat(player.score(board)).isEqualTo(new Score(38.0d));
        }
    }

    @DisplayName("같은 수직 라인에 있지 않은 폰은 1점으로 취급한다.")
    @Test
    void testScoreLosingPawn() {
        assertThat(getScoreOf(Owner.BLACK)).isEqualTo(new Score(38.0d));

        chessGame.move(new Position("a2"), new Position("a4"));
        chessGame.move(new Position("b7"), new Position("b5"));
        chessGame.move(new Position("a4"), new Position("b5"));

        assertThat(getScoreOf(Owner.BLACK)).isEqualTo(new Score(37.0d));
    }

    @DisplayName("같은 수직 라인에 있는 폰은 0.5점으로 취급한다.")
    @Test
    void testScoreLosingPawnInVerticalLine() {
        assertThat(getScoreOf(Owner.WHITE)).isEqualTo(new Score(38.0d));

        chessGame.move(new Position("a2"), new Position("a4"));
        chessGame.move(new Position("b7"), new Position("b5"));
        chessGame.move(new Position("a4"), new Position("b5"));

        assertThat(getScoreOf(Owner.WHITE)).isEqualTo(new Score(37.0d));
    }

    @DisplayName("왕이 죽으면 점수는 다른 기물에 상관없이 0점이다.")
    @Test
    void testScoreIfKingDead() {
        chessGame.move(new Position("c7"), new Position("c6"));
        chessGame.move(new Position("d8"), new Position("a5"));
        chessGame.move(new Position("d2"), new Position("d3"));
        chessGame.move(new Position("a5"), new Position("e1"));

        assertThat(getScoreOf(Owner.WHITE)).isEqualTo(new Score(0d));
    }

    private Score getScoreOf(final Owner owner) {
        final Scores scores = chessGame.scores();

        final Player searched = scores.players()
                .stream()
                .filter(player -> player.isOwner(owner))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return scores.get(searched);
    }
}