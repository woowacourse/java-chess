package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class KingTest {
    @Test
    @DisplayName("King 인스턴스를 정상적으로 생성하는 지 테스트")
    public void init() {
        assertThatCode(() -> {
            new King(false, 'a', '1');
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{displayName}")
    @DisplayName("검은색, 흰색일 때 각각의 이름을 잘불러오는 지 테스트")
    @CsvSource(value = {"true:K", "false:k"}, delimiter = ':')
    public void getName(boolean isBlack, char name) {
        Piece piece = new King(isBlack, 'a', '1');
        assertThat(piece.name()).isEqualTo(name);
    }
}
