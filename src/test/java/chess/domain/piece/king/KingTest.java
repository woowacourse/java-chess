package chess.domain.piece.king;

import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    private Piece whiteKing;
    private Piece blackKing;

    @BeforeEach
    void setUp() {
        whiteKing = King.getInstanceOf(Owner.WHITE);
        blackKing = King.getInstanceOf(Owner.BLACK);
    }

    @Test
    @DisplayName("블랙킹과 화이트킹 인스턴스 잘 가져온다.")
    void getInstanceOfTest() {
        assertThat(whiteKing).isInstanceOf(WhiteKing.class);
        assertThat(blackKing).isInstanceOf(BlackKing.class);
    }

    @Test
    @DisplayName("getInstanceOf의 인자로 NONE을 넘기면 예외가 발생한다.")
    void getInstanceOfThrowExceptionTest() {
        assertThatThrownBy(() -> {
            King.getInstanceOf(Owner.NONE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid King");
    }

    @Test
    @DisplayName("킹의 최대 이동거리를 반환한다.")
    void maxDistanceTest() {
        //given
        int kingMaxDistance = whiteKing.maxDistance();

        //then
        assertThat(kingMaxDistance).isEqualTo(1);
    }

    @Test
    @DisplayName("킹의 심볼 반환된다.")
    void getSymbolTest() {
        //given
        String whiteKingSymbol = whiteKing.getSymbol();
        String blackKingSymbol = blackKing.getSymbol();

        //then
        assertThat(whiteKingSymbol).isEqualTo("k");
        assertThat(blackKingSymbol).isEqualTo("K");
    }
}