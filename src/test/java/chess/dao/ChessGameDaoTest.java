package chess.dao;

import chess.domain.Team;
import chess.domain.state.turn.State;
import chess.domain.state.turn.WhiteTurn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    @BeforeEach
    public void before() {
        chessGameDao.removeAll();
    }

    @Test
    public void saveTest() {
        assertDoesNotThrow(() -> chessGameDao.saveState(new WhiteTurn()));
    }

    @Test
    public void saveAndLoadTest() {
        chessGameDao.saveState(new WhiteTurn());
        State state = chessGameDao.loadState();

        assertThat(state.getTeam()).isEqualTo(Team.WHITE);
    }
}
