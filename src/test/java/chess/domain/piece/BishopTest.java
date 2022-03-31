package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.move.BishopMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("컬러를 none으로 생성하면 에러가 발생한다.")
    void constructorException() {
        assertThatThrownBy(() -> new Bishop(Team.NONE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 팀을 입력해주세요.");
    }

    @Test
    @DisplayName("비숍 이동 전략을 가져온다.")
    void getMoveStrategy() {
        assertThat(new Bishop(Team.BLACK).getMoveStrategy()).isInstanceOf(BishopMoveStrategy.class);
    }

    @Test
    @DisplayName("King이 아니다.")
    void isKing() {
        assertThat(new Bishop(Team.BLACK).isKing()).isFalse();
    }
}