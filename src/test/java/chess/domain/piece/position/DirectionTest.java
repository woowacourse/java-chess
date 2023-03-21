package chess.domain.piece.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Direction 은")
class DirectionTest {

    @ParameterizedTest(name = "변위로 방위를 찾을 수 있다. 변위가 [file = {0}, rank = {1}] 일 때, 방위는 {2} 이다.")
    @CsvSource({
            "8,0,EAST",
            "-4,0,WEST",
            "0,-5,SOUTH",
            "0,4,NORTH",
            "6,2,NORTHEAST",
            "2,-7,SOUTHEAST",
            "-2,-4,SOUTHWEST",
            "-3,5,NORTHWEST",
    })
    void 변위로_방위를_찾을_수_있다(final int fileDisplacement, final int rankDisplacement, final Direction expected) {
        // when
        final Direction result = Direction.byInterval(rankDisplacement, fileDisplacement);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
