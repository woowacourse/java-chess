package chess.domain.piece.knight;

import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {

    private Piece whiteKnight;
    private Piece blackKnight;

    @BeforeEach
    void setUp() {
        whiteKnight = Knight.getInstanceOf(Owner.WHITE);
        blackKnight = Knight.getInstanceOf(Owner.BLACK);
    }

    @Test
    @DisplayName("블랙나이트외 화이트나이트 인스턴스 잘 가져온다.")
    void getInstanceOfTest() {
        assertThat(whiteKnight).isInstanceOf(WhiteKnight.class);
        assertThat(blackKnight).isInstanceOf(BlackKnight.class);
    }

    @Test
    @DisplayName("getInstanceOf의 인자로 NONE을 넘기면 예외가 발생한다.")
    void getInstanceOfThrowExceptionTest() {
        assertThatThrownBy(() -> {
            Knight.getInstanceOf(Owner.NONE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid Knight");
    }

    @Test
    @DisplayName("나이트의 최대 이동거리를 반환한다.")
    void maxDistanceTest() {
        //given
        int knightMaxDistance = blackKnight.maxDistance();

        //then
        assertThat(knightMaxDistance).isEqualTo(1);
    }

    @Test
    @DisplayName("나이트의 심볼 반환된다.")
    void getSymbolTest() {
        //given
        String whiteKnightSymbol = whiteKnight.getSymbol();
        String blackKnightSymbol = blackKnight.getSymbol();

        //then
        assertThat(whiteKnightSymbol).isEqualTo("n");
        assertThat(blackKnightSymbol).isEqualTo("N");
    }
}