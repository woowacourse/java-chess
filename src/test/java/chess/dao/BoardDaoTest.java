package chess.dao;

import chess.domain.Team;
import chess.domain.state.turn.State;
import chess.domain.state.turn.WhiteTurn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BoardDaoTest {

    @BeforeEach
    public void before() {
        BoardDao boardDao = new BoardDao();
        boardDao.removeAll();
    }

    @Test
    public void saveTest() {
        BoardDao boardDao = new BoardDao();
        assertDoesNotThrow(() -> boardDao.saveState(new WhiteTurn()));
    }

    @Test
    public void saveAndLoadTest() {
        BoardDao boardDao = new BoardDao();
        boardDao.saveState(new WhiteTurn());
        State state = boardDao.loadState();
        assertThat(state.getTeam()).isEqualTo(Team.WHITE);
    }
}
