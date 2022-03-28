package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.domain.board.BoardInitializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackWinTest {

    @DisplayName("흑팀의 승리인지 확인한다.")
    @Test
    void isBlackWin() {
        BlackWin blackWin = new BlackWin(new Board(BoardInitializer.initBoard()));

        assertThat(blackWin.findWinner()).isEqualTo(Winner.BLACK);
    }

    @DisplayName("흑팀이 백팀의 킹을 잡을 경우 흑팀이 승리한다.")
    @Test
    void blackWin() {
        GameState state = new WhiteTurn(new Board(BoardInitializer.initBoard()));
        state = state.move(new Position("d2"), new Position("d4"));
        state = state.move(new Position("c7"), new Position("c5"));
        state = state.move(new Position("e2"), new Position("e4"));
        state = state.move(new Position("d8"), new Position("a5"));
        state = state.move(new Position("f2"), new Position("f4"));
        state = state.move(new Position("a5"), new Position("e1"));

        assertThat(state).isInstanceOf(BlackWin.class);
    }
}
