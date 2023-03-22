package chess.gamecommand;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @Test
    @DisplayName("게임 종료 상태에서 시작 시 예외를 던진다.")
    void start() {
        // given
        End end = new End();

        // when, then
        assertThatThrownBy(() -> end.start())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임 종료 상태에서는 시작할 수 없습니다.");
    }

    @Test
    @DisplayName("게임 종료 상태에서 이동 시 예외를 던진다.")
    void move() {
        // given
        End end = new End();
        Position sourcePosition = new Position(File.A, Rank.TWO);
        Position targetPosition = new Position(File.A, Rank.FOUR);

        // when, then
        assertThatThrownBy(() -> end.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임 종료 상태에서는 기물을 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("게임 종료 상태에서 종료 시 예외를 던진다.")
    void end() {
        // given
        End end = new End();

        // when, then
        assertThatThrownBy(() -> end.end())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임 종료 상태에서는 종료할 수 없습니다.");
    }

    @Test
    @DisplayName("게임 종료 상태에서 기물들을 가져올 시 예외를 던진다.")
    void getPieces() {
        // given
        End end = new End();

        // when, then
        assertThatThrownBy(() -> end.getPieces())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임 종료 상태에서는 기물들을 반환할 수 없습니다.");
    }

    @Test
    @DisplayName("게임 종료 상태에서 턴 이름을 가져올 시 예외를 던진다.")
    void getTurnDisplayName() {
        // given
        End end = new End();

        // when, then
        assertThatThrownBy(() -> end.getTurnDisplayName())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 종료 상태에서는 턴 이름을 반환할 수 없습니다.");
    }
}
