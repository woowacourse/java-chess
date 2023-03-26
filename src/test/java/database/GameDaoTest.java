package database;

import chess.cache.PieceCache;
import chess.dao.GameDao;
import chess.dao.ConnectionGenerator;
import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.GameDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GameDaoTest {
    @Test
    void create() {
        GameDao gameDao = new GameDao(ConnectionGenerator.getConnection());
        Board board = Board.from(PieceCache.create());
        gameDao.createGame(board.getBoard());
    }

    @Test
    @DisplayName("게임보드가 정상적으로 64개 생성되었는지 테스트")
    void findByLastGameBoard() {
        GameDao gameDao = new GameDao(ConnectionGenerator.getConnection());
        GameDto gameDto = gameDao.findByLastGame();
        Map<Position, Piece> board = gameDao.findByLastGameBoard(gameDto.getId());
        Assertions.assertThat(board.size()).isEqualTo(64);
    }
}
