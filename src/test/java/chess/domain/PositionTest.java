package chess.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PositionTest {

    @Test
    void 같은_File과_같은_Rank일_경우_동일한_객체가_반환된다() {
        //given
        Position position1 = Position.of("a", "1");
        Position position2 = Position.of("a", "1");

        //when
        boolean result = position2 == position1;

        //then
        assertThat(result)
                .isTrue();
    }
}
