package model.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.InvalidStatusException;
import java.util.List;
import model.GameBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @Test
    @DisplayName("종료 상태에서 play하면 예외가 발생한다.")
    void invalidPlay() {
        //given
        final GameBoard gameBoard = new GameBoard();
        final GameStatus gameStatus = Initialization.gameSetting(List.of("start"));
        final List<String> endCommand = List.of("end");

        //when
        final GameStatus play = gameStatus.play(endCommand, gameBoard);

        //then
        assertThatThrownBy(() -> play.play(endCommand, gameBoard))
                .isInstanceOf(InvalidStatusException.class);
    }

    @Test
    @DisplayName("종료 상태면 isRunning 이 false 이다.")
    void isNotRunning() {
        final GameStatus gameStatus = Initialization.gameSetting(List.of("end"));

        assertThat(gameStatus.isRunning()).isFalse();
    }
}
