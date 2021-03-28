package chess.domain.piece.info;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NameTest {
    @DisplayName("색깔름에 따른 이름을 받는다.")
    @Test
    void 색깔에_따른_이름() {
        Name name = Name.BISHOP;

        String nameByColor = name.nameByColor(Color.WHITE);

        assertThat(nameByColor).isEqualTo("b");
    }
}
