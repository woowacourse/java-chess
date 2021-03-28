package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EmptyTest {
    private Pieces initialPieces;

    @BeforeEach
    void setUp() {
        initialPieces = new Pieces(PieceFactory.initialPieces());
    }

    @DisplayName("Empty 객체 생성 확인")
    @Test
    void 빈_기물_객체_생성_테스트() {
        Empty empty = new Empty(Position.of("a1"));

        assertThat(empty.isSamePosition(Position.of("a1"))).isTrue();
        assertThat(empty.name()).isEqualTo(".");
    }

    @DisplayName("Empty 객체를 움직일 경우 예외")
    @Test
    void 움직일_수_없다() {
        Empty empty = new Empty(Position.of("a1"));
        Position target = Position.of("e6");

        assertThatThrownBy(() -> empty.move(target))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
