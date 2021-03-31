package chess.domain.piece.bishop;

import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {

    private Piece whiteBishop;
    private Piece blackBishop;

    @BeforeEach
    void setUp() {
        whiteBishop = Bishop.getInstanceOf(Owner.WHITE);
        blackBishop = Bishop.getInstanceOf(Owner.BLACK);
    }

    @Test
    @DisplayName("블랙비숍과 화이트비숍 인스턴스 잘 가져온다.")
    void getInstanceOfTest() {
        assertThat(whiteBishop).isInstanceOf(WhiteBishop.class);
        assertThat(blackBishop).isInstanceOf(BlackBishop.class);
    }

    @Test
    @DisplayName("getInstanceOf의 인자로 NONE을 넘기면 예외가 발생한다.")
    void getInstanceOfThrowExceptionTest() {
        assertThatThrownBy(() -> {
            Bishop.getInstanceOf(Owner.NONE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid Bishop");
    }

    @Test
    @DisplayName("비숍의 최대 이동거리를 반환한다.")
    void maxDistanceTest() {
        //given
        int bishopMaxDistance = whiteBishop.maxDistance();

        //then
        assertThat(bishopMaxDistance).isEqualTo(7);
    }

    @Test
    @DisplayName("퀸의 심볼 반환된다.")
    void getSymbolTest() {
        //given
        String whiteBishopSymbol = whiteBishop.getSymbol();
        String blackBishopSymbol = blackBishop.getSymbol();

        //then
        assertThat(whiteBishopSymbol).isEqualTo("b");
        assertThat(blackBishopSymbol).isEqualTo("B");
    }

}