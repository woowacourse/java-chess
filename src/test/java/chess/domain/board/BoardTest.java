package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.player.Player;
import chess.domain.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    private Map<Point, Piece> tempBoard;

    @BeforeEach
    void setUp() {
        tempBoard = BoardFactory.createEmptyBoard();
    }

    @DisplayName("비숍을 a1을 a3으로 이동시킬 수 있다.")
    @Test
    void move1() {
        tempBoard.put(new Point('a', 1), new Bishop(Team.WHITE));
        Board board = new Board(tempBoard);

        board.move(new Player(Team.WHITE),
                new Point('a', 1),
                new Point('c', 3));

        assertThat(board.getBoard().get(new Point('c', 3))).isEqualTo(new Bishop(Team.WHITE));
        assertThat(board.getBoard().get(new Point('a', 1))).isEqualTo(Empty.INSTANCE);
    }

    @DisplayName("나이트를 a1에서 c2로 이동시킬 수 있다.")
    @Test
    void move2() {
        tempBoard.put(new Point('a', 1), new Knight(Team.WHITE));
        Board board = new Board(tempBoard);

        board.move(new Player(Team.WHITE),
                new Point('a', 1),
                new Point('c', 2));

        assertThat(board.getBoard().get(new Point('c', 2))).isEqualTo(new Knight(Team.WHITE));
        assertThat(board.getBoard().get(new Point('a', 1))).isEqualTo(Empty.INSTANCE);
    }

    @DisplayName("룩을 a1을 a8으로 이동시킬 수 있다.")
    @Test
    void move3() {
        tempBoard.put(new Point('a', 1), new Rook(Team.WHITE));
        Board board = new Board(tempBoard);

        board.move(new Player(Team.WHITE),
                new Point('a', 1),
                new Point('a', 8));

        assertThat(board.getBoard().get(new Point('a', 8))).isEqualTo(new Rook(Team.WHITE));
        assertThat(board.getBoard().get(new Point('a', 1))).isEqualTo(Empty.INSTANCE);
    }

    @DisplayName("폰을 a2에서 a3으로 이동시킬 수 있다.")
    @Test
    void move4() {
        tempBoard.put(new Point('a', 2), new Pawn(Team.WHITE));
        Board board = new Board(tempBoard);

        board.move(new Player(Team.WHITE),
                new Point('a', 2),
                new Point('a', 3));

        assertThat(board.getBoard().get(new Point('a', 3))).isEqualTo(new Pawn(Team.WHITE));
        assertThat(board.getBoard().get(new Point('a', 2))).isEqualTo(Empty.INSTANCE);
    }

    @DisplayName("비숍이 이동할 경로에 기물이 있으면 예외가 발생한다.")
    @Test
    void invalidMove() {
        tempBoard.put(new Point('a', 1), new Bishop(Team.WHITE));
        tempBoard.put(new Point('b', 2), new Bishop(Team.WHITE));
        Board board = new Board(tempBoard);

        assertThatThrownBy(() -> board.move(new Player(Team.WHITE),
                new Point('a', 1),
                new Point('c', 3)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("룩이 이동할 경로에 기물이 있으면 예외가 발생한다.")
    @Test
    void invalidMove2() {
        tempBoard.put(new Point('a', 1), new Rook(Team.WHITE));
        tempBoard.put(new Point('a', 5), new Rook(Team.WHITE));
        Board board = new Board(tempBoard);

        assertThatThrownBy(() -> board.move(new Player(Team.WHITE),
                new Point('a', 1),
                new Point('a', 8)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰은 수직으로 이동할 때 적이 경로에 있어도 전진할 수 없다.")
    @Test
    void invalidMove3() {
        tempBoard.put(new Point('a', 1), new Pawn(Team.WHITE));
        tempBoard.put(new Point('a', 2), new Pawn(Team.BLACK));
        Board board = new Board(tempBoard);

        assertThatThrownBy(() -> board.move(new Player(Team.WHITE),
                new Point('a', 1),
                new Point('a', 2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰은 대각선에 적이 없으면 대각선으로 이동할 수 없다.")
    @Test
    void invalidMove4() {
        tempBoard.put(new Point('a', 1), new Pawn(Team.WHITE));
        Board board = new Board(tempBoard);

        assertThatThrownBy(() -> board.move(new Player(Team.WHITE),
                new Point('a', 1),
                new Point('b', 2)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
