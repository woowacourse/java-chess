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

    @Test
    void 포지션의_랭크_차이_계산_테스트() {
        //given
        Position position1 = Position.of("a", "1");
        Position position2 = Position.of("a", "3");

        //when
        int result = position1.getRankDifference(position2);

        //then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void 포지션의_파일_차이_계산_테스트() {
        //given
        Position position1 = Position.of("a", "1");
        Position position2 = Position.of("f", "1");

        //when
        int result = position1.getFileDifference(position2);

        //then
        assertThat(result).isEqualTo(5);
    }
}
