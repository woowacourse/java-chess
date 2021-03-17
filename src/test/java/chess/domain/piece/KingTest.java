package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {
    @DisplayName("King 객체 생성 확인")
    @Test
    void 킹_객체_생성() {
        King king = new King(Position.of('e', '8'), "K");

        assertThat(king.getPosition()).isEqualTo(Position.of('e', '8'));
        assertThat(king.getName()).isEqualTo("K");
    }

    @DisplayName("초기화된 King 객체들 생성 확인")
    @Test
    void 킹_객체들_생성() {
        List<King> kings = King.generate();

        assertThat(kings.size()).isEqualTo(2);
    }
}
