package chess.domain.board;

import static chess.fixture.PositionFixture.E4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BoardTest {

    @Test
    void 불가능한_이동_커맨드를_입력받는_경우_예외를_던진다() {
        // given
        final Board board = BoardInitializer.initialize();

        // expect
        assertThatThrownBy(() -> board.move("d2", "d5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 이동 명령어입니다.");
    }

    @Test
    void 이동_가능한_커맨드를_입력받는_경우_기물을_이동한다() {
        // given
        final Board board = BoardInitializer.initialize();

        // when
        board.move("e2", "e4");

        // then
        assertThat(board.getBoard()).containsEntry(Position.from( "e4"), Pawn.from(Color.WHITE));
    }

    @Test
    void 상대편의_기물을_이동하려는_경우_예외를_던진다() {
        // given
        final Board board = BoardInitializer.initialize();

        // expect
        assertThatThrownBy(() -> board.move("g7", "g6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대방의 기물을 움직일 수 없습니다.");
    }

    @Test
    void 이동_경로에_기물이_있는_경우_예외를_던진다() {
        // given
        final Board board = BoardInitializer.initialize();

        // expect
        assertThatThrownBy(() -> board.move("d1", "d4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 기물이 존재합니다.");
    }
}
