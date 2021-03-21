package chess.domain.board;

import chess.domain.piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class FileTest {

    private static Stream<Arguments> getFileInputAndEnum() {
        return Stream.of(Arguments.of("a", File.A),
                Arguments.of("c", File.C),
                Arguments.of("h", File.H));
    }

    private static Stream<Arguments> getDirectionAndFile() {
        return Stream.of(Arguments.of(Direction.LEFT_DOWN_DOWN, File.B),
                Arguments.of(Direction.LEFT_LEFT_UP, File.A),
                Arguments.of(Direction.UP, File.C),
                Arguments.of(Direction.RIGHT, File.D),
                Arguments.of(Direction.RIGHT_RIGHT_DOWN, File.E));
    }

    @DisplayName("문자열과 매치되는 File(행) 열거형을 탐색한다.")
    @ParameterizedTest
    @MethodSource("getFileInputAndEnum")
    void findFile(String fileInput, File expectedFile) {
        File file = File.findFileBySignature(fileInput);

        assertThat(file).isEqualTo(expectedFile);
    }

    @DisplayName("문자열과 매치되는 File이 없는 경우 예외를 발생한다.")
    @Test
    void cannotFindFile() {
        assertThatCode(() -> File.findFileBySignature("i"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 File(행)값 입니다.");
    }

    @DisplayName("두 File의 x값의 차이를 계산한다.")
    @Test
    void calculateDifference() {
        File firstFile = File.A;
        File secondFile = File.E;

        int difference = firstFile.calculateDifference(secondFile);

        assertThat(difference).isEqualTo(-4);
    }

    @DisplayName("Direction에 정의된 x값만큼 이동한다.")
    @ParameterizedTest
    @MethodSource("getDirectionAndFile")
    void move(Direction direction, File expectedFile) {
        File file = File.C;

        File movedFile = file.move(direction);

        assertThat(movedFile).isEqualTo(expectedFile);
    }

    @DisplayName("Direction만큼 File을 이동했을 때 x값이 a~h 범위가 아닌 경우 예외가 발생한다.")
    @Test
    void cannotMove() {
        assertThatCode(() -> File.B.move(Direction.LEFT_LEFT_UP))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 File(행)값 입니다.");
    }
}
