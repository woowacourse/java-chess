package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.BasicChessBoardGenerator;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import java.util.HashMap;
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
        State blackTurn = whiteTurn.movePiece(Position.valueOf("a2"), Position.valueOf("a3"));

        assertThat(blackTurn).isInstanceOf(BlackTurn.class);
    }

    @DisplayName("BlackTurn에 수를 두면 WhiteTurn이 된다")
    @Test
    void whiteBlackWhiteTurn() {
        Board board = BasicChessBoardGenerator.generator();
        State whiteTurn = Ready.start(board);
        State blackTurn = whiteTurn.movePiece(Position.valueOf("a2"), Position.valueOf("a3"));
        State whiteTurn2 = blackTurn.movePiece(Position.valueOf("a7"), Position.valueOf("a6"));

        assertThat(whiteTurn2).isInstanceOf(WhiteTurn.class);
    }

    @DisplayName("Black King이 없다면 WhiteWin으로 상태가 만들어진다.")
    @Test
    void whiteWinTest() {
        HashMap<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("a1"), new King(Color.WHITE));
        Board board = new Board(value);

        State state = Ready.continueOf(Color.BLACK, board);

        assertThat(state).isInstanceOf(WhiteWin.class);
    }

    @DisplayName("White King이 없다면 BlackWin으로 상태가 만들어진다.")
    @Test
    void blackWinTest() {
        HashMap<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("a1"), new King(Color.BLACK));
        Board board = new Board(value);

        State state = Ready.continueOf(Color.WHITE, board);

        assertThat(state).isInstanceOf(BlackWin.class);
    }
}
