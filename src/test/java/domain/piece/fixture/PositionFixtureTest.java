package domain.piece.fixture;

import static domain.position.File.A;
import static domain.position.File.B;
import static domain.position.File.C;
import static domain.position.File.D;
import static domain.position.File.E;
import static domain.position.File.F;
import static domain.position.File.G;
import static domain.position.File.H;
import static domain.position.Rank.EIGHT;
import static domain.position.Rank.FIVE;
import static domain.position.Rank.FOUR;
import static domain.position.Rank.ONE;
import static domain.position.Rank.SEVEN;
import static domain.position.Rank.SIX;
import static domain.position.Rank.THREE;
import static domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import domain.position.File;
import domain.position.Position;
import domain.position.PositionGenerator;
import domain.position.Rank;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PositionFixtureTest {

    private static Stream<Arguments> allFilesAndRanks() {
        return Stream.of(
                Arguments.of(A, ONE),
                Arguments.of(A, TWO),
                Arguments.of(A, THREE),
                Arguments.of(A, FOUR),
                Arguments.of(A, FIVE),
                Arguments.of(A, SIX),
                Arguments.of(A, SEVEN),
                Arguments.of(A, EIGHT),
                Arguments.of(B, ONE),
                Arguments.of(B, TWO),
                Arguments.of(B, THREE),
                Arguments.of(B, FOUR),
                Arguments.of(B, FIVE),
                Arguments.of(B, SIX),
                Arguments.of(B, SEVEN),
                Arguments.of(B, EIGHT),
                Arguments.of(C, ONE),
                Arguments.of(C, TWO),
                Arguments.of(C, THREE),
                Arguments.of(C, FOUR),
                Arguments.of(C, FIVE),
                Arguments.of(C, SIX),
                Arguments.of(C, SEVEN),
                Arguments.of(C, EIGHT),
                Arguments.of(D, ONE),
                Arguments.of(D, TWO),
                Arguments.of(D, THREE),
                Arguments.of(D, FOUR),
                Arguments.of(D, FIVE),
                Arguments.of(D, SIX),
                Arguments.of(D, SEVEN),
                Arguments.of(D, EIGHT),
                Arguments.of(E, ONE),
                Arguments.of(E, TWO),
                Arguments.of(E, THREE),
                Arguments.of(E, FOUR),
                Arguments.of(E, FIVE),
                Arguments.of(E, SIX),
                Arguments.of(E, SEVEN),
                Arguments.of(E, EIGHT),
                Arguments.of(F, ONE),
                Arguments.of(F, TWO),
                Arguments.of(F, THREE),
                Arguments.of(F, FOUR),
                Arguments.of(F, FIVE),
                Arguments.of(F, SIX),
                Arguments.of(F, SEVEN),
                Arguments.of(F, EIGHT),
                Arguments.of(G, ONE),
                Arguments.of(G, TWO),
                Arguments.of(G, THREE),
                Arguments.of(G, FOUR),
                Arguments.of(G, FIVE),
                Arguments.of(G, SIX),
                Arguments.of(G, SEVEN),
                Arguments.of(G, EIGHT),
                Arguments.of(H, ONE),
                Arguments.of(H, TWO),
                Arguments.of(H, THREE),
                Arguments.of(H, FOUR),
                Arguments.of(H, FIVE),
                Arguments.of(H, SIX),
                Arguments.of(H, SEVEN),
                Arguments.of(H, EIGHT)
        );
    }

    @Test
    @DisplayName("하나의 Position 객체를 제외한 나머지 Position 객체들을 리스트로 반환한다.")
    void otherPositions_Position() {
        Position position = PositionGenerator.generate(A, ONE);

        List<Position> otherPositions = PositionFixture.otherPositions(position);

        assertThat(otherPositions).doesNotContain(position);
    }

    @Test
    @DisplayName("하나의 Position 객체를 제외한 나머지 Position 객체들을 리스트로 반환한다.")
    void otherPositions_Positions() {
        Position position1 = PositionGenerator.generate(A, ONE);
        Position position2 = PositionGenerator.generate(A, TWO);

        List<Position> otherPositions = PositionFixture.otherPositions(List.of(position1, position2));

        assertThat(otherPositions).doesNotContain(position1, position2);
    }

    @ParameterizedTest
    @MethodSource("allFilesAndRanks")
    @DisplayName("파일 A~H 랭크 1~8의 Position 객체를 생성한다.")
    void generate_Positions(File file, Rank rank) {
        assertThat(PositionFixture.get(file, rank)).isInstanceOf(Position.class);
    }
}
