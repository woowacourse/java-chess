package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WhiteTurnTest {
    @Test
    void start() {
        State whiteTurn = new WhiteTurn(Board.create());
        assertThatThrownBy(() -> whiteTurn.start())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void end() {
        State whiteTurn = new WhiteTurn(Board.create());
        State end = whiteTurn.end();
        assertThat(end).isInstanceOf(End.class);
    }

    @Test
    void move_white() {
        State whiteTurn = new WhiteTurn(Board.create());
        State blackTurn = whiteTurn.move(Coordinate.of("a2"), Coordinate.of("a4"));
        Piece piece = blackTurn.getValue().get(Coordinate.of("a4"));
        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("WhiteTurn 상태에서 검정 말을 움직이면 에러가 발생한다.")
    void move_black() {
        State whiteTurn = new WhiteTurn(Board.create());
        assertThatThrownBy(() -> whiteTurn.move(Coordinate.of("a7"), Coordinate.of("a6")))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void is_finished() {
        State whiteTurn = new WhiteTurn(Board.create());
        assertThat(whiteTurn.isFinished()).isFalse();
    }
}
