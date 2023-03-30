package dao;

import domain.game.ChessBoardGenerator;
import domain.game.File;
import domain.game.Game;
import domain.game.Position;
import domain.game.Rank;
import domain.game.Side;
import org.junit.jupiter.api.Test;

class GameDaoTest {
    private final GameDao gameDao = new GameDao();

    @Test
    public void create() {
        Game game = new Game(new ChessBoardGenerator().generate(), "user", "title", Side.WHITE);
        gameDao.create(game);
    }

    @Test
    public void saveChessBoard() {
        Game game = new Game(new ChessBoardGenerator().generate(), "user", "title", Side.WHITE);
        game.move(Position.of(File.E, Rank.TWO), Position.of(File.E, Rank.FOUR));
        gameDao.saveChessBoard(game, 15);
    }

    @Test
    public void deleteById() {
        for (int i = 1; i < 65; i++) {
            gameDao.deleteById(i);
        }
    }
}
