package chess.domain.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegexUtilsTest {

    @Test
    @DisplayName("유효한 알파벳 확인")
    void isValidAlphaColumn() {
        assertTrue(RegexUtils.isValidAlphaColumn("a"));
        assertTrue(RegexUtils.isValidAlphaColumn("h"));
    }

    @Test
    @DisplayName("유효하지 않은 알파벳 확인 - 대문자")
    void isValidAlphaColumn_fail() {
        assertFalse(RegexUtils.isValidAlphaColumn("A"));
        assertFalse(RegexUtils.isValidAlphaColumn("H"));
    }

    @Test
    @DisplayName("유효하지 않은 알파벳 확인 - 범위 밖")
    void isValidAlphaColumn_fail1() {
        assertFalse(RegexUtils.isValidAlphaColumn("i"));
    }

    @Test
    @DisplayName("유효한 숫자 확인")
    void isValidRowNumber() {
        assertTrue(RegexUtils.isValidRowNumber("1"));
        assertTrue(RegexUtils.isValidRowNumber("8"));
    }

    @Test
    @DisplayName("유효하지 않은 숫자 확인 - 범위 밖")
    void iisValidRowNumber_fail() {
        assertFalse(RegexUtils.isValidRowNumber("0"));
        assertFalse(RegexUtils.isValidRowNumber("9"));
        assertFalse(RegexUtils.isValidRowNumber("11"));
    }

}