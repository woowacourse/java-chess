package chess.board;

import java.util.stream.Stream;

import chess.piece.Pieces;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board(new Pieces());
    }

    @Test
    @DisplayName("기물이 해당 위치로 갈 수 없으면 예외를 던진다.")
    void movePiece_throws() {
        // given
        final Position sourcePosition = new Position(File.B, Rank.TWO);
        final Position targetPosition = new Position(File.B, Rank.FIVE);

        // when, then
        Assertions.assertThatThrownBy(() -> board.movePiece(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 기물은 대상 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("movePiece_pieceExistOnPath_TestCases")
    @DisplayName("타겟 위치까지의 경로에 말이 존재하는 경우 예외를 던진다.")
    void movePiece_pieceExistOnPath_throws(Position sourcePosition, Position targetPosition) {
        // when, then
        Assertions.assertThatThrownBy(() -> board.movePiece(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 타겟 위치까지의 경로에 말이 존재합니다.");
    }

    static Stream<Arguments> movePiece_pieceExistOnPath_TestCases() {
        return Stream.of(
                Arguments.arguments(new Position(File.A, Rank.ONE), new Position(File.A, Rank.FOUR)),
                Arguments.arguments(new Position(File.C, Rank.ONE), new Position(File.E, Rank.THREE)),
                Arguments.arguments(new Position(File.D, Rank.ONE), new Position(File.D, Rank.THREE))
        );
    }

    @ParameterizedTest
    @MethodSource("movePiece_sameSidePieceExistOnTarget_TestCases")
    @DisplayName("타겟 위치에 아군 말이 존재하는 경우 예외를 던진다.")
    void movePiece_sameSidePieceExistOnTarget_throws(Position sourcePosition, Position targetPosition) {
        // when, then
        Assertions.assertThatThrownBy(() -> board.movePiece(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 타겟 위치에 아군 말이 존재합니다.");
    }

    static Stream<Arguments> movePiece_sameSidePieceExistOnTarget_TestCases() {
        return Stream.of(
                Arguments.arguments(new Position(File.A, Rank.ONE), new Position(File.A, Rank.TWO)),
                Arguments.arguments(new Position(File.C, Rank.ONE), new Position(File.B, Rank.TWO)),
                Arguments.arguments(new Position(File.D, Rank.ONE), new Position(File.D, Rank.TWO))
        );
    }
}
