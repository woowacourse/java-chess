package chess.domain.piece;

import chess.domain.piece.Bishop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {
    @DisplayName("Bishop 객체 생성 확인")
    @Test
    void 비숍_객체_생성() {
        Bishop bishop = new Bishop(Position.of('c', '8'), "B");

        assertThat(bishop.getPosition()).isEqualTo(Position.of('c', '8'));
        assertThat(bishop.getName()).isEqualTo("B");
    }

    @DisplayName("초기화된 Bishop 객체들 생성 확인")
    @Test
    void 비숍_객체들_생성() {
        List<Bishop> bishops = Bishop.generate();

        assertThat(bishops.size()).isEqualTo(4);
    }
}
