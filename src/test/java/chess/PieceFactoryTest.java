package chess;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceFactoryTest {
    @DisplayName("팩토리로 생성시 크기 확인")
    @Test
    public void size() {
        //given
        List<Piece> pieces = PieceFactory.whitePieces();

        //when
        int size = pieces.size();

        //then
        assertThat(size).isEqualTo(16);
    }

    @DisplayName("팩토리로 생성시 크기 확인")
    @Test
    public void size2() {
        //given
        List<Piece> pieces = PieceFactory.blackPieces();

        //when
        int size = pieces.size();

        //then
        assertThat(size).isEqualTo(16);
    }
}
