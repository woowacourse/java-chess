package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    void 체크판_생성() {
        assertDoesNotThrow(() -> new Game(BoardFactory.init()));
    }

    @Test
    void 체크판_초기화_확인() {
        Game game = new Game(BoardFactory.init());
        assertThat(game.getBoard().get(new Point("h1"))).isEqualTo(new Rook(Team.WHITE));
    }

    @Test
    void 폰_두칸_이동() {
        Game game = new Game(BoardFactory.init());
        Point start = new Point("a2");
        Point end = new Point("a4");
        game.move(start, end);
        assertThat(game.getBoard().get(new Point("a4"))).isEqualTo(new Pawn(Team.WHITE));
    }

    @Test
    void 폰_한칸_이동() {
        Game game = new Game(BoardFactory.init());
        Point start = new Point("a2");
        Point end = new Point("a3");
        game.move(start, end);
        assertThat(game.getBoard().get(new Point("a3"))).isEqualTo(new Pawn(Team.WHITE));
    }

    @Test
    void 나이트_이동() {
        Map<Point, Piece> board = new HashMap<>();
        board.put(new Point("a1"), new Knight(Team.WHITE));
        board.put(new Point("b3"), new Blank());
        Game game = new Game(board);
        Point start = new Point("a1");
        Point end = new Point("b3");
        game.move(start, end);
        assertThat(game.getBoard().get(new Point("b3"))).isEqualTo(new Knight(Team.WHITE));
    }

    @Test
    void 나이트_벽_뛰어넘는_이동() {
        Map<Point, Piece> board = new HashMap<>();
        board.put(new Point("a1"), new Knight(Team.WHITE));
        board.put(new Point("a2"), new Pawn(Team.WHITE));
        board.put(new Point("b2"), new Pawn(Team.WHITE));
        board.put(new Point("b1"), new Pawn(Team.WHITE));
        board.put(new Point("b3"), new Blank());
        Game game = new Game(board);
        Point start = new Point("a1");
        Point end = new Point("b3");
        game.move(start, end);
        assertThat(game.getBoard().get(new Point("b3"))).isEqualTo(new Knight(Team.WHITE));
    }

    @Test
    void 룩_이동_불가능() {
        Game game = new Game(BoardFactory.init());
        Point start = new Point("a1");
        Point end = new Point("a3");
        assertThrows(IllegalArgumentException.class, () -> game.move(start, end));
    }

    @Test
    void 빈칸에서_이동() {
        Game game = new Game(BoardFactory.init());
        Point start = new Point("a3");
        Point end = new Point("a4");
        assertThrows(IllegalArgumentException.class, () -> game.move(start, end));
    }

    @Test
    void 도착지점이_우리팀말() {
        Map<Point, Piece> board = new HashMap<>();
        board.put(new Point("a1"), new Knight(Team.WHITE));
        board.put(new Point("b3"), new Pawn(Team.WHITE));
        Game game = new Game(board);
        Point start = new Point("a1");
        Point end = new Point("b3");
        assertThrows(IllegalArgumentException.class, () -> game.move(start, end));
    }

    @Test
    void 도착지점이_상대말() {
        Map<Point, Piece> board = new HashMap<>();
        board.put(new Point("a1"), new Knight(Team.WHITE));
        board.put(new Point("b3"), new Pawn(Team.BLACK));
        Game game = new Game(board);
        Point start = new Point("a1");
        Point end = new Point("b3");
        game.move(start, end);
        assertThat(game.getBoard().get(end)).isEqualTo(new Knight(Team.WHITE));
    }

    @Test
    void 현재_점수_계산() {
        Game game = new Game(BoardFactory.init());
        assertThat(game.calculateScore(Team.WHITE)).isEqualTo(38.0);
    }

    @Test
    void 킹이_두명_살아있음() {
        Game game = new Game(BoardFactory.init());
        assertTrue(game.isKingAlive());
    }

    @Test
    void 킹이_한명_죽어있음() {
        Map<Point, Piece> board = new HashMap<>();
        board.put(new Point("a1"), new King(Team.WHITE));
        Game game = new Game(board);
        assertFalse(game.isKingAlive());
    }

    @Test
    void 폰_세로_두개_점수계산() {
        Map<Point, Piece> board = new HashMap<>();
        board.put(new Point("a1"), new Pawn(Team.WHITE));
        board.put(new Point("a2"), new Pawn(Team.WHITE));
        Game game = new Game(board);
        assertThat(game.calculateScore(Team.WHITE)).isEqualTo(1.0);
    }

    @Test
    void 폰_가로_두개_점수계산() {
        Map<Point, Piece> board = new HashMap<>();
        board.put(new Point("a1"), new Pawn(Team.WHITE));
        board.put(new Point("b1"), new Pawn(Team.WHITE));
        Game game = new Game(board);
        assertThat(game.calculateScore(Team.WHITE)).isEqualTo(2.0);
    }
}
