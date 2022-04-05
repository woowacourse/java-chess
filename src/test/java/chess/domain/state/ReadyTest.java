package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.BasicChessBoardGenerator;
import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @DisplayName("게임을 시작하면 WhiteTurn으로 게임이 시작된다")
    @Test
    void whiteTurn() {
        Board board = BasicChessBoardGenerator.generator();
        State whiteTurn = Ready.start(board);
        assertThat(whiteTurn).isInstanceOf(WhiteTurn.class);
    }

    @DisplayName("WhiteTurn에 수를 두면 BlackTurn이 된다")
    @Test
    void blackTurn() {
        Board board = BasicChessBoardGenerator.generator();
        State whiteTurn = Ready.start(board);
        State blackTurn = whiteTurn.movePiece(Position.of("a2"), Position.of("a3"));

        assertThat(blackTurn).isInstanceOf(BlackTurn.class);
    }

    @DisplayName("BlackTurn에 수를 두면 WhiteTurn이 된다")
    @Test
    void whiteBlackWhiteTurn() {
        Board board = BasicChessBoardGenerator.generator();
        State whiteTurn = Ready.start(board);
        State blackTurn = whiteTurn.movePiece(Position.of("a2"), Position.of("a3"));
        State whiteTurn2 = blackTurn.movePiece(Position.of("a7"), Position.of("a6"));

        assertThat(whiteTurn2).isInstanceOf(WhiteTurn.class);
    }
}
