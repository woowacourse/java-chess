package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FormTest {

    @Test
    @DisplayName("검은색 폰의 형식 가져오기")
    void getBlackPawnForm() {
        assertThat(Form.getForm(new Pawn(Color.BLACK))).isEqualTo("♟");
    }

    @Test
    @DisplayName("흰색 폰의 형식 가져오기")
    void getWhitePawnForm() {
        assertThat(Form.getForm(new Pawn(Color.WHITE))).isEqualTo("♙");
    }

    @Test
    @DisplayName("검은색 룩의 형식 가져오기")
    void getBlackRookForm() {
        assertThat(Form.getForm(new Rook(Color.BLACK))).isEqualTo("♜");
    }

    @Test
    @DisplayName("흰색 룩의 형식 가져오기")
    void getWhiteRookForm() {
        assertThat(Form.getForm(new Rook(Color.WHITE))).isEqualTo("♖");
    }

    @Test
    @DisplayName("검은색 나이트의 형식 가져오기")
    void getBlackKnightForm() {
        assertThat(Form.getForm(new Knight(Color.BLACK))).isEqualTo("♞");
    }

    @Test
    @DisplayName("흰색 나이트의 형식 가져오기")
    void getWhiteKnightForm() {
        assertThat(Form.getForm(new Knight(Color.WHITE))).isEqualTo("♘");
    }

    @Test
    @DisplayName("검은색 비숍의 형식 가져오기")
    void getBlackBishopForm() {
        assertThat(Form.getForm(new Bishop(Color.BLACK))).isEqualTo("♝");
    }

    @Test
    @DisplayName("흰색 비숍의 형식 가져오기")
    void getWhiteBishopForm() {
        assertThat(Form.getForm(new Bishop(Color.WHITE))).isEqualTo("♗");
    }

    @Test
    @DisplayName("검은색 퀸의 형식 가져오기")
    void getBlackQueenForm() {
        assertThat(Form.getForm(new Queen(Color.BLACK))).isEqualTo("♛");
    }

    @Test
    @DisplayName("흰색 퀸의 형식 가져오기")
    void getWhiteQueenForm() {
        assertThat(Form.getForm(new Queen(Color.WHITE))).isEqualTo("♕");
    }

    @Test
    @DisplayName("검은색 킹의 형식 가져오기")
    void getBlackKingForm() {
        assertThat(Form.getForm(new King(Color.BLACK))).isEqualTo("♚");
    }

    @Test
    @DisplayName("흰색 킹의 형식 가져오기")
    void getWhiteKingForm() {
        assertThat(Form.getForm(new King(Color.WHITE))).isEqualTo("♔");
    }

    @Test
    @DisplayName("기물이 없는 경우")
    void getNullForm() {
        assertThat(Form.getForm(null)).isEqualTo(".");
    }
}