package chess.domain.command;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoveTest {
    @Test
    void Move_커맨드_생성_확인() {
        Command waiting = new Waiting();
        Command move = waiting.changeRunningCommand("move a2 a4");

        assertThat(move).isInstanceOf(Move.class);
    }

    @Test
    void Move_커맨드_생성_에러() {
        Command waiting = new Waiting();

        assertThatThrownBy(() -> {
            waiting.changeRunningCommand("move a23 a45");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Waiting_커맨드_변경_확인() {
        Command waiting = new Waiting();
        Command move = waiting.changeRunningCommand("move a2 a4");

        assertThat(move.move(ChessBoard.generate(), Color.WHITE)).isInstanceOf(Waiting.class);
    }
}
