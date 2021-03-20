package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Pieces;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("비숍 테스트")
class BishopTest {

    private Piece bishop;
    private Map<Position, Piece> pieces;

    @BeforeEach
    void setUp() {
        bishop = new Bishop(Color.BLANK, Position.of("e5"));

        Pieces pieces = new Pieces();
        pieces.init();

        this.pieces = pieces.pieces();
    }

    @Test
    @DisplayName("이동검사")
    void move() {
        assertThatCode(() -> bishop.move(Position.of("d6"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> bishop.move(Position.of("f6"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> bishop.move(Position.of("c3"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> bishop.move(Position.of("g3"), pieces)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이동 에러 검사")
    void validate() {
        assertThatThrownBy(() -> bishop.move(Position.of("a5"), pieces))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> bishop.move(Position.of("g5"), pieces))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> bishop.move(Position.of("e6"), pieces))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> bishop.move(Position.of("e3"), pieces))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(bishop.score()).isEqualTo(3);
    }

    @Test
    @DisplayName("흰색일때 심볼 테스트")
    void whiteSymbol() {
        Piece piece = new Bishop(Color.WHITE, Position.of("a1"));
        assertThat(piece.symbol()).isEqualTo("b");
    }

    @Test
    @DisplayName("검정색일때 심볼 테스트")
    void blackSymbol() {
        Piece piece = new Bishop(Color.BLACK, Position.of("a1"));
        assertThat(piece.symbol()).isEqualTo("B");
    }
}