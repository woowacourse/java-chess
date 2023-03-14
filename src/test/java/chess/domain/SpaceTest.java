package chess.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SpaceTest {

    @Test
    void 피스가_존재하지_않는_칸_생성_테스트() {
        //given
        Space space = new Space();

        //when
        String result = space.getPieceName();

        //then
        assertThat(result).isEqualTo(".");
    }

    @Test
    void 피스가_존재하는_칸_생성_테스트() {
        //given
        Space q = new Space(new Piece("q"));

        //when
        String result = q.getPieceName();

        //then
        assertThat(result).isEqualTo("q");
    }
}
