package chess.command;

import chess.board.ChessBoard;
import chess.game.ChessGame;
import chess.location.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MoveCommandTest {

    @Test
    @DisplayName("정적 팩토리 메서드 테스트")
    void of() {
        String input = "move a_1 a_2";
        assertThat(MoveCommand.of(input, new ChessGame()))
                .isInstanceOf(MoveCommand.class);
    }

    // 하나의 테스트는 하나의 개념만을 테스트해야한다.
    // 두 가지의 테스트가 모두 하나의 개념인가 ? ==> O
    @Test
    @DisplayName("현재, 목적지 로케이션 프로퍼티 확인")
    void of2() {
        String input = "move a_1 a_2";
        MoveCommand moveCommand = MoveCommand.of(input, new ChessGame());

        Location now = moveCommand.getNow();
        Location destination = moveCommand.getDestination();

        assertThat(now).isEqualTo(new Location(1, 'a'));
        assertThat(destination).isEqualTo(new Location(2, 'a'));
    }
}