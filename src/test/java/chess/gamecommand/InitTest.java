package chess.gamecommand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.command.CommandStatus;
import chess.domain.command.End;
import chess.domain.command.Init;
import chess.domain.command.Play;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitTest {

    @Test
    @DisplayName("초기 상태에서 시작 시 플레이 상태로 전이된다.")
    void start() {
        // given
        CommandStatus commandStatus = new Init();

        // when, then
        assertThat(commandStatus.start()).isInstanceOf(Play.class);
    }

    @Test
    @DisplayName("초기 상태에서 move 시 예외를 던진다.")
    void move() {
        // given
        CommandStatus commandStatus = new Init();
        Position sourcePosition = new Position(File.A, Rank.TWO);
        Position targetPosition = new Position(File.A, Rank.FOUR);

        // when, then
        assertThatThrownBy(() -> commandStatus.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 초기 상태에서는 기물을 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("초기 상태에서 시작 시 종료 상태로 전이된다.")
    void end() {
        // given
        CommandStatus commandStatus = new Init();

        // when, then
        assertThat(commandStatus.end()).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("초기 상태에서 보드를 가져올 시 예외를 던진다.")
    void getPieces() {
        // given
        CommandStatus commandStatus = new Init();

        // when, then
        assertThatThrownBy(() -> commandStatus.getPieces())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 초기 상태에서는 기물들을 반환할 수 없습니다.");
    }

    @Test
    @DisplayName("초기 상태에서 턴 이름을 가져올 시 예외를 던진다.")
    void getTurnDisplayName() {
        // given
        CommandStatus commandStatus = new Init();

        // when, then
        assertThatThrownBy(() -> commandStatus.getTurnDisplayName())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 초기 상태에서는 턴 이름을 반환할 수 없습니다.");
    }
}
