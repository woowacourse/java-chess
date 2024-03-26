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

class KingTest {

    @DisplayName("주어진 출발지 -> 도착지를 Rook이 이동할 수 있는지 검증한다.")
    @MethodSource("checkMovableTestCase")
    @ParameterizedTest
    void checkMovableTest(final Position source, final Position destination) {
        // Given
        King king = new King(PieceColor.WHITE);
        Map<Position, Piece> piecePositions = Map.of(position(File.D, Rank.TWO), new Rook(PieceColor.BLACK));
        Board board = new Board(piecePositions);

        // When & Then
        assertThatCode(() -> king.move(source, destination, board))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> checkMovableTestCase() {
        return Stream.of(
                Arguments.of(position(File.B, Rank.TWO), position(File.B, Rank.THREE)),
                Arguments.of(position(File.B, Rank.TWO), position(File.B, Rank.ONE)),
                Arguments.of(position(File.B, Rank.TWO), position(File.A, Rank.TWO)),
                Arguments.of(position(File.B, Rank.TWO), position(File.C, Rank.TWO)),
                Arguments.of(position(File.B, Rank.TWO), position(File.C, Rank.THREE)),
                Arguments.of(position(File.B, Rank.TWO), position(File.A, Rank.THREE)),
                Arguments.of(position(File.B, Rank.TWO), position(File.A, Rank.ONE)),
                Arguments.of(position(File.B, Rank.TWO), position(File.C, Rank.ONE))
        );
    }

    @DisplayName("출발지에서 목적지까지 거리가 두 칸 이상이면 예외를 발생시킨다.")
    @Test
    void throwExceptionWhenDistanceOverOrEqualTwo() {
        // Given
        Position source = position(File.B, Rank.THREE);
        Position destination = position(File.B, Rank.SIX);
        King king = new King(PieceColor.WHITE);
        Map<Position, Piece> piecePositions = Collections.emptyMap();
        Board board = new Board(piecePositions);

        // When & Then
        assertThatThrownBy(() -> king.move(source, destination, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 거리입니다.");
    }

    @DisplayName("도착지에 아군 기물이 존재하면 예외를 발생시킨다.")
    @Test
    void throwExceptionWhenDestinationHasTeamPieceTest() {
        // Given
        Position source = position(File.B, Rank.TWO);
        Position destination = position(File.B, Rank.THREE);
        King king = new King(PieceColor.WHITE);
        Map<Position, Piece> piecePositions = Map.of(destination, new Rook(PieceColor.WHITE));
        Board board = new Board(piecePositions);

        // When & Then
        assertThatThrownBy(() -> king.move(source, destination, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아군 기물이 위치한 칸으로는 이동할 수 없습니다.");
    }

    private static Position position(final File file, final Rank rank) {
        return new Position(file, rank);
    }
}
