package chess.domain.game.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class PlayingTest {
    @Test
    @DisplayName("플레잉 생성")
    void constructor() {
        assertThat(new Playing(Board.create(), Turn.from(Color.WHITE))).isInstanceOf(Playing.class);
    }

    @Test
    @DisplayName("게임 진행중 시작시 예외 발생")
    void start() {
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
            () -> new Playing(Board.create(), Turn.from(Color.WHITE)).start());
    }

    @Test
    @DisplayName("게임 종료 상태 생성")
    void end() {
        assertThat(new Playing(Board.create(), Turn.from(Color.WHITE)).end()).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("게임 중 체스 말 이동")
    void move() {
        assertThat(new Playing(Board.create(), Turn.from(Color.WHITE)).move(Position.from("a2"), Position
            .from("a3"))).isInstanceOf(State.class);
    }

    @Test
    @DisplayName("게임 중 체스판 확인")
    void board() {
        assertThat(new Playing(Board.create(), Turn.from(Color.WHITE)).board()).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("게임 중 상태는 종료 상태가 아니다")
    void isFinished() {
        assertThat(new Playing(Board.create(), Turn.from(Color.WHITE)).isFinished()).isFalse();
    }
}
