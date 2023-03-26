package chess.utils;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CharToPieceTest {

    @Test
    @DisplayName("piceName에 해당하는 Piece를 반환할 수 있다.")
    void convertCharToPieceTest() {
        // given
        char pieceName = 'K';

        // when
        Piece convertedPiece = CharToPiece.of(pieceName);

        // then
        assertThat(convertedPiece).isEqualTo(new King(Color.BLACK));
    }
}