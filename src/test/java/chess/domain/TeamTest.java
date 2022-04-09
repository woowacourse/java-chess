package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TeamTest {

    @Test
    void findWhiteTest() {
        assertAll(
                () -> assertThat(Team.find("white")).isEqualTo(Team.WHITE),
                () -> assertThat(Team.find("black")).isEqualTo(Team.BLACK),
                () -> assertThat(Team.find("neutrality")).isEqualTo(Team.NEUTRALITY)
        );
    }
}
