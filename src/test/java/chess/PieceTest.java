package chess;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    @DisplayName("Piece의 Rank와 File의 동일 여부를 검증한다.")
    void piece_equal() {
        //given
        Piece piece = new Piece(Rank.ONE, File.A);

        //when
        boolean actual = piece.isSamePosition(new Piece(Rank.ONE, File.A));

        //then
        Assertions.assertThat(actual).isTrue();
    }
}
