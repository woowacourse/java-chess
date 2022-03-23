package chess;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    @DisplayName("Piece의 타입이 일치하는지 검증한다.")
    void piece_type() {
        //given
        Piece piece = new Piece(PieceType.PAWN, PieceColor.BLACK);

        //when
        boolean actual = piece.isSameType(PieceType.PAWN);

        //then
        Assertions.assertThat(actual).isTrue();
    }
}
