package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Pieces;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("킹 테스트")
class KingTest {

    private Piece king;
    private Map<Position, Piece> pieces;

    @BeforeEach
    void setUp() {
        Position position = Position.of("e5");

        king = new King(Color.BLACK, position);

        Pieces pieces = new Pieces();
        pieces.init();

        this.pieces = pieces.pieces();
    }

    @Test
    @DisplayName("이동검사")
    void move() {
        assertThatCode(() -> king.move(Position.of("d6"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> king.move(Position.of("e6"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> king.move(Position.of("f6"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> king.move(Position.of("d5"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> king.move(Position.of("f5"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> king.move(Position.of("d4"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> king.move(Position.of("e4"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> king.move(Position.of("f4"), pieces)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이동 에러 검사")
    void validate() {
        assertThatThrownBy(() -> king.move(Position.of("b6"), pieces))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> king.move(Position.of("g6"), pieces))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> king.move(Position.of("h6"), pieces))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> king.move(Position.of("g5"), pieces))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> king.move(Position.of("c5"), pieces))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> king.move(Position.of("d3"), pieces))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> king.move(Position.of("e3"), pieces))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> king.move(Position.of("f3"), pieces))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(king.score()).isEqualTo(0);
    }

    @Test
    @DisplayName("흰색일때 심볼 테스트")
    void whiteSymbol() {
        Piece piece = new King(Color.WHITE, Position.of("a1"));
        assertThat(piece.symbol()).isEqualTo("k");
    }

    @Test
    @DisplayName("검정색일때 심볼 테스트")
    void blackSymbol() {
        Piece piece = new King(Color.BLACK, Position.of("a1"));
        assertThat(piece.symbol()).isEqualTo("K");
    }
}