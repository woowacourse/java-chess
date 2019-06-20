package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class KnightTest {
    @Test
    void 생성() {
        assertDoesNotThrow(() -> new Knight(Team.WHITE));
    }

    @Test
    void 이동() {
        Knight knight = new Knight(Team.WHITE);
        assertThat(knight.getCandidatePoints(new Point("a1"), new Point("b3"))).isEqualTo(Arrays.asList(new Point("b3")));
    }

    @Test
    void 이동_불가() {
        Knight knight = new Knight(Team.WHITE);
        assertThat(knight.getCandidatePoints(new Point("a1"), new Point("b4"))).isEqualTo(new ArrayList<>());
    }
}
