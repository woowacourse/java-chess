package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackTurnTest {
    @Test
    void start() {
        State blackTurn = new BlackTurn(Board.create());
        assertThatThrownBy(blackTurn::start)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void end() {
        State blackTurn = new BlackTurn(Board.create());
        State end = blackTurn.end();
        assertThat(end).isInstanceOf(End.class);
    }

    @Test
    void move_black() {
        State blackTurn = new BlackTurn(Board.create());
        State whiteTurn = blackTurn.move(Coordinate.of("a7"), Coordinate.of("a6"));
        Piece piece = whiteTurn.getBoard().getValue().get(Coordinate.of("a6"));
        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("BlackTurn 상태에선 흰색 말을 움직이면 에러가 발생한다.")
    void move_white() {
        State blackTurn = new BlackTurn(Board.create());
        assertThatThrownBy(() -> blackTurn.move(Coordinate.of("a2"), Coordinate.of("a4")))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void is_finished() {
        State blackTurn = new BlackTurn(Board.create());
        assertThat(blackTurn.isFinished()).isFalse();
    }
}
