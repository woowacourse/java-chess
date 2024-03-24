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

class KingMovementStrategyTest {

    static Stream<Arguments> canKingMoveAllDirectionOneStepArguments() {
        return Stream.of(
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.D, ChessRank.THREE)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.E, ChessRank.FOUR)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.D, ChessRank.FIVE)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.C, ChessRank.FOUR)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.E, ChessRank.FIVE)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.C, ChessRank.FIVE)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.E, ChessRank.THREE)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.C, ChessRank.THREE))
        );
    }

    static Stream<Arguments> cannotKingMoveAllDirectionMoreThanTwoStepArguments() {
        return Stream.of(
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.D, ChessRank.TWO)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.F, ChessRank.FOUR)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.D, ChessRank.SIX)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.B, ChessRank.FOUR)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.F, ChessRank.SIX)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.B, ChessRank.SIX)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.F, ChessRank.TWO)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.B, ChessRank.TWO))
        );
    }

    @DisplayName("킹은 모든 방향으로 한 칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("canKingMoveAllDirectionOneStepArguments")
    void canKingMoveAllDirectionOneStep(Position source, Position target) {
        // given
        Piece king = new Piece(PieceType.BLACK_KING);

        // when
        boolean result = king.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("킹은 모든 방향으로 두 칸 이상 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("cannotKingMoveAllDirectionMoreThanTwoStepArguments")
    void cannotKingMoveAllDirectionMoreThanTwoStep(Position source, Position target) {
        // given
        Piece king = new Piece(PieceType.BLACK_KING);

        // when
        boolean result = king.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }
}
