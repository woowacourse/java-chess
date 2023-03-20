package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class GameResultTest {

    @Test
    void 기물의_총_점수를_반환한다() {
        // given
        Board board = new Start();
        board = board.initialize();
        final GameResult result = board.getResult();

        // when
        final double score = result.score(Color.WHITE);

        // then
        assertThat(score).isEqualTo(38.0);
    }

    @Test
    void 하나의_File에_여러_개의_폰이_존재하는_경우_각폰의_점수가_반이_된다() {
        // given
        Board board = new Start();
        board = board.initialize();
        board = board.move("e2", "e4");
        board = board.move("d7", "d5");
        board = board.move("e4", "d5");
        final GameResult result = board.getResult();

        // when
        final double score = result.score(Color.WHITE);

        // then
        assertThat(score).isEqualTo(37.0);
    }

    @Test
    void 왕이_잡힌_경우_왕을_잡은쪽이_승리한다() {
        // given
        Board board = new Start();
        board = board.initialize();
        board = board.move("e2", "e4");
        board = board.move("e7", "e5");
        board = board.move("d1", "h5");
        board = board.move("f7", "f5");
        board = board.move("h5", "e8");
        final GameResult result = board.getResult();

        // when
        final Color winner = result.winner();

        // then
        assertThat(winner).isEqualTo(Color.WHITE);
    }

    @Test
    void 왕이_잡히지_않은_경우_점수를_비교하여_결과를_계산한다() {
        // given
        Board board = new Start();
        board = board.initialize();
        board = board.move("d2", "d4");
        board = board.move("b8", "c6");
        board = board.move("c1", "f4");
        board = board.move("c6", "d4");
        final GameResult result = board.getResult();

        // when
        final Color winner = result.winner();

        // then
        assertThat(winner).isEqualTo(Color.BLACK);
    }

    @Test
    void 양쪽다_왕이_살아있고_점수가_동일할_경우_EMPTY를_반환한다() {
        // given
        Board board = new Start();
        board = board.initialize();
        final GameResult result = board.getResult();

        // when
        final Color winner = result.winner();

        // then
        assertThat(winner).isEqualTo(Color.EMPTY);
    }
}
