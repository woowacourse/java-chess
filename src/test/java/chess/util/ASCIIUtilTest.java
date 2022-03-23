package chess.util;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ASCIIUtilTest {
    @DisplayName("입력된 두개의 문자가 같은 케이스면 참을 반환한다.")
    @Test
    void verifySameCase_True() {
        assertThat(ASCIIUtil.verifySameCase("A", "A")).isTrue();
        assertThat(ASCIIUtil.verifySameCase("a", "a")).isTrue();
    }

    @DisplayName("입력된 두개의 문자가 다른 케이스면 거짓을 반환한다.")
    @Test
    void verifySameCase_False() {
        assertThat(ASCIIUtil.verifySameCase("A", "a")).isFalse();
    }
}
