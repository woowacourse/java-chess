package domain.board.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampTest {

    @DisplayName("WHITE 로 진영을 검색시 하얀색 진영을 반환받는다.")
    @Test
    void findByNameWhite() {
        Camp camp = Camp.findByName("WHITE");
        Assertions.assertThat(camp).isEqualTo(Camp.WHITE);
    }

    @DisplayName("BLACK 로 진영을 검색시 검은색 진영을 반환받는다.")
    @Test
    void findByNameBlack() {
        Camp camp = Camp.findByName("BLACK");
        Assertions.assertThat(camp).isEqualTo(Camp.BLACK);
    }

    @DisplayName("NONE 로 진영을 검색시 NONE 진영을 반환받는다.")
    @Test
    void findByNameNone() {
        Camp camp = Camp.findByName("NONE");
        Assertions.assertThat(camp).isEqualTo(Camp.NONE);
    }
}
