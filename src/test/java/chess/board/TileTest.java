package chess.board;

import chess.board.piece.King;
import chess.board.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TileTest {
    private static Stream<Arguments> pieceProvider() {
        return Stream.of(
                Arguments.of(new King(), true),
                Arguments.of(null, false)
        );
    }

    @DisplayName("피스가 있는 경우 확인")
    @ParameterizedTest
    @MethodSource("pieceProvider")
    void hasPiece(Piece piece, boolean expect) {
        Tile tile = new Tile(Coordinate.of(File.A, Rank.ONE), piece);
        assertThat(tile.hasPiece()).isEqualTo(expect);
    }
}