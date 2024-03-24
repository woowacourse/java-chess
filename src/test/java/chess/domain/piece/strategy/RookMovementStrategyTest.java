package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RookMovementStrategyTest {

    static Stream<Arguments> canRookMoveCrossArguments() {
        return Stream.of(
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.D, ChessRank.THREE)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.H, ChessRank.FOUR)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.D, ChessRank.SEVEN)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.A, ChessRank.FOUR))
        );
    }

    static Stream<Arguments> cannotRookMoveDiagonalArguments() {
        return Stream.of(
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.E, ChessRank.FIVE)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.C, ChessRank.FIVE)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.E, ChessRank.THREE)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.C, ChessRank.THREE))
        );
    }

    @DisplayName("룩은 상하좌우로 원하는 만큼 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("canRookMoveCrossArguments")
    void canRookMoveDirection(Position source, Position target) {
        // given
        Piece rook = new Piece(PieceType.BLACK_ROOK);

        // when
        boolean result = rook.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("룩은 대각선으로 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("cannotRookMoveDiagonalArguments")
    void cannotRookMoveDiagonal(Position source, Position target) {
        // given
        Piece rook = new Piece(PieceType.BLACK_ROOK);

        // when
        boolean result = rook.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }
}
