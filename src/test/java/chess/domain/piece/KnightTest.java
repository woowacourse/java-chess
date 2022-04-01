package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KnightTest {

    private Knight whiteKnight;

    @BeforeEach
    void setUp() {
        whiteKnight = new Knight(Color.WHITE);
    }

    @ParameterizedTest
    @MethodSource("providePosition")
    @DisplayName("Knight가 움직일 수 있는 포지션인 경우 True를 반환한다")
    void isMovable(Position from, Position to) {
        assertThat(whiteKnight.isMovable(from, to)).isTrue();
    }

    private static Stream<Arguments> providePosition() {
        return Stream.of(
                Arguments.of(Position.valueOf(File.C, Rank.TWO), Position.valueOf(File.A, Rank.ONE)),
                Arguments.of(Position.valueOf(File.C, Rank.ONE), Position.valueOf(File.A, Rank.TWO)),
                Arguments.of(Position.valueOf(File.B, Rank.TWO), Position.valueOf(File.D, Rank.THREE)),
                Arguments.of(Position.valueOf(File.B, Rank.TWO), Position.valueOf(File.D, Rank.ONE)),
                Arguments.of(Position.valueOf(File.B, Rank.ONE), Position.valueOf(File.A, Rank.THREE)),
                Arguments.of(Position.valueOf(File.A, Rank.ONE), Position.valueOf(File.B, Rank.THREE)),
                Arguments.of(Position.valueOf(File.B, Rank.THREE), Position.valueOf(File.A, Rank.ONE)),
                Arguments.of(Position.valueOf(File.A, Rank.THREE), Position.valueOf(File.B, Rank.ONE)));
    }

    @ParameterizedTest
    @MethodSource("provideNonMovablePosition")
    @DisplayName("Knight가 움직일 수 없는 경우 False를 반환한다.")
    void isNotMovableTest(Position from, Position to) {
        assertThat(whiteKnight.isMovable(from, to)).isFalse();
    }

    private static Stream<Arguments> provideNonMovablePosition() {
        return Stream.of(
                Arguments.of(Position.valueOf(File.E, Rank.THREE), Position.valueOf(File.A, Rank.ONE)),
                Arguments.of(Position.valueOf(File.C, Rank.ONE), Position.valueOf(File.B, Rank.TWO)),
                Arguments.of(Position.valueOf(File.B, Rank.TWO), Position.valueOf(File.F, Rank.FOUR)),
                Arguments.of(Position.valueOf(File.A, Rank.THREE), Position.valueOf(File.A, Rank.TWO)));
    }


}
