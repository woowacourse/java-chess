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

class RookTest {

    @DisplayName("주어진 출발지 -> 도착지를 Rook이 이동할 수 있는지 검증한다.")
    @MethodSource("checkMovableTestCase")
    @ParameterizedTest
    void checkMovableTest(final Position source, final Position destination) {
        // Given
        Rook rook = new Rook(PieceColor.WHITE);
        Map<Position, Piece> piecePositions = Map.of(position(File.D, Rank.TWO), new Rook(PieceColor.BLACK));
        Board board = new Board(piecePositions);

        // When & Then
        assertThatCode(() -> rook.move(source, destination, board))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> checkMovableTestCase() {
        return Stream.of(
                Arguments.of(position(File.B, Rank.TWO), position(File.B, Rank.SIX)),
                Arguments.of(position(File.B, Rank.TWO), position(File.B, Rank.ONE)),
                Arguments.of(position(File.B, Rank.TWO), position(File.A, Rank.TWO)),
                Arguments.of(position(File.B, Rank.TWO), position(File.D, Rank.TWO))
        );
    }

    @DisplayName("Rook이 이동할 수 없는 방향의 도착지가 입력되면 예외를 발생시킨다.")
    @MethodSource("throwExceptionWhenInvalidDirectionTestCase")
    @ParameterizedTest
    void throwExceptionWhenInvalidDirectionTest(final Position source, final Position destination) {
        // Given
        Rook rook = new Rook(PieceColor.WHITE);
        Map<Position, Piece> piecePositions = Collections.emptyMap();
        Board board = new Board(piecePositions);

        // When & Then
        assertThatThrownBy(() ->rook.move(source, destination, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("방향이 유효하지 않아 이동할 수 없는 칸입니다.");
    }

    private static Stream<Arguments> throwExceptionWhenInvalidDirectionTestCase() {
        return Stream.of(
                Arguments.of(position(File.B, Rank.TWO), position(File.E, Rank.FIVE)),
                Arguments.of(position(File.B, Rank.TWO), position(File.G, Rank.SEVEN))
        );
    }

    @DisplayName("이동 경로에 기물이 존재하면 예외를 발생시킨다.")
    @MethodSource("throwExceptionWhenPathsHasPieceTestCase")
    @ParameterizedTest
    void throwExceptionWhenPathsHasPieceTest(final Position source, final Position destination) {
        // Given
        Rook rook = new Rook(PieceColor.WHITE);
        Map<Position, Piece> piecePositions = Map.of(
                position(File.B, Rank.FOUR), new Rook(PieceColor.BLACK),
                position(File.D, Rank.TWO), new Rook(PieceColor.WHITE)
        );
        Board board = new Board(piecePositions);

        // When & Then
        assertThatThrownBy(() -> rook.move(source, destination, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("목적지 경로에 기물이 존재하여 이동할 수 없습니다.");
    }

    private static Stream<Arguments> throwExceptionWhenPathsHasPieceTestCase() {
        return Stream.of(
                Arguments.of(position(File.B, Rank.TWO), position(File.B, Rank.SIX)),
                Arguments.of(position(File.B, Rank.TWO), position(File.G, Rank.TWO))
        );
    }

    @DisplayName("도착지에 아군 기물이 존재하면 예외를 발생시킨다.")
    @Test
    void throwExceptionWhenDestinationHasTeamPieceTest() {
        // Given
        Position source = position(File.B, Rank.TWO);
        Position destination = position(File.B, Rank.SIX);
        Rook rook = new Rook(PieceColor.WHITE);
        Map<Position, Piece> piecePositions = Map.of(destination, new Rook(PieceColor.WHITE));
        Board board = new Board(piecePositions);

        // When & Then
        assertThatThrownBy(() -> rook.move(source, destination, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아군 기물이 위치한 칸으로는 이동할 수 없습니다.");
    }

    private static Position position(final File file, final Rank rank) {
        return new Position(file, rank);
    }
}
