package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.List;
import chess.domain.board.Coordinate;
import chess.domain.board.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new King(Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("target 좌표에 아군 기물이 있다면, 이동할 수 없다.")
    @Test
    void attackTeam() {
        King king = new King(Team.WHITE);
        HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
        Coordinate source = new Coordinate(1, 'e');
        Coordinate target = new Coordinate(2, 'e');
        piecesMap.put(source, king);
        piecesMap.put(target, new Pawn(Team.WHITE));
        Pieces pieces = new Pieces(piecesMap);

        assertThatThrownBy(() -> king.validateMovable(source, target, pieces))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아군 기물은 공격할 수 없습니다.");
    }

    @DisplayName("제자리 이동을 할 수 없다.")
    @Test
    void sameCoordinate() {
        King king = new King(Team.WHITE);
        HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
        Coordinate source = new Coordinate(3, 'e');
        piecesMap.put(source, king);
        Pieces pieces = new Pieces(piecesMap);

        assertThatThrownBy(() -> king.validateMovable(source, source, pieces))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("제자리 이동은 할 수 없습니다.");
    }

    @DisplayName("킹은 가로, 세로 및 대각선으로도 1칸씩 움직일 수 있다.")
    @MethodSource("createTargetForHappyCase")
    @ParameterizedTest
    void canGo(Coordinate target) {
        King king = new King(Team.WHITE);
        HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
        Coordinate source = new Coordinate(3, 'e');
        piecesMap.put(source, king);
        Pieces pieces = new Pieces(piecesMap);

        assertThatCode(() -> king.validateMovable(source, target, pieces))
                .doesNotThrowAnyException();
    }

    @DisplayName("킹이 갈 수 없는 위치라면 이동할 수 없다.")
    @MethodSource("createTargetForExceptionCase")
    @ParameterizedTest
    void cantGo(Coordinate target) {
        King king = new King(Team.WHITE);
        HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
        Coordinate source = new Coordinate(3, 'e');
        piecesMap.put(source, king);
        Pieces pieces = new Pieces(piecesMap);

        assertThatThrownBy(() -> king.validateMovable(source, target, pieces))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
    }

    /***
     * ........ 8
     * ........ 7
     * ........ 6
     * ........ 5
     * ...OOO.. 4
     * ...OKO.. 3
     * ...OOO.. 2
     * ........ 1
     * --------
     * abcdefgh
     */
    private static Iterable<Coordinate> createTargetForHappyCase() {
        return List.of(
                new Coordinate(2, 'd'),
                new Coordinate(2, 'e'),
                new Coordinate(2, 'f'),
                new Coordinate(3, 'd'),
                new Coordinate(3, 'f'),
                new Coordinate(4, 'd'),
                new Coordinate(4, 'e'),
                new Coordinate(4, 'f'));
    }

    /***
     * ........ 8
     * ........ 7
     * ........ 6
     * ..XXXXX. 5
     * ..XOOOX. 4
     * ..XOKOX. 3
     * ..XOOOX. 2
     * ..XXXXX. 1
     * --------
     * abcdefgh
     */
    private static Iterable<Coordinate> createTargetForExceptionCase() {
        return List.of(
                new Coordinate(5, 'c'),
                new Coordinate(5, 'd'),
                new Coordinate(5, 'e'),
                new Coordinate(5, 'f'),
                new Coordinate(5, 'g'),
                new Coordinate(4, 'c'),
                new Coordinate(4, 'g'),
                new Coordinate(3, 'c'),
                new Coordinate(3, 'g'),
                new Coordinate(2, 'c'),
                new Coordinate(2, 'g'),
                new Coordinate(1, 'c'),
                new Coordinate(1, 'd'),
                new Coordinate(1, 'e'),
                new Coordinate(1, 'f'),
                new Coordinate(1, 'g'));
    }
}
