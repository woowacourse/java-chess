package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteWinTest {

    @DisplayName("흑팀의 승리가 아닌지 확인한다.")
    @Test
    void isBlackWin() {
        Map<Integer, Rank> ranks = new HashMap<>();

        WhiteWin whiteWin = new WhiteWin(new Board(ranks));

        assertThat(whiteWin.findWinner()).isEqualTo(Winner.WHITE);
    }

    @DisplayName("백팀이 흑팀의 킹을 잡을 경우 백팀이 승리한다.")
    @Test
    void whiteWin() {
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

        GameState state = new WhiteTurn(new Board(ranks));

        state = state.move(new Position("a1"), new Position("b1"));

        assertThat(state).isInstanceOf(WhiteWin.class);
    }
}
