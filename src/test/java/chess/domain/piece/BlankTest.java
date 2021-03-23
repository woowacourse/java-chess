package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Pieces;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("빈공간 테스트")
class BlankTest {

    private Blank blank;
    private Map<Position, Piece> pieces;

    @BeforeEach
    void setUp() {
        blank = new Blank(Color.BLANK, Position.of("a3"));

        Pieces pieces = new Pieces();
        pieces.init();

        this.pieces = pieces.pieces();
    }

    @Test
    @DisplayName("이동 에러 검사")
    void validate() {
        Position position = Position.of(3, 1);

        assertThatThrownBy(() -> blank.move(position, pieces))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(blank.score()).isEqualTo(0);
    }

    @Test
    @DisplayName("심볼 테스트")
    void symbol() {
        assertThat(blank.symbol()).isEqualTo(".");
    }
}