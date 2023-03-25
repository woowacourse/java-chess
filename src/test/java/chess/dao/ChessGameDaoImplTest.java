package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.dao.dto.ChessGameDto;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.state.GameState;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessGameDaoImplTest {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @AfterEach
    void clear() {
        jdbcTemplate.executeUpdate("delete from chess_game", Collections.emptyList());
    }

    @Test
    void 체스_게임을_저장한다() {
        final ChessGame chessGame = new ChessGame(BoardFactory.create(), GameState.INIT);
        final ChessGameDao chessGameDao = new ChessGameDaoImpl();

        chessGameDao.save(chessGame);

        final List<ChessGameDto> chessGames = chessGameDao.findAll();
        assertThat(chessGames).hasSize(1);
    }

    @Test
    void 체스_게임을_조회한다() {
        final ChessGame chessGame = new ChessGame(BoardFactory.create(), GameState.INIT);
        final ChessGameDao chessGameDao = new ChessGameDaoImpl();
        chessGameDao.save(chessGame);
        final Optional<ChessGameDto> chessGameDto = chessGameDao.findLatest();

        final Optional<ChessGameDto> findChessGame = chessGameDao.findById(chessGameDto.get().getId());

        assertThat(findChessGame.get().getTurn()).isEqualTo("WHITE");
    }

    @Test
    void 체스_게임_목록을_확인한다() {
        final ChessGame chessGame = new ChessGame(BoardFactory.create(), GameState.INIT);
        final ChessGameDao chessGameDao = new ChessGameDaoImpl();
        chessGameDao.save(chessGame);

        final List<ChessGameDto> chessGames = chessGameDao.findAll();

        assertAll(
                () -> assertThat(chessGames).hasSize(1),
                () -> assertThat(chessGames.get(0).getTurn()).isEqualTo("WHITE")
        );
    }
}
