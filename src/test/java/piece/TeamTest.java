package piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamTest {
    @ParameterizedTest
    @CsvSource(value = {"WHITE,true","BLACK,false"})
    void 자신이_화이트팀이면_true를_반환하고_블랙팀이면_false를_반환한다(Team team, boolean isWhite) {
        boolean isWhiteTeam = team.isWhiteTeam();
        assertThat(isWhiteTeam).isEqualTo(isWhite);
    }
}
