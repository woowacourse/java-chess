package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Position;
import chess.domain.state.GameState;
import chess.domain.state.WhiteTurn;
import chess.view.OutputView;
import org.junit.jupiter.api.Test;

class GameDaoTest {

    @Test
    void save() {
        GameState gameState = new WhiteTurn(new Board(BoardInitializer.initBoard()));
        gameState = gameState.move(new Position("a2"), new Position("a4"));

        OutputView.printBoard(gameState);

        final GameDao gameDao = new GameDao();
        gameDao.save(gameState);
    }

    @Test
    void findState() {
        final GameDao gameDao = new GameDao();
        GameState gameState = gameDao.findState(new Board(BoardInitializer.initBoard()));

        assertThat(gameState.getState()).isEqualTo("흑팀 차례");
    }
}
