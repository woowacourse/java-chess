package chess.domain.board;

import static chess.fixture.PositionFixture.B8;
import static chess.fixture.PositionFixture.C1;
import static chess.fixture.PositionFixture.C6;
import static chess.fixture.PositionFixture.D1;
import static chess.fixture.PositionFixture.D2;
import static chess.fixture.PositionFixture.D4;
import static chess.fixture.PositionFixture.D5;
import static chess.fixture.PositionFixture.D7;
import static chess.fixture.PositionFixture.E2;
import static chess.fixture.PositionFixture.E4;
import static chess.fixture.PositionFixture.E5;
import static chess.fixture.PositionFixture.E7;
import static chess.fixture.PositionFixture.E8;
import static chess.fixture.PositionFixture.F4;
import static chess.fixture.PositionFixture.F5;
import static chess.fixture.PositionFixture.F7;
import static chess.fixture.PositionFixture.H5;
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
        Board board = new InitialState();
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
        Board board = new InitialState();
        board = board.initialize();
        board = board.move(E2, E4);
        board = board.move(D7, D5);
        board = board.move(E4, D5);
        final GameResult result = board.getResult();

        // when
        final double score = result.score(Color.WHITE);

        // then
        assertThat(score).isEqualTo(37.0);
    }

    @Test
    void 왕이_잡힌_경우_왕을_잡은쪽이_승리한다() {
        // given
        Board board = new InitialState();
        board = board.initialize();
        board = board.move(E2, E4);
        board = board.move(E7, E5);
        board = board.move(D1, H5);
        board = board.move(F7, F5);
        board = board.move(H5, E8);
        final GameResult result = board.getResult();

        // when
        final Color winner = result.winner();

        // then
        assertThat(winner).isEqualTo(Color.WHITE);
    }

    @Test
    void 왕이_잡히지_않은_경우_점수를_비교하여_결과를_계산한다() {
        // given
        Board board = new InitialState();
        board = board.initialize();
        board = board.move(D2, D4);
        board = board.move(B8, C6);
        board = board.move(C1, F4);
        board = board.move(C6, D4);
        final GameResult result = board.getResult();

        // when
        final Color winner = result.winner();

        // then
        assertThat(winner).isEqualTo(Color.BLACK);
    }

    @Test
    void 양쪽다_왕이_살아있고_점수가_동일할_경우_EMPTY를_반환한다() {
        // given
        Board board = new InitialState();
        board = board.initialize();
        final GameResult result = board.getResult();

        // when
        final Color winner = result.winner();

        // then
        assertThat(winner).isEqualTo(Color.EMPTY);
    }
}
