package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Pieces;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("나이트 테스트")
class KnightTest {

    private Piece knight;
    private Map<Position, Piece> pieces;

    @BeforeEach
    void setUp() {
        Position position = Position.of("e5");

        knight = new Knight(Color.BLACK, position);

        Pieces pieces = new Pieces();
        pieces.init();

        this.pieces = pieces.pieces();
    }

    @Test
    @DisplayName("이동검사")
    void move() {
        pieces.put(Position.of("d7"), new Blank());
        pieces.put(Position.of("f7"), new Blank());

        assertThatCode(() -> knight.move(Position.of("d7"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> knight.move(Position.of("f7"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> knight.move(Position.of("c6"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> knight.move(Position.of("c4"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> knight.move(Position.of("g6"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> knight.move(Position.of("g4"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> knight.move(Position.of("d3"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> knight.move(Position.of("f3"), pieces)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이동 에러 검사")
    void validate() {
        assertThatThrownBy(() -> knight.move(Position.of("c5"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> knight.move(Position.of("e6"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> knight.move(Position.of("g5"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> knight.move(Position.of("e3"), pieces)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> knight.move(Position.of("c3"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> knight.move(Position.of("g3"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> knight.move(Position.of("h3"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> knight.move(Position.of("f4"), pieces)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(knight.score()).isEqualTo(2.5);
    }

    @Test
    @DisplayName("흰색일때 심볼 테스트")
    void whiteSymbol() {
        Piece piece = new Knight(Color.WHITE, Position.of("a1"));
        assertThat(piece.symbol()).isEqualTo("n");
    }

    @Test
    @DisplayName("검정색일때 심볼 테스트")
    void blackSymbol() {
        Piece piece = new Knight(Color.BLACK, Position.of("a1"));
        assertThat(piece.symbol()).isEqualTo("N");
    }
}