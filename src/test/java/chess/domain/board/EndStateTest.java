package chess.domain.board;

import static chess.fixture.PositionFixture.D1;
import static chess.fixture.PositionFixture.E2;
import static chess.fixture.PositionFixture.E4;
import static chess.fixture.PositionFixture.E5;
import static chess.fixture.PositionFixture.E7;
import static chess.fixture.PositionFixture.E8;
import static chess.fixture.PositionFixture.F5;
import static chess.fixture.PositionFixture.F7;
import static chess.fixture.PositionFixture.H5;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class EndStateTest {

    @Test
    void 왕이_잡히는_경우_게임이_종료된다() {
        // given
        Board board = new InitialState();
        board = board.initialize();
        board = board.move(E2, E4);
        board = board.move(E7, E5);
        board = board.move(D1, H5);
        board = board.move(F7, F5);

        // when
        board = board.move(H5, E8);

        // then
        assertThat(board.isEnd()).isTrue();
    }
}
