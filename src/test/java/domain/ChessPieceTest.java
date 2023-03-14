package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPieceTest {

    @Test
    @DisplayName("Piece를 받아서 검은색 ChessPiece를 생성한다")
    public void testMakeBlack() {
        //given
        final Piece piece = Piece.KING;

        //when
        final ChessPiece chessPiece = ChessPiece.makeBlack(piece);

        //then
        assertThat(chessPiece).extracting("piece").isEqualTo(piece);
        assertThat(chessPiece).extracting("color").isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("Piece를 받아서 하얀색 ChessPiece를 생성한다")
    public void testMakeWhite() {
        //given
        final Piece piece = Piece.KING;

        //when
        final ChessPiece chessPiece = ChessPiece.makeWhite(piece);

        //then
        assertThat(chessPiece).extracting("piece").isEqualTo(piece);
        assertThat(chessPiece).extracting("color").isEqualTo(Color.WHITE);
    }
}
