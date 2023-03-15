package chess.domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class SquareTest {

    @Test
    @DisplayName("생성 테스트")
    void create() {
        assertThatCode(() -> Square.of(File.A, Rank.FIVE))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이미 존재하는 square에 대해서는 square를 새로 생성하지 않고 앞서 생성된 인스턴스를 가져온다.")
    void get_instance_when_already_exist() {
        Square testSquare = Square.of(File.A, Rank.FIVE);
        assertThat(testSquare).isSameAs(Square.of(File.A, Rank.FIVE));
    }
}