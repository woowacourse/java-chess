package chess.domain.distance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DistanceTest {
    private Distance distance;
    
    @BeforeEach
    void setUp() {
        distance = new Distance(2);
    }
    
    @Test
    void 절대값으로_변환하여_반환한다() {
        Distance distance = new Distance(-3);
        assertThat(distance.absoluteValue()).isEqualTo(3);
    }
    
    @Test
    void 값이_1보다_큰지_확인한다() {
        assertThat(distance.isGreaterThenOne()).isTrue();
    }
    
    @Test
    void 더한_값을_반환한다() {
        assertThat(distance.add(new Distance(5))).isEqualTo(new Distance(7));
    }
    
    @Test
    void 값이_같은지_확인한다() {
        assertThat(distance.isEqualTo(2)).isTrue();
    }
}