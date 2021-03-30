package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Pieces;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("룩 테스트")
class RookTest {

    private Piece rook;
    private Map<Position, Piece> pieces;

    @BeforeEach
    void setUp() {
        rook = new Rook(Color.BLACK, Position.of("e5"));

        Pieces pieces = new Pieces();
        pieces.init();

        this.pieces = pieces.pieces();
    }

    @Test
    @DisplayName("이동검사")
    void move() {
        assertThatCode(() -> rook.move(Position.of("a5"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> rook.move(Position.of("g5"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> rook.move(Position.of("e6"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> rook.move(Position.of("e3"), pieces)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이동 에러 검사")
    void validate() {
        assertThatThrownBy(() -> rook.move(Position.of("c6"), pieces))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> rook.move(Position.of("f6"), pieces))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> rook.move(Position.of("g3"), pieces))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> rook.move(Position.of("d3"), pieces))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(rook.score()).isEqualTo(5);
    }

    @Test
    @DisplayName("흰색일때 심볼 테스트")
    void whiteSymbol() {
        Piece piece = new Rook(Color.WHITE, Position.of("a1"));
        assertThat(piece.symbol()).isEqualTo("r");
    }

    @Test
    @DisplayName("검정색일때 심볼 테스트")
    void blackSymbol() {
        Piece piece = new Rook(Color.BLACK, Position.of("a1"));
        assertThat(piece.symbol()).isEqualTo("R");
    }
}