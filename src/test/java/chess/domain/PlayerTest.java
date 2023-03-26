package chess.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void should_플레이어를생성한다_when_이름과팀이정상적으로입력됐을때() {
        //given

        //when

        //then
        assertDoesNotThrow(() -> new Player(new Name("poi"), Team.WHITE));
    }

}