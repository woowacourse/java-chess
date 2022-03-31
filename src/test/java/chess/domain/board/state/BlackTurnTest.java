package chess.domain.board.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Rank;
import chess.domain.piece.Position;
import chess.domain.state.BlackTurn;
import chess.domain.state.BoardInitializer;
import chess.domain.state.GameState;
import chess.domain.state.WhiteTurn;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackTurnTest {

    @DisplayName("흑팀의 차례인지 확인한다.")
    @Test
    void isBlackTurn() {
        Map<Integer, Rank> ranks = new HashMap<>();
        BlackTurn blackTurn = new BlackTurn(ranks);

        assertThat(blackTurn.isBlackTurn()).isTrue();
    }

    @DisplayName("흑팀 차례 이후에 백팀 차례가 된다.")
    @Test
    void isBlackTurnAfterWhiteTurn() {
        GameState whiteTurn = BoardInitializer.initBoard();
        GameState blackTurn = whiteTurn.move(new Position("b2"), new Position("b4"));
        GameState whiteTurn2 = blackTurn.move(new Position("b7"), new Position("b6"));

        assertThat(whiteTurn2).isInstanceOf(WhiteTurn.class);
    }

    @DisplayName("흑팀 차례에 백팀 말을 움직이면 예외가 발생한다.")
    @Test
    void moveBlackPieceInWhiteTurn() {
        GameState whiteTurn = BoardInitializer.initBoard();
        GameState blackTurn = whiteTurn.move(new Position("b2"), new Position("b4"));

        assertThatThrownBy(() -> blackTurn.move(new Position("c2"), new Position("c4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("본인의 기물이 아닙니다.");
    }
}
