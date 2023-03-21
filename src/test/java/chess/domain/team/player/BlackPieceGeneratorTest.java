package chess.domain.team.player;

import chess.domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static chess.domain.team.Team.BLACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlackPieceGeneratorTest {

    PieceGenerator pieceGenerator;

    @BeforeEach
    void setUp() {
        pieceGenerator = new BlackPieceGenerator();
    }

    @Test
    @DisplayName("검은색 체스 기물 종류는 6개이다")
    void BlackPieceSizeIs6() {
        // given
        final int expected = 6;

        // when
        final Supplier<List<Piece>> supplier = pieceGenerator::generate;

        // then
        assertThat(supplier.get().size()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("createBlackGenerator")
    void contains_blackPiece(Piece piece) {
        // when
        final List<Piece> pieces = pieceGenerator.generate();
        final boolean actual = pieces.contains(piece);

        // then
        assertTrue(actual);
    }

    private static Stream<Arguments> createBlackGenerator() {
        return Stream.of(
                Arguments.of(new King(BLACK)),
                Arguments.of(new Queen(BLACK)),
                Arguments.of(new Bishop(BLACK)),
                Arguments.of(new Knight(BLACK)),
                Arguments.of(new Rook(BLACK)),
                Arguments.of(new Pawn(BLACK))
        );
    }
}
