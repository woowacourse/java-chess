package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceConvertorTest {
    @Test
    @DisplayName("생성 확인 테스트")
    void pieceConverterCreateTest(){
        // given
        Piece whiteKing = PieceConvertor.of("K", Color.WHITE);

        // when & then
        assertThat(whiteKing).isInstanceOf(King.class);
    }
}