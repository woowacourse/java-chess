package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PositionTest {

    @Test
    @DisplayName("File과 Rank를 받아서 Position을 생성한다.")
    void generatePosition() {
        // when, then
        Assertions.assertDoesNotThrow(() -> new Position(File.A, Rank.ONE));
    }

    @Test
    @DisplayName("받은 File과 Rank로 이동한다.")
    void move() {
        // given
        final Position position = new Position(File.A, Rank.ONE);

        // when
        position.move(5, 7);

        // then
        assertThat(position.getFile()).isEqualTo(File.F);
        assertThat(position.getRank()).isEqualTo(Rank.EIGHT);
    }

    @ParameterizedTest
    @MethodSource("generatePoint")
    @DisplayName("입력받은 칸만큼 이동한다.")
    void move2(int x, int y, File expectedFile, Rank expectedRank) {
        // given
        final Position position = new Position(File.A, Rank.ONE);

        // when
        position.move(x, y);

        // then
        assertThat(position.getFile()).isEqualTo(expectedFile);
        assertThat(position.getRank()).isEqualTo(expectedRank);
    }

    static Stream<Arguments> generatePoint() {
        return Stream.of(
                Arguments.of(1, 0, File.B, Rank.ONE),
                Arguments.of(0, 2, File.A, Rank.THREE),
                Arguments.of(1, 1, File.B, Rank.TWO)
        );
    }
}
