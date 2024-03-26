package domain.piece;

import domain.board.Board;
import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {

    @DisplayName("주어진 출발지 -> 도착지를 Bishop이 이동할 수 있는지 검증한다.")
    @MethodSource("checkMovableTestCase")
    @ParameterizedTest
    void checkMovableTest(final Position source, final Position destination) {
        // Given
        Bishop bishop = new Bishop(PieceColor.WHITE);
        Map<Position, Piece> piecePositions = Map.of(position(File.C, Rank.ONE), new Rook(PieceColor.BLACK));
        Board board = new Board(piecePositions);

        // When & Then
        assertThatCode(() -> bishop.move(source, destination, board))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> checkMovableTestCase() {
        return Stream.of(
                Arguments.of(position(File.B, Rank.TWO), position(File.E, Rank.FIVE)),
                Arguments.of(position(File.B, Rank.TWO), position(File.A, Rank.THREE)),
                Arguments.of(position(File.B, Rank.TWO), position(File.A, Rank.ONE)),
                Arguments.of(position(File.B, Rank.TWO), position(File.C, Rank.ONE))
        );
    }

    @DisplayName("Bishop이 이동할 수 없는 방향의 도착지가 입력되면 예외를 발생시킨다.")
    @MethodSource("throwExceptionWhenInvalidDirectionTestCase")
    @ParameterizedTest
    void throwExceptionWhenInvalidDirectionTest(final Position source, final Position destination) {
        // Given
        Bishop bishop = new Bishop(PieceColor.WHITE);
        Map<Position, Piece> piecePositions = Collections.emptyMap();
        Board board = new Board(piecePositions);

        // When & Then
        assertThatThrownBy(() ->bishop.move(source, destination, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비숍이 이동할 수 있는 방향이 아닙니다.");
    }

    private static Stream<Arguments> throwExceptionWhenInvalidDirectionTestCase() {
        return Stream.of(
                Arguments.of(position(File.B, Rank.TWO), position(File.B, Rank.FIVE)),
                Arguments.of(position(File.B, Rank.TWO), position(File.G, Rank.TWO))
        );
    }

    @DisplayName("이동 경로에 기물이 존재하면 예외를 발생시킨다.")
    @MethodSource("throwExceptionWhenPathsHasPieceTestCase")
    @ParameterizedTest
    void throwExceptionWhenPathsHasPieceTest(final Position source, final Position destination) {
        // Given
        Bishop bishop = new Bishop(PieceColor.WHITE);
        Map<Position, Piece> piecePositions = Map.of(
                position(File.C, Rank.THREE), new Bishop(PieceColor.BLACK)
        );
        Board board = new Board(piecePositions);

        // When & Then
        assertThatThrownBy(() -> bishop.move(source, destination, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("목적지 경로에 기물이 존재하여 이동할 수 없습니다.");
    }

    private static Stream<Arguments> throwExceptionWhenPathsHasPieceTestCase() {
        return Stream.of(
                Arguments.of(position(File.B, Rank.TWO), position(File.E, Rank.FIVE)),
                Arguments.of(position(File.D, Rank.FOUR), position(File.B, Rank.TWO))
        );
    }

    @DisplayName("도착지에 아군 기물이 존재하면 예외를 발생시킨다.")
    @Test
    void throwExceptionWhenDestinationHasTeamPieceTest() {
        // Given
        Position source = position(File.B, Rank.TWO);
        Position destination = position(File.D, Rank.FOUR);
        Bishop bishop = new Bishop(PieceColor.WHITE);
        Map<Position, Piece> piecePositions = Map.of(destination, new Bishop(PieceColor.WHITE));
        Board board = new Board(piecePositions);

        // When & Then
        assertThatThrownBy(() -> bishop.move(source, destination, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아군 기물이 위치한 칸으로는 이동할 수 없습니다.");
    }

    private static Position position(final File file, final Rank rank) {
        return new Position(file, rank);
    }
}
