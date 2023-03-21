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

import static chess.domain.team.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WhitePizeceGeneratorTest {

    PieceGenerator pieceGenerator;

    @BeforeEach
    void setUp() {
        pieceGenerator = new WhitePieceGenerator();
    }

    @Test
    @DisplayName("흰색 체스 기물 종류는 6개이다")
    void WhitePieceSizeIs6() {
        // given
        final int expected = 6;

        // when
        final Supplier<List<Piece>> supplier = pieceGenerator::generate;

        // then
        assertThat(supplier.get().size()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("createWhiteGenerator")
    void contains_whitePiece(Piece piece) {
        // when
        final List<Piece> pieces = pieceGenerator.generate();
        final boolean actual = pieces.contains(piece);

        // then
        assertTrue(actual);
    }

    private static Stream<Arguments> createWhiteGenerator() {
        return Stream.of(
                Arguments.of(new King(WHITE)),
                Arguments.of(new Queen(WHITE)),
                Arguments.of(new Bishop(WHITE)),
                Arguments.of(new Knight(WHITE)),
                Arguments.of(new Rook(WHITE)),
                Arguments.of(new Pawn(WHITE))
        );
    }
}
