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

class QueenMovementStrategyTest {

    static Stream<Arguments> canQueenMoveAllDirectionArguments() {
        return Stream.of(
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.D, ChessRank.ONE)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.G, ChessRank.FOUR)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.D, ChessRank.SEVEN)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.A, ChessRank.FOUR)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.G, ChessRank.SEVEN)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.A, ChessRank.SEVEN)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.G, ChessRank.ONE)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.A, ChessRank.ONE))
        );
    }

    @DisplayName("퀸은 모든 방향으로 원하는 만큼 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("canQueenMoveAllDirectionArguments")
    void canQueenMoveAllDirection(Position source, Position target) {
        // given
        Piece queen = new Piece(PieceType.WHITE_QUEEN);

        // when
        boolean result = queen.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }
}
