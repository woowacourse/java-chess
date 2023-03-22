package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ColumnPieceTest {

    /**
     * .KR.....  8
     * P.PB....  7
     * .P..Q...  6
     * ........  5
     * .....nq.  4
     * .....p.p  3
     * .....pp.  2
     * ....rk..  1
     * 12345678
     */
    @ParameterizedTest
    @MethodSource("calculateScore")
    @DisplayName("calculatePiecesScore() : 주어진 색깔의 피스 기물들의 점수 합을 구할 수 있다.")
    void test_calculatePiecesScore(final ColumnPiece columnPiece,
                                   final Color color,
                                   final Score result) throws Exception {
        //when & then
        assertEquals(columnPiece.calculatePiecesScore(color), result);
    }

    static Stream<Arguments> calculateScore() {

        final ColumnPiece columnPiece1 =
                new ColumnPiece(
                        List.of(new Pawn(WHITE), new Pawn(WHITE),
                                new Knight(WHITE), new King(WHITE)
                        )
                );

        final Color color1 = WHITE;
        final Score result1 = Score.from(3.5);

        final ColumnPiece columnPiece2 = new ColumnPiece(
                List.of(new Queen(WHITE), new Pawn(WHITE))
        );

        final Color color2 = WHITE;
        final Score result2 = Score.from(10);

        final ColumnPiece columnPiece3 = new ColumnPiece(
                List.of(new Pawn(WHITE))
        );

        final Color color3 = WHITE;
        final Score result3 = Score.from(1);

        final ColumnPiece columnPiece4 = new ColumnPiece(
                List.of(new Queen(BLACK), new Rook(WHITE))
        );

        final Color color4 = WHITE;
        final Score result4 = Score.from(5);

        final Color color5 = BLACK;
        final Score result5 = Score.from(9);

        final ColumnPiece columnPiece5 = new ColumnPiece(
                List.of(new Bishop(BLACK))
        );

        final Color color6 = BLACK;
        final Score result6 = Score.from(3);

        final ColumnPiece columnPiece6 = new ColumnPiece(
                List.of(new Rook(BLACK), new Pawn(BLACK))
        );

        final Color color7 = BLACK;
        final Score result7 = Score.from(6);

        final ColumnPiece columnPiece7 = new ColumnPiece(
                List.of(new King(BLACK), new Pawn(BLACK))
        );

        final Color color8 = BLACK;
        final Score result8 = Score.from(1);

        final ColumnPiece columnPiece8 = new ColumnPiece(
                List.of(new Pawn(BLACK))
        );

        final Color color9 = BLACK;
        final Score result9 = Score.from(1);


        return Stream.of(
                Arguments.of(columnPiece1, color1, result1),
                Arguments.of(columnPiece2, color2, result2),
                Arguments.of(columnPiece3, color3, result3),
                Arguments.of(columnPiece4, color4, result4),
                Arguments.of(columnPiece4, color5, result5),
                Arguments.of(columnPiece5, color6, result6),
                Arguments.of(columnPiece6, color7, result7),
                Arguments.of(columnPiece7, color8, result8),
                Arguments.of(columnPiece8, color9, result9)
        );
    }
}
