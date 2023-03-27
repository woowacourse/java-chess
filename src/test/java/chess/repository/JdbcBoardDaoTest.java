package chess.repository;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class JdbcBoardDaoTest {

    private final JdbcBoardDao jdbcBoardDao = new JdbcBoardDao(new TestConnector());

    @Test
    void 체스_말_삽입_테스트() {
        ChessGame chessGame = new ChessGame(BoardFactory.createBoard(), Team.WHITE);
        Assertions.assertDoesNotThrow(() -> jdbcBoardDao.saveChessGame(chessGame));
    }
}
