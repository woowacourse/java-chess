package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

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
        assertThat(piece.getName()).isEqualTo(name);
    }

    @ParameterizedTest
    @DisplayName("상하좌우, 대각선 방향으로 1칸만 이동하도록 값을 제대로 입력할 경우 예외 발생 X - Grid의 범위를 안 벗어난다고 가정")
    @CsvSource(value = {"d:4", "e:3", "e:4", "c:3", "c:4"}, delimiter = ':')
    public void move(char nextX, char nextY) {
        assertThatCode(() -> {
            char x = 'd';
            char y = '3';
            Piece king = new King(true, x, y);
            king.move(nextX, nextY);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("상하좌우, 대각선 방향으로 1칸만 이동하도록 값을 제대로 입력할 경우 예외 발생 - Grid의 범위를 안 벗어난다고 가정")
    @CsvSource(value = {"f:3", "d:6", "e:7", "d:3"}, delimiter = ':')
    public void move_ThrowsException(char nextX, char nextY) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            char x = 'd';
            char y = '3';
            Piece king = new King(true, x, y);
            king.move(nextX, nextY);
        }).withMessage("이동할 수 있는 범위를 벗어났습니다.");
    }
}
