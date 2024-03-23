package model.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.InvalidStatusException;
import java.util.List;
import model.ChessBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @DisplayName("종료 상태에서 play하면 예외가 발생한다.")
    @Test
    void failToPlayIfStatusEnd() {
        //given
        final ChessBoard chessBoard = ChessBoard.setupStartingPosition();
        final GameStatus gameStatus = Initialization.gameSetting(List.of("start"));
        final List<String> endCommand = List.of("end");

        //when
        final GameStatus play = gameStatus.play(endCommand, chessBoard);

        //then
        assertThatThrownBy(() -> play.play(endCommand, chessBoard))
                .isInstanceOf(InvalidStatusException.class);
    }

    @DisplayName("종료 상태일 때 상태를 확인한다.")
    @Test
    void checkRunning() {
        final GameStatus gameStatus = Initialization.gameSetting(List.of("end"));

        assertThat(gameStatus.isRunning()).isFalse();
    }
}
