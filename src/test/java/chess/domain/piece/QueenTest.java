package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.List;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class QueenTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Queen(Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("target 좌표에 아군 기물이 있다면, 이동할 수 없다.")
    @Test
    void attackTeam() {
        Queen queen = new Queen(Team.WHITE);
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Coordinate source = new Coordinate(3, 'e');
        Coordinate target = new Coordinate(4, 'd');
        pieces.put(source, queen);
        pieces.put(target, new Pawn(Team.WHITE));
        Board board = new Board(pieces);

        assertThatThrownBy(() -> queen.validateMovable(source, target, board))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아군 기물은 공격할 수 없습니다.");
    }

    @DisplayName("제자리 이동을 할 수 없다.")
    @Test
    void sameCoordinate() {
        Queen queen = new Queen(Team.WHITE);
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Coordinate source = new Coordinate(3, 'e');
        pieces.put(source, queen);
        Board board = new Board(pieces);

        assertThatThrownBy(() -> queen.validateMovable(source, source, board))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("제자리 이동은 할 수 없습니다.");
    }

    /***
     * ........ 8
     * T....... 7  T: target
     * ........ 6
     * ..O..... 5  O: Obstacle
     * ........ 4
     * ....Q... 3  Q: Queen
     * ........ 2
     * ........ 1
     * --------
     * abcdefgh
     */
    @DisplayName("target 으로 가는 대각선 경로에 기물이 존재하면, 이동할 수 없다.")
    @Test
    void obstacleDiagonal() {
        Queen queen = new Queen(Team.WHITE);
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Coordinate source = new Coordinate(3, 'e');
        Coordinate obstacle = new Coordinate(5, 'c');
        Coordinate target = new Coordinate(7, 'a');
        pieces.put(source, queen);
        pieces.put(obstacle, new Pawn(Team.WHITE));
        pieces.put(target, new Pawn(Team.BLACK));
        Board board = new Board(pieces);

        assertThatThrownBy(() -> queen.validateMovable(source, target, board))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("기물로 막혀있어 이동할 수 없습니다.");
    }

    /***
     * ....T... 8  T: target
     * ........ 7
     * ....O... 6  O: Obstacle
     * ........ 5
     * ........ 4
     * ....Q... 3  Q: Queen
     * ........ 2
     * ........ 1
     * --------
     * abcdefgh
     */
    @DisplayName("target 으로 가는 세로 경로에 기물이 존재하면, 이동할 수 없다.")
    @Test
    void obstacleStraight() {
        Queen queen = new Queen(Team.WHITE);
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Coordinate source = new Coordinate(3, 'e');
        Coordinate obstacle = new Coordinate(6, 'e');
        Coordinate target = new Coordinate(8, 'e');
        pieces.put(source, queen);
        pieces.put(obstacle, new Pawn(Team.WHITE));
        pieces.put(target, new Pawn(Team.BLACK));
        Board board = new Board(pieces);

        assertThatThrownBy(() -> queen.validateMovable(source, target, board))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("기물로 막혀있어 이동할 수 없습니다.");
    }

    @DisplayName("퀸은 대각과 가로, 세로로 제한없이 움직일 수 있다.")
    @MethodSource("createTargetForHappyCase")
    @ParameterizedTest
    void canGo(Coordinate target) {
        Queen queen = new Queen(Team.WHITE);
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Coordinate source = new Coordinate(3, 'e');
        pieces.put(source, queen);
        Board board = new Board(pieces);

        assertThatCode(() -> queen.validateMovable(source, target, board))
                .doesNotThrowAnyException();
    }

    @DisplayName("퀸이 갈 수 없는 위치라면 이동할 수 없다.")
    @MethodSource("createTargetForExceptionCase")
    @ParameterizedTest
    void cantGo(Coordinate target) {
        Queen queen = new Queen(Team.WHITE);
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Coordinate source = new Coordinate(3, 'e');
        pieces.put(source, queen);
        Board board = new Board(pieces);

        assertThatThrownBy(() -> queen.validateMovable(source, target, board))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
    }

    /***
     * ....O... 8
     * O...O... 7
     * .O..O..O 6
     * ..O.O.O. 5
     * ...OOO.. 4
     * OOOOBOOO 3
     * ...OOO.. 2
     * ..O.O.O. 1
     * --------
     * abcdefgh
     */
    private static Iterable<Coordinate> createTargetForHappyCase() {
        return List.of(
                new Coordinate(4, 'd'),
                new Coordinate(5, 'c'),
                new Coordinate(6, 'b'),
                new Coordinate(7, 'a'),
                new Coordinate(4, 'f'),
                new Coordinate(5, 'g'),
                new Coordinate(6, 'h'),
                new Coordinate(2, 'd'),
                new Coordinate(1, 'c'),
                new Coordinate(2, 'f'),
                new Coordinate(1, 'g'),
                new Coordinate(3, 'a'),
                new Coordinate(3, 'b'),
                new Coordinate(3, 'c'),
                new Coordinate(3, 'd'),
                new Coordinate(3, 'f'),
                new Coordinate(3, 'g'),
                new Coordinate(3, 'h'),
                new Coordinate(1, 'e'),
                new Coordinate(2, 'e'),
                new Coordinate(4, 'e'),
                new Coordinate(5, 'e'),
                new Coordinate(6, 'e'),
                new Coordinate(7, 'e'),
                new Coordinate(8, 'e'));
    }

    /***
     * ....O... 8
     * O...O... 7
     * .O..O..O 6
     * ..OXOXO. 5
     * ..XOOOX. 4
     * OOOOBOOO 3
     * ..XOOOX. 2
     * ..OXOXO. 1
     * --------
     * abcdefgh
     */
    private static Iterable<Coordinate> createTargetForExceptionCase() {
        return List.of(
                new Coordinate(5, 'd'),
                new Coordinate(5, 'f'),
                new Coordinate(4, 'c'),
                new Coordinate(4, 'g'),
                new Coordinate(2, 'c'),
                new Coordinate(2, 'g'),
                new Coordinate(1, 'd'),
                new Coordinate(1, 'f'));
    }
}
