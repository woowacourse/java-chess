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

class KnightTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Knight(Team.WHITE))
                .doesNotThrowAnyException();

    }

    @DisplayName("target 좌표에 아군 기물이 있다면, 이동할 수 없다.")
    @Test
    void attackTeam() {
        Knight knight = new Knight(Team.WHITE);
        HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
        Coordinate source = new Coordinate(1, 'e');
        Coordinate target = new Coordinate(2, 'e');
        piecesMap.put(source, knight);
        piecesMap.put(target, new Pawn(Team.WHITE));
        Pieces pieces = new Pieces(piecesMap);

        assertThatThrownBy(() -> knight.validateMovable(source, target, pieces))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아군 기물은 공격할 수 없습니다.");
    }

    @DisplayName("제자리 이동을 할 수 없다.")
    @Test
    void sameCoordinate() {
        Knight knight = new Knight(Team.WHITE);
        HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
        Coordinate source = new Coordinate(3, 'e');
        piecesMap.put(source, knight);
        Pieces pieces = new Pieces(piecesMap);

        assertThatThrownBy(() -> knight.validateMovable(source, source, pieces))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("제자리 이동은 할 수 없습니다.");
    }

    @DisplayName("나이트는 사방중 한 방향으로 한 칸, 그리고 그 방향의 양 대각선 방향 중 한 방향으로 아군을 뛰어 넘어, 움직일 수 있다.")
    @MethodSource("createTargetForHappyCase")
    @ParameterizedTest
    void canGo(Coordinate target) {
        Knight knight = new Knight(Team.WHITE);
        HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
        Coordinate source = new Coordinate(3, 'e');
        piecesMap.put(source, knight);
        Pieces pieces = new Pieces(piecesMap);

        assertThatCode(() -> knight.validateMovable(source, target, pieces))
                .doesNotThrowAnyException();
    }

    @DisplayName("나이트가 갈 수 없는 위치라면 이동할 수 없다.")
    @MethodSource("createTargetForExceptionCase")
    @ParameterizedTest
    void cantGo(Coordinate target) {
        Knight knight = new Knight(Team.WHITE);
        HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
        Coordinate source = new Coordinate(3, 'e');
        piecesMap.put(source, knight);
        Pieces pieces = new Pieces(piecesMap);

        assertThatThrownBy(() -> knight.validateMovable(source, target, pieces))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
    }

    /***
     * ........ 8
     * ........ 7
     * ........ 6
     * ...O.O.. 5
     * ..O...O. 4
     * ....N... 3
     * ..O...O. 2
     * ...O.O.. 1
     * --------
     * abcdefgh
     */
    private static Iterable<Coordinate> createTargetForHappyCase() {
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

    /***
     * ........ 8
     * ........ 7
     * ........ 6
     * ...OXO.. 5
     * ..OXXXO. 4
     * ..XXNXX. 3
     * ..OXXXO. 2
     * ...OXO.. 1
     * --------
     * abcdefgh
     */
    private static Iterable<Coordinate> createTargetForExceptionCase() {
        return List.of(
                new Coordinate(5, 'e'),
                new Coordinate(4, 'd'),
                new Coordinate(4, 'e'),
                new Coordinate(4, 'f'),
                new Coordinate(3, 'c'),
                new Coordinate(3, 'd'),
                new Coordinate(3, 'f'),
                new Coordinate(3, 'g'),
                new Coordinate(2, 'd'),
                new Coordinate(2, 'e'),
                new Coordinate(2, 'f'),
                new Coordinate(1, 'e'));
    }
}
