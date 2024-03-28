package chess.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("비숍을 a1을 a3으로 이동시킬 수 있다.")
    @Test
    void move1() {
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.FIRST), Piece.bishopFrom(Team.WHITE)));
        Player player = new Player(Team.WHITE, board);

        player.move(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.C, Rank.THIRD));

        assertThat(board.get(Point.of(File.C, Rank.THIRD))).isEqualTo(Piece.bishopFrom(Team.WHITE));
        assertThat(board.get(Point.of(File.A, Rank.FIRST))).isEqualTo(Piece.empty());
    }

    @DisplayName("나이트를 a1에서 b1, b2, c1의 기물 뛰어넘고 c2로 이동시킬 수 있다.")
    @Test
    void move2() {
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.FIRST), Piece.knightFrom(Team.WHITE),
                Point.of(File.B, Rank.FIRST), Piece.knightFrom(Team.WHITE),
                Point.of(File.B, Rank.SECOND), Piece.knightFrom(Team.WHITE),
                Point.of(File.C, Rank.FIRST), Piece.knightFrom(Team.WHITE)
        ));
        Player player = new Player(Team.WHITE, board);

        player.move(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.C, Rank.SECOND));

        assertThat(board.get(Point.of(File.C, Rank.SECOND))).isEqualTo(Piece.knightFrom(Team.WHITE));
        assertThat(board.get(Point.of(File.A, Rank.FIRST))).isEqualTo(Piece.empty());
    }

    @DisplayName("룩을 a1을 a8으로 이동시킬 수 있다.")
    @Test
    void move3() {
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.FIRST), Piece.rookFrom(Team.WHITE)));
        Player player = new Player(Team.WHITE, board);

        player.move(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.A, Rank.EIGHTH));

        assertThat(board.get(Point.of(File.A, Rank.EIGHTH))).isEqualTo(Piece.rookFrom(Team.WHITE));
        assertThat(board.get(Point.of(File.A, Rank.FIRST))).isEqualTo(Piece.empty());
    }

    @DisplayName("폰을 a2에서 a3으로 이동시킬 수 있다.")
    @Test
    void move4() {
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.SECOND), Piece.pawnFrom(Team.WHITE)));
        Player player = new Player(Team.WHITE, board);

        player.move(
                Point.of(File.A, Rank.SECOND),
                Point.of(File.A, Rank.THIRD));

        assertThat(board.get(Point.of(File.A, Rank.THIRD))).isEqualTo(Piece.pawnFrom(Team.WHITE));
        assertThat(board.get(Point.of(File.A, Rank.SECOND))).isEqualTo(Piece.empty());
    }

    @DisplayName("비숍이 이동할 경로에 기물이 있으면 예외가 발생한다.")
    @Test
    void invalidMove() {
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.FIRST), Piece.bishopFrom(Team.WHITE),
                Point.of(File.B, Rank.SECOND), Piece.bishopFrom(Team.WHITE)));
        Player player = new Player(Team.WHITE, board);

        assertThatThrownBy(() ->
                player.move(
                        Point.of(File.A, Rank.FIRST),
                        Point.of(File.C, Rank.THIRD)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("룩이 이동할 경로에 기물이 있으면 예외가 발생한다.")
    @Test
    void invalidMove2() {
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.FIRST), Piece.rookFrom(Team.WHITE),
                Point.of(File.A, Rank.FIFTH), Piece.rookFrom(Team.WHITE)));
        Player player = new Player(Team.WHITE, board);

        assertThatThrownBy(() ->
                player.move(
                        Point.of(File.A, Rank.FIRST),
                        Point.of(File.A, Rank.EIGHTH)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰은 수직으로 이동할 때 적이 경로에 있어도 전진할 수 없다.")
    @Test
    void invalidMove3() {
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.FIRST), Piece.pawnFrom(Team.WHITE),
                Point.of(File.A, Rank.SECOND), Piece.pawnFrom(Team.BLACK)));
        Player player = new Player(Team.WHITE, board);

        assertThatThrownBy(() ->
                player.move(
                        Point.of(File.A, Rank.FIRST),
                        Point.of(File.A, Rank.SECOND)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰은 대각선에 적이 없으면 대각선으로 이동할 수 없다.")
    @Test
    void invalidMove4() {
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.FIRST), Piece.pawnFrom(Team.WHITE)));
        Player player = new Player(Team.WHITE, board);

        assertThatThrownBy(() -> player.move(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.B, Rank.SECOND)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰은 기물을 뛰어넘을 수 없다.")
    @Test
    void invalidMove5() {
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.SECOND), Piece.pawnFrom(Team.WHITE),
                Point.of(File.A, Rank.THIRD), Piece.pawnFrom(Team.WHITE)));
        Player player = new Player(Team.WHITE, board);

        assertThatThrownBy(() -> player.move(
                Point.of(File.A, Rank.SECOND),
                Point.of(File.A, Rank.FOURTH)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
