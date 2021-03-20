package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EmptyTest {
    private CurrentPieces initialPieces;

    @BeforeEach
    void setUp() {
        initialPieces = new CurrentPieces(PieceFactory.initialPieces());
    }

    @DisplayName("Empty 객체 생성 확인")
    @Test
    void 빈_기물_객체_생성_테스트() {
        Empty empty = new Empty();

        assertThat(empty.getPosition()).isEqualTo(Position.EMPTY);
        assertThat(empty.getName()).isEqualTo(".");
    }

    @DisplayName("Empty 객체를 움직일 경우 예외")
    @Test
    void 움직일_수_없다() {
        Empty empty = new Empty();
        Position target = Position.of('e', '6');

        assertThatThrownBy(() -> empty.move(target, initialPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
