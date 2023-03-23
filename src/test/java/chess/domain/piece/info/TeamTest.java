package chess.domain.piece.info;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TeamTest {

    @ParameterizedTest
    @CsvSource(value = {"ONE:WHITE", "TWO:WHITE", "THREE:EMPTY", "SIX:EMPTY", "SEVEN:BLACK",
        "EIGHT:BLACK"}, delimiter = ':')
    void 초기_열_정보가_주어졌을_때_적절한_팀_정보가_반환된다(File file, Team expected) {
        //given

        //when
        Team actual = Team.initialOf(file);

        //then
        assertThat(actual).isEqualTo(expected);
    }

}