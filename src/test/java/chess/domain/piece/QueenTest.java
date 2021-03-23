package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Pieces;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("퀸 테스트")
public class QueenTest {

    private Piece queen;
    private Map<Position, Piece> pieces;

    @BeforeEach
    void setUp() {
        queen = new Queen(Color.BLANK, Position.of("e5"));

        Pieces pieces = new Pieces();
        pieces.init();

        this.pieces = pieces.pieces();
    }

    @Test
    @DisplayName("이동검사")
    void move() {
        assertThatCode(() -> queen.move(Position.of("d6"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> queen.move(Position.of("e6"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> queen.move(Position.of("f6"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> queen.move(Position.of("a5"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> queen.move(Position.of("g5"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> queen.move(Position.of("d4"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> queen.move(Position.of("e3"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> queen.move(Position.of("g3"), pieces)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이동 에러 검사")
    void validate() {
        pieces.put(Position.of("d7"), new Blank());
        pieces.put(Position.of("f7"), new Blank());

        assertThatThrownBy(() -> queen.move(Position.of("d7"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> queen.move(Position.of("f7"), pieces)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.move(Position.of("c6"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> queen.move(Position.of("c4"), pieces)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.move(Position.of("g6"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> queen.move(Position.of("g4"), pieces)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.move(Position.of("d3"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> queen.move(Position.of("f3"), pieces)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(queen.score()).isEqualTo(9);
    }

    @Test
    @DisplayName("흰색일때 심볼 테스트")
    void whiteSymbol() {
        Piece piece = new Queen(Color.WHITE, Position.of("a1"));
        assertThat(piece.symbol()).isEqualTo("q");
    }

    @Test
    @DisplayName("검정색일때 심볼 테스트")
    void blackSymbol() {
        Piece piece = new Queen(Color.BLACK, Position.of("a1"));
        assertThat(piece.symbol()).isEqualTo("Q");
    }
}
