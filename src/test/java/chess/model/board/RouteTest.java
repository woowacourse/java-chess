package chess.model.board;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

public class RouteTest {
    @Test
    void 생성자_확인() {
        Route route = new Route(Arrays.asList("11", "12"));
        assertThat(route).isEqualTo(new Route(Arrays.asList("11", "12")));
    }

    @Test
    void 생성자_오류_확인_null이_입력될_경우() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Route(null));
    }

    @Test
    void 생성자_오류_확인_빈_경로가_입력될_경우() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Route(Collections.emptyList()));
    }

    @Test
    void 이동경로에_특정위치가_포함하는지_확인() {
        Route route = new Route(Arrays.asList("11", "12"));
        assertThat(route.contains("11")).isTrue();
    }

    @Test
    void 이동경로에_특정위치가_포함되지_않는지_확인() {
        Route route = new Route(Arrays.asList("11", "12"));
        assertThat(route.contains("21")).isFalse();
    }
}
