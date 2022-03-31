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

class WhiteTurnTest {

    @DisplayName("흑팀의 차례가 아닌지 확인한다.")
    @Test
    void isNotBlackTurn() {
        Map<Integer, Rank> ranks = new HashMap<>();
        WhiteTurn whiteTurn = new WhiteTurn(new Board(BoardInitializer.initBoard()));

        assertThat(whiteTurn.isBlackTurn()).isFalse();
    }

    @DisplayName("백팀 차례 이후에 흑팀 차례가 된다.")
    @Test
    void isBlackTurnAfterWhiteTurn() {
        GameState whiteTurn = new WhiteTurn(new Board(BoardInitializer.initBoard()));
        GameState blackTurn = whiteTurn.move(new Position("b2"), new Position("b4"));

        assertThat(blackTurn).isInstanceOf(BlackTurn.class);
    }

    @DisplayName("백팀 차례에 흑팀 말을 움직이면 예외가 발생한다.")
    @Test
    void moveBlackPieceInWhiteTurn() {
        GameState whiteTurn = new WhiteTurn(new Board(BoardInitializer.initBoard()));

        assertThatThrownBy(() -> whiteTurn.move(new Position("b7"), new Position("b5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("본인의 기물이 아닙니다.");
    }
}
