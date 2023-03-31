package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.Game;
import chess.domain.piece.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameDaoTest {
    private final GameDao gameDao = new GameDao();

    @BeforeEach
    public void beforeEach(){
        Game game = new Game(new Board(new BoardFactory().generateBoard()),Team.WHITE);
        gameDao.save(game);
    }

    @AfterEach
    public void afterEach(){
        gameDao.delete();
    }

    @DisplayName("select Test")
    @Test
    void Should_Success_When_Select() {
        assertThat(gameDao.select().get().getTurn()).isEqualTo(Team.WHITE.name());
    }
}
