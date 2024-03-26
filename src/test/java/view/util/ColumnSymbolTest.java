package view.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColumnSymbolTest {

    @DisplayName("받은 값에 해당하는 식별자가 있는지 확인한다.")
    @Test
    void invalidIdentifier() {
        Assertions.assertThatThrownBy(() -> ColumnSymbol.from("I"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 식별자가 아닙니다.");
    }
}
