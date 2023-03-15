package chess;

import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.Piece;
import chess.piece.Pieces;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesTest {

    @Test
    @DisplayName("기물 32개를 생성한다.")
    void generateInitialPieces() {
        // given
        final Pieces pieces = new Pieces();

        // when
        final List<Piece> generatedPieces = pieces.generateInitialPieces();

        // then
        assertThat(generatedPieces.size()).isEqualTo(32);
    }
}
