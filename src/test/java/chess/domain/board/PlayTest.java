package chess.domain.board;

import static chess.fixture.PositionFixture.E4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PlayTest {

    @Test
    void 불가능한_이동_커맨드를_입력받는_경우_예외를_던진다() {
        // given
        Board board = new Start();
        board = board.initialize();

        // expect
        final Board finalBoard = board;
        assertThatThrownBy(() -> finalBoard.move("d2", "d5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 이동 명령어 입니다.");
    }

    @Test
    void 이동_가능한_커맨드를_입력받는_경우_기물을_이동한다() {
        // given
        Board board = new Start();
        board = board.initialize();

        // when
        board = board.move("e2", "e4");

        // then
        assertThat(board.getBoard().get(E4)).isEqualTo(Pawn.from(Color.WHITE));
    }

    @Test
    void 상대편의_기물을_이동하려는_경우_예외를_던진다() {
        // given
        Board board = new Start();
        board = board.initialize();

        // expect
        final Board finalBoard = board;
        assertThatThrownBy(() -> finalBoard.move("g7", "g6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대방의 기물을 움직일 수 없습니다.");
    }

    @Test
    void 이동_경로에_기물이_있는_경우_예외를_던진다() {
        // given
        Board board = new Start();
        board = board.initialize();

        // expect
        final Board finalBoard = board;
        assertThatThrownBy(() -> finalBoard.move("d1", "d4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 기물이 있을 수 없습니다.");
    }

    @Test
    void 게임이_종료_되었는지_확인한다() {
        // given
        Board board = new Start();
        board = board.initialize();

        // when
        final boolean result = board.isEnd();

        // then
        assertThat(result).isFalse();
    }
}
