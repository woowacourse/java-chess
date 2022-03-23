package chess.domain.piece;

import static chess.domain.board.File.A;
import static chess.domain.board.File.B;
import static chess.domain.board.File.C;
import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.File.F;
import static chess.domain.board.File.G;
import static chess.domain.board.File.H;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.File;
import chess.domain.board.Rank;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class PieceTest {

    @ParameterizedTest
    @DisplayName("Rank와 File 값을 받아 초기 위치의 기물을 반환한다.")
    @MethodSource("provideFileAndRankAndExpected")
    void create(final File file, final Rank rank, final Class<? extends Piece> expected) {
        //when
        final Piece actual = Piece.create(file, rank);
        //then
        assertThat(actual).isInstanceOf(expected);
    }

    private static Stream<Arguments> provideFileAndRankAndExpected() {
        return Stream.of(
                Arguments.of(A, ONE, Rook.class),
                Arguments.of(B, ONE, Knight.class),
                Arguments.of(C, ONE, Bishop.class),
                Arguments.of(D, ONE, Queen.class),
                Arguments.of(E, ONE, King.class),
                Arguments.of(F, ONE, Bishop.class),
                Arguments.of(G, ONE, Knight.class),
                Arguments.of(H, ONE, Rook.class),
                Arguments.of(A, TWO, Pawn.class)
        );
    }

    @ParameterizedTest
    @DisplayName("Rank, File을 받아 자신의 위치와 동일한지 반환한다.")
    @CsvSource({"A, ONE, true", "A, TWO, false"})
    void hasPosition(final File file, final Rank rank, final boolean expected) {
        // given
        final Piece piece = Piece.create(A, ONE);
        // when
        final boolean actual = piece.hasPosition(file, rank);
        // then
        assertThat(actual).isEqualTo(expected);
    }
}
