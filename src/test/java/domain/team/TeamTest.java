package domain.team;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TeamTest {
    @Test
    void caseInitial_White_Test() {
        assertThat(Team.WHITE.caseInitial("A")).isEqualTo("a");
    }

    @Test
    void caseInitial_Black_Test() {
        assertThat(Team.BLACK.caseInitial("a")).isEqualTo("A");
    }
}
