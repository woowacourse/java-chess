package domain.piece.attribute.point;

import domain.piece.attribute.point.File;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.ArgumentUtils;

import java.util.stream.Stream;


class FileTest {
    private static Stream<Arguments> maskingValues() {
        return Stream.of(
                Arguments.of(File.A, 'a'),
                Arguments.of(File.B, 'b'),
                Arguments.of(File.C, 'c'),
                Arguments.of(File.D, 'd'),
                Arguments.of(File.E, 'e'),
                Arguments.of(File.F, 'f'),
                Arguments.of(File.G, 'g'),
                Arguments.of(File.H, 'h')
        );
    }
    @ParameterizedTest
    @MethodSource("maskingValues")
    @DisplayName("파일은 가지고 있는 값에 따라 생성 인스턴스가 바뀐다.")
    void generate_different_instance_from_character(File file,char text) {
        Assertions.assertThat(File.from(text)).isEqualTo(file);
    }
}
