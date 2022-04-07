package chess.model.piece;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.model.PieceColor;

class PieceCacheTest {

    @Test
    @DisplayName("캐싱된 객체의 동등성 검사")
    void of() {
        //given
        List<Piece> actual = List.of(PieceCache.of("P"), PieceCache.of("p"),
        PieceCache.of("K"));

        //when
        List<Piece> expected = List.of(Pawn.colorOf(PieceColor.BLACK), Pawn.colorOf(PieceColor.WHITE),
            King.colorOf(PieceColor.BLACK));

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}