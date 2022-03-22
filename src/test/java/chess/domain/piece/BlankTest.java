package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Blank;
import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlankTest {

    @DisplayName("빈 칸 기물이 생성되었을 때 점으로 표시된다.")
    @Test
    void constructor() {
        Blank blank = new Blank(new Position("a1"));

        assertThat(blank.getSignature()).isEqualTo('.');
    }
}
