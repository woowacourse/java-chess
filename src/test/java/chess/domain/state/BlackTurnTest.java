package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.Rank;
import chess.domain.piece.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackTurnTest {

    @DisplayName("흑팀의 차례인지 확인한다.")
    @Test
    void isBlackTurn() {
        Map<Integer, Rank> ranks = new HashMap<>();

        BlackTurn blackTurn = new BlackTurn(new Board(ranks));

        assertThat(blackTurn.isBlackTurn()).isTrue();
    }

    @DisplayName("흑팀 차례 이후에 백팀 차례가 된다.")
    @Test
    void isBlackTurnAfterWhiteTurn() {
        GameState blackTurn = new BlackTurn(new Board(BoardInitializer.initBoard()));
        GameState whiteTurn = blackTurn.move(new Position("b7"), new Position("b6"));

        assertThat(whiteTurn).isInstanceOf(WhiteTurn.class);
    }

    @DisplayName("흑팀 차례에 백팀 말을 움직이면 예외가 발생한다.")
    @Test
    void moveBlackPieceInWhiteTurn() {
        GameState blackTurn = new BlackTurn(new Board(BoardInitializer.initBoard()));

        assertThatThrownBy(() -> blackTurn.move(new Position("c2"), new Position("c4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("본인의 기물이 아닙니다.");
    }
}
