package chess.domain.distance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DistancesTest {
    private Distances distances;
    
    @BeforeEach
    void setUp() {
        distances = new Distances(-2, -5);
    }
    
    @Test
    void 두_값을_전부_절대값으로_변환_후_반환한다() {
        assertThat(distances.absoluteValue()).isEqualTo(new Distances(2, 5));
    }
    
    @Test
    void Column만_절대값으로_변환_후_반환한다() {
        assertThat(distances.absoluteColumn()).isEqualTo(new Distances(2, -5));
    }
    
    @Test
    void 두_값이_모두_0인_경우() {
        Distances distances = new Distances(0, 0);
        assertThat(distances.isBothZero()).isTrue();
    }
    
    @Test
    void 두_값이_다른_경우() {
        assertThat(distances.isBothDifferent()).isTrue();
    }
    
    @Test
    void 두_값중_하나라도_1보다_큰_경우() {
        Distances distances = new Distances(2, -5);
        assertThat(distances.isEitherGreaterThenOne()).isTrue();
    }
    
    @Test
    void 두_값에_1과_2가_모두_포함되는_경우() {
        Distances distances = new Distances(2, 1);
        assertThat(distances.isContainsOneAndTwo()).isTrue();
    }
    
    @Test
    void 두_값_모두_0이_아닌_경우() {
        assertThat(distances.isNotContainsZero()).isTrue();
    }
}