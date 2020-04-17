package chess.domain.status;

import chess.domain.board.Board;
import chess.domain.board.DefaultBoardInitializer;
import chess.domain.board.ManipulatedBoardInitializer;
import chess.domain.player.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatusTest {
    private Board defaultBoard = Board.of(new DefaultBoardInitializer());
    private Board manipulatedBoard = Board.of(new ManipulatedBoardInitializer());

    @Test
    void create() {
        assertThat(Status.of(defaultBoard))
                .isInstanceOf(Status.class);
    }

    @Test
    void defaultStatus() {
        assertThat(Status.of(defaultBoard).getStatus().get(Player.BLACK)).isEqualTo(38);
        assertThat(Status.of(defaultBoard).getStatus().get(Player.WHITE)).isEqualTo(38);
    }

    @Test
    void status() {
        assertThat(Status.of(manipulatedBoard).getStatus().get(Player.BLACK)).isEqualTo(5);
        assertThat(Status.of(manipulatedBoard).getStatus().get(Player.WHITE)).isEqualTo(0);
    }

    @Test
    void winner() {
        assertThat(Status.of(manipulatedBoard).getWinner()).isEqualTo(Player.BLACK);
    }
}