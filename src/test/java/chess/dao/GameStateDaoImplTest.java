package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStateDaoImplTest {

    private final GameStateDaoImpl gameStateDaoImpl = GameStateDaoImpl.getInstance();

    @Test
    @DisplayName("턴 정보를 DB에 저장한다.")
    void saveTurn() {
        //given
        gameStateDaoImpl.saveTurn("WHITE");
        //when
        final String actual = gameStateDaoImpl.getTurn();
        //then
        assertThat(actual).isEqualTo("WHITE");
    }

    @Test
    @DisplayName("게임 상태를 DB에 저장한다.")
    void saveState() {
        gameStateDaoImpl.removeGameState();
        //given
        gameStateDaoImpl.saveState("playing");
        //when
        final String actual = gameStateDaoImpl.getGameState();
        //then
        assertThat(actual).isEqualTo("playing");
    }

    @AfterEach
    void removeAll() {
        gameStateDaoImpl.removeGameState();
    }

}