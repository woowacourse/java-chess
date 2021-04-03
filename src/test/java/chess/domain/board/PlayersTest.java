package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    private Players players;

    @BeforeEach
    void setUp() {
        players = new Players("white", "black");
    }

    @Test
    @DisplayName("유효성 테스트")
    void validate() {
        assertThatThrownBy(() -> new Players("a", "b"))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Players("12글자이상의닉네임은에러", "러가나야합니다."))
            .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("검은색 플레이어 가져오기 테스트")
    void getWhitePlayer() {
        assertThat(players.getBlackPlayer()).isEqualTo("black");
    }

    @Test
    @DisplayName("흰색 플레이어 가져오기 테스트")
    void getBlackPlayer() {
        assertThat(players.getWhitePlayer()).isEqualTo("white");
    }
}