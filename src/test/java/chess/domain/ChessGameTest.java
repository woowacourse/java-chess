package chess.domain;

import chess.cache.PieceCache;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static chess.PositionCache.*;

class ChessGameTest {
    private static final Pawn BLACK_PAWN = Pawn.from(Color.BLACK);

    @DisplayName("점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("calculateScoreTestDummy")
    void calculateLinearMoveAblePositionTest(
            final Map<Position, Piece> piecesMap,
            final double score
    ) {
        //given
        Board board = Board.from(piecesMap);
        ChessGame chessGame = ChessGame.restart(board, Color.BLACK);

        //then
        Assertions.assertThat(chessGame.calculateScore(Color.BLACK)).isEqualTo(score);
    }

    static Stream<Arguments> calculateScoreTestDummy() {
        return Stream.of(
                Arguments.arguments(
                        Map.of(POSITION_1_1, BLACK_PAWN, POSITION_1_2, BLACK_PAWN, POSITION_1_5, BLACK_PAWN),
                        1.5
                ),
                Arguments.arguments(
                        Map.of(POSITION_1_1, BLACK_PAWN),
                        1
                ),
                Arguments.arguments(
                        PieceCache.create(),
                        38
                )
        );
    }
}
