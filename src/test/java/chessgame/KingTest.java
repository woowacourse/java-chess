package chessgame;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {

    @Test
    @DisplayName("블랙팀이면, 이름이 대문자로 바뀌어서 저장되는지 확인한다.")
    void Should_UpperCaseName_When_BlackTeam() {
        String name = "k";
        King king = King.from(Team.BLACK);
        assertThat(king.toString()).isEqualTo(name.toUpperCase());
    }

    @Test
    @DisplayName("화이트팀이면, 이름이 소문자로 바뀌어서 저장되는지 확인한다.")
    void Should_LowerCaseName_When_WhiteTeam() {
        String name = "K";
        King king = King.from(Team.WHITE);
        assertThat(king.toString()).isEqualTo(name.toLowerCase());
    }
}
