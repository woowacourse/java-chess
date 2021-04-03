package chess.dao;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.CommandAsString;
import chess.domain.game.Game;
import chess.domain.game.state.GameState;
import chess.domain.game.state.InitialState;
import chess.domain.result.Result;
import chess.dto.GameStateDto;
import chess.view.OutputView;
import java.sql.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameDaoTest {

    private GameDao gameDao;

    @BeforeEach
    void setup() {
        gameDao = new GameDao();
    }

    @Test
    void connection() {
        Connection con = gameDao.getConnection();
        assertNotNull(con);
    }

    @Test
    void addGame() throws Exception {
        Game game = new Game(new InitialState());
        GameState gameState = game.getState();
        gameDao.addGame("1", new GameStateDto(gameState));
    }

    @Test
    void updateGame() throws Exception {
        Game game = new Game(new InitialState());
        game = game.execute(new CommandAsString("start"));
        game = game.execute(new CommandAsString("move a2 a4"));
        GameState gameState = game.getState();
        gameDao.updateGame("1", new GameStateDto(gameState));
    }

    @Test
    void findGameByGameId() throws Exception {
        Game game = gameDao.findGameByGameId("1");
        CommandAsString command = new CommandAsString("move a7 a5");
        game = game.execute(command);
        Result result = game.result(command);
        OutputView.print(result.infoAsString());
    }

    @Test
    void deleteGame() throws Exception {
        gameDao.deleteGame("1");
    }
}