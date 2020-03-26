package chess.domains.piece;

import chess.domains.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @DisplayName("Rook 말이 원하는 위치로 이동할 수 있는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"e2, true", "e6, true", "d4, true", "g4, true", "f6, false"})
    void canMove_1(String target, boolean expectedResult) {
        Piece rook = new Rook(PieceColor.WHITE);

        boolean actualResult = rook.canMove(Position.ofPositionName("e4"), Position.ofPositionName(target));

        assertThat(actualResult).isEqualTo(expectedResult);
    }


    @DisplayName("Bishop 말이 원하는 위치로 이동할 수 있는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"d3, true", "f5, true", "d5, true", "g2, true", "e3, false"})
    void canMove_2(String target, boolean expectedResult) {
        Piece bishop = new Bishop(PieceColor.WHITE);

        boolean actualResult = bishop.canMove(Position.ofPositionName("e4"), Position.ofPositionName(target));

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("Queen 말이 원하는 위치로 이동할 수 있는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"e2, true", "e6, true", "d4, true", "g4, true",
            "d3, true", "f5, true", "d5, true", "g2, true",
            "f6, false"})
    void canMove_3(String target, boolean expectedResult) {
        Piece queen = new Queen(PieceColor.WHITE);

        boolean actualResult = queen.canMove(Position.ofPositionName("e4"), Position.ofPositionName(target));

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("King 말이 원하는 위치로 이동할 수 있는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"e3, true", "e5, true", "d4, true", "f4, true",
            "d3, true", "f5, true", "d5, true", "f5, true",
            "f6, false"})
    void canMove_4(String target, boolean expectedResult) {
        Piece king = new King(PieceColor.WHITE);

        boolean actualResult = king.canMove(Position.ofPositionName("e4"), Position.ofPositionName(target));

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("Knight 말이 원하는 위치로 이동할 수 있는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"c3, true", "c5, true", "d2, true", "f2, true",
            "d6, true", "f6, true", "g3, true", "g5, true",
            "f4, false"})
    void canMove_5(String target, boolean expectedResult) {
        Piece knight = new Knight(PieceColor.WHITE);

        boolean actualResult = knight.canMove(Position.ofPositionName("e4"), Position.ofPositionName(target));

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("Pawn White 말이 초기 위치에서 원하는 위치로 이동할 수 있는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"a3, true", "a4, true", "a5, false", "a1, false", "b2, false"})
    void canMove_6(String target, boolean expectedResult) {
        Piece pawn = new Pawn(PieceColor.WHITE);

        boolean actualResult = pawn.canMove(Position.ofPositionName("a2"), Position.ofPositionName(target));

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("Pawn Black 말이 초기 위치에서 원하는 위치로 이동할 수 있는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"a6, true", "a5, true", "a4, false", "a8, false", "b7, false"})
    void canMove_7(String target, boolean expectedResult) {
        Piece pawn = new Pawn(PieceColor.BLACK);

        boolean actualResult = pawn.canMove(Position.ofPositionName("a7"), Position.ofPositionName(target));

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("Pawn White 말이 초기 위치가 아닌 곳에서 원하는 위치로 이동할 수 있는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"e5, true", "e6, false", "e4, false", "f4, false", "d4, false"})
    void canMove_8(String target, boolean expectedResult) {
        Piece pawn = new Pawn(PieceColor.WHITE);

        boolean actualResult = pawn.canMove(Position.ofPositionName("e4"), Position.ofPositionName(target));

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("Pawn Black 말이 초기 위치가 아닌 곳에서 원하는 위치로 이동할 수 있는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"e3, true", "e2, false", "e5, false", "d4, false", "f4, false"})
    void canMove_9(String target, boolean expectedResult) {
        Piece pawn = new Pawn(PieceColor.BLACK);

        boolean actualResult = pawn.canMove(Position.ofPositionName("e4"), Position.ofPositionName(target));

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("Rook 말이 가려고 하는 위치까지의 경로를 반환할 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"e4, e7, e5, e6", "e4, h4, f4, g4", "e4, e1, e3, e2", "e4, b4, d4, c4"})
    void findRoute_1(String sourceName, String targetName, String route1, String route2) {
        Position source = Position.ofPositionName(sourceName);
        Position target = Position.ofPositionName(targetName);
        Piece rook = new Rook(PieceColor.WHITE);

        List<Position> expected = new ArrayList<>(Arrays.asList(
                Position.ofPositionName(route1), Position.ofPositionName(route2)));

        assertThat(rook.findRoute(source, target)).isEqualTo(expected);
    }

    @DisplayName("Bishop 말이 가려고 하는 위치까지의 경로를 반환할 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"e4, h7, f5, g6", "e4, b1, d3, c2", "e4, b7, d5, c6", "e4, h1, f3, g2"})
    void findRoute_2(String sourceName, String targetName, String route1, String route2) {
        Position source = Position.ofPositionName(sourceName);
        Position target = Position.ofPositionName(targetName);
        Piece bishop = new Bishop(PieceColor.WHITE);

        List<Position> expected = new ArrayList<>(Arrays.asList(
                Position.ofPositionName(route1), Position.ofPositionName(route2)));

        assertThat(bishop.findRoute(source, target)).isEqualTo(expected);
    }

    @DisplayName("Queen 말이 가려고 하는 위치까지의 경로를 반환할 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"e4, e7, e5, e6", "e4, h4, f4, g4", "e4, e1, e3, e2", "e4, b4, d4, c4",
            "e4, h7, f5, g6", "e4, b1, d3, c2", "e4, b7, d5, c6", "e4, h1, f3, g2"})
    void findRoute_3(String sourceName, String targetName, String route1, String route2) {
        Position source = Position.ofPositionName(sourceName);
        Position target = Position.ofPositionName(targetName);
        Piece queen = new Queen(PieceColor.WHITE);

        List<Position> expected = new ArrayList<>(Arrays.asList(
                Position.ofPositionName(route1), Position.ofPositionName(route2)));

        assertThat(queen.findRoute(source, target)).isEqualTo(expected);
    }

    @DisplayName("Pawn White 말이 처음 이동 시에 가려고 하는 위치까지의 경로를 반환할 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"a2, a4, a3", "b2, b4, b3", "c2, c4, c3"})
    void findRoute_4(String sourceName, String targetName, String route1) {
        Position source = Position.ofPositionName(sourceName);
        Position target = Position.ofPositionName(targetName);
        Piece pawn = new Pawn(PieceColor.WHITE);

        List<Position> expected = new ArrayList<>(Arrays.asList(Position.ofPositionName(route1)
        ));

        assertThat(pawn.findRoute(source, target)).isEqualTo(expected);
    }

    @DisplayName("Pawn Black말이 처음 이동 시에 가려고 하는 위치까지의 경로를 반환할 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"a7, a5, a6", "b7, b5, b6", "c7, c5, c6"})
    void findRoute_5(String sourceName, String targetName, String route1) {
        Position source = Position.ofPositionName(sourceName);
        Position target = Position.ofPositionName(targetName);
        Piece pawn = new Pawn(PieceColor.BLACK);

        List<Position> expected = new ArrayList<>(Arrays.asList(
                Position.ofPositionName(route1)));

        assertThat(pawn.findRoute(source, target)).isEqualTo(expected);
    }

    @DisplayName("Pawn White 말이 가려고 하는 위치까지의 경로를 반환할 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"a4, a5", "b4, b5", "c4, c5"})
    void findRoute_6(String sourceName, String targetName) {
        Position source = Position.ofPositionName(sourceName);
        Position target = Position.ofPositionName(targetName);
        Piece pawn = new Pawn(PieceColor.WHITE);

        List<Position> expected = Collections.EMPTY_LIST;

        assertThat(pawn.findRoute(source, target)).isEqualTo(expected);
    }

    @DisplayName("Pawn Black 말이 가려고 하는 위치까지의 경로를 반환할 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"a5, a4", "b5, b4", "c5, c4"})
    void findRoute_7(String sourceName, String targetName) {
        Position source = Position.ofPositionName(sourceName);
        Position target = Position.ofPositionName(targetName);
        Piece pawn = new Pawn(PieceColor.BLACK);

        List<Position> expected = Collections.EMPTY_LIST;

        assertThat(pawn.findRoute(source, target)).isEqualTo(expected);
    }
}