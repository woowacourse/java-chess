package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Piece;
import domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardSettingTest {

    @Test
    @DisplayName("말 찾기 테스트")
    public void testFindPiece() {
        //given
        final Location location = Location.of(1, 1);

        //when
        final Piece piece = BoardSetting.findPiece(location);

        //then
        assertThat(piece).isEqualTo(Rook.makeWhite());
    }
}
