package chess.domain.board.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Rank;
import chess.domain.piece.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteWinTest {

    @DisplayName("흑팀의 승리가 아닌지 확인한다.")
    @Test
    void isBlackWin() {
        Map<Integer, Rank> ranks = new HashMap<>();
        WhiteWin whiteWin = new WhiteWin(ranks);

        assertThat(whiteWin.findWinner()).isEqualTo(Winner.WHITE);
    }

    @DisplayName("백팀이 흑팀의 킹을 잡을 경우 백팀이 승리한다.")
    @Test
    void whiteWin() {
        BoardState state = BoardInitializer.initBoard();
        state = state.move(new Position("c2"), new Position("c4"));
        state = state.move(new Position("d7"), new Position("d5"));
        state = state.move(new Position("d1"), new Position("a4"));
        state = state.move(new Position("h7"), new Position("h5"));
        state = state.move(new Position("a4"), new Position("e8"));

        assertThat(state).isInstanceOf(WhiteWin.class);
    }
}
