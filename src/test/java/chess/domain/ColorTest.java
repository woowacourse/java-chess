package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ColorTest {

    @Test
    void 색은_개수가_2개이다() {
        final List<Color> colors = Arrays.stream(Color.values())
                .collect(Collectors.toList());

        assertThat(colors).containsExactly(Color.BLACK, Color.WHITE);
    }
}
