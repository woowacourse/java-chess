package chess.domain.board.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Rank;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Position;
import chess.domain.state.BlackTurn;
import chess.domain.state.BlackWin;
import chess.domain.state.GameState;
import chess.domain.state.Winner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackWinTest {

    @DisplayName("흑팀의 승리인지 확인한다.")
    @Test
    void isBlackWin() {
        Map<Integer, Rank> ranks = new HashMap<>();
        BlackWin blackWin = new BlackWin(ranks);

        assertThat(blackWin.findWinner()).isEqualTo(Winner.BLACK);
    }

    @DisplayName("흑팀이 백팀의 킹을 잡을 경우 흑팀이 승리한다.")
    @Test
    void blackWin() {
        Map<Integer, Rank> ranks = new HashMap<>();

        ranks.put(0, new Rank(List.of(
                King.createWhite(new Position("a1")),
                King.createBlack(new Position("b1")),
                new Blank(new Position("c1")),
                new Blank(new Position("d1")),
                new Blank(new Position("e1")),
                new Blank(new Position("f1")),
                new Blank(new Position("g1")),
                new Blank(new Position("h1"))
        )));

        GameState state = new BlackTurn(ranks);

        state = state.move(new Position("b1"), new Position("a1"));

        assertThat(state).isInstanceOf(BlackWin.class);
    }
}
