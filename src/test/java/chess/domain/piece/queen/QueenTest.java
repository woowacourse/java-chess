package chess.domain.piece.queen;

import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueenTest {

    private Piece whiteQueen;
    private Piece blackQueen;

    @BeforeEach
    void setUp() {
        whiteQueen = Queen.getInstanceOf(Owner.WHITE);
        blackQueen = Queen.getInstanceOf(Owner.BLACK);
    }

    @Test
    @DisplayName("블랙퀸과 화이트퀸 인스턴스 잘 가져온다.")
    void getInstanceOfTest() {
        assertThat(whiteQueen).isInstanceOf(WhiteQueen.class);
        assertThat(blackQueen).isInstanceOf(BlackQueen.class);
    }

    @Test
    @DisplayName("getInstanceOf의 인자로 NONE을 넘기면 예외가 발생한다.")
    void getInstanceOfThrowExceptionTest() {
        assertThatThrownBy(() -> {
            Queen.getInstanceOf(Owner.NONE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid Queen");
    }

    @Test
    @DisplayName("퀸의 최대 이동거리를 반환한다.")
    void maxDistanceTest() {
        //given
        int queenMaxDistance = whiteQueen.maxDistance();

        //then
        assertThat(queenMaxDistance).isEqualTo(7);
    }

    @Test
    @DisplayName("퀸의 심볼 반환된다.")
    void getSymbolTest() {
        //given
        String whiteQueenSymbol = whiteQueen.getSymbol();
        String blackQueenSymbol = blackQueen.getSymbol();

        //then
        assertThat(whiteQueenSymbol).isEqualTo("q");
        assertThat(blackQueenSymbol).isEqualTo("Q");
    }
}