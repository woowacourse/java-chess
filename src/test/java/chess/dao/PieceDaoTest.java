package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.Game;
import chess.domain.piece.Team;
import chess.dto.PiecesDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {
    private final PieceDao pieceDao = new PieceDao();
    private int gameId;

    @BeforeEach
    public void beforeEach() {
        Game game = new Game(new Board(new BoardFactory().generateBoard()), Team.WHITE);
        GameDao gameDao = new GameDao();
        gameDao.save(game);
        gameId = gameDao.select().get().getGameId();
        pieceDao.save(game, gameId);
    }

    @AfterEach
    public void afterEach() {
        pieceDao.delete();
    }

    @DisplayName("select Test")
    @Test
    void Should_Success_When_Select() {
        assertThat(pieceDao.select(gameId)).isInstanceOf(PiecesDto.class);
    }
}
