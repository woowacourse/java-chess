package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampTest {

    @DisplayName("현재 팀에 따른 상대 팀을 반환한다.")
    @Test
    void 팀_턴_변경() {
        Camp camp = Camp.WHITE;

        assertThat(camp = camp.transfer()).isEqualTo(Camp.BLACK);
        assertThat(camp.transfer()).isEqualTo(Camp.WHITE);
    }

}
