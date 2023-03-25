package chess.domain.database;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.game.User;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao(Database.TEST);

    @BeforeEach
    void setUp() {
        Connection connection = chessGameDao.getConnection();
        assert connection != null;
        ChessTest.clearAll(connection);
        ChessTest.createMockUser(connection, "odo27", "mangmoong");
        ChessTest.createMockGame(connection, "100", "odo27");
    }

    @Test
    void connection() {
        assertThat(chessGameDao.getConnection()).isNotNull();
    }

    @Test
    void save() {
        ChessGame chessGame = new ChessGame();
        Assertions.assertThatCode(() -> chessGameDao.save("100", chessGame))
                .doesNotThrowAnyException();
    }

    @Test
    void get_game_by_id() {
        ChessGame chessGame = new ChessGame();
        chessGameDao.save("100", chessGame);
        ChessGame newChessGame = chessGameDao.getGameById("100", 1);
        assertThat(newChessGame.getBoard()).hasSize(32);
    }

    @Test
    void get_user_by_id_success() {
        Assertions.assertThatCode(() -> chessGameDao.getUserById("odo27"))
                .doesNotThrowAnyException();
    }

    @Test
    void get_user_by_id_has_id_and_nickname() {
        User user = chessGameDao.getUserById("odo27");
        assertThat(user.getId()).isEqualTo("odo27");
        assertThat(user.getNickname()).isEqualTo("mangmoong");
    }

    @Test
    void get_last_turn_by_id() {
        ChessGame chessGame = new ChessGame(Board.create(), new Turn(1));
        chessGameDao.save("100", chessGame);
        chessGame.move(Square.of(File.A, Rank.TWO), Square.of(File.A, Rank.FOUR));
        chessGameDao.save("100", chessGame);
        chessGame.move(Square.of(File.A, Rank.SEVEN), Square.of(File.A, Rank.FIVE));
        chessGameDao.save("100", chessGame);
        assertThat(chessGameDao.getLastTurnById("100")).isEqualTo(3);
    }

    @Test
    void get_last_game_id() {
        Connection connection = chessGameDao.getConnection();
        ChessTest.createMockGame(connection, "101", "odo27");
        ChessTest.createMockGame(connection, "102", "odo27");
        assertThat(chessGameDao.getLastGameId("odo27")).isEqualTo("102");
    }

    @Test
    void add_user() {
        User user = new User("abc", "hello");
        chessGameDao.addUser(user);
        User abc = chessGameDao.getUserById("abc");
        assertThat(abc.getId()).isEqualTo("abc");
        assertThat(abc.getNickname()).isEqualTo("hello");
    }

    @Test
    void get_user_by_id() {
        User user = chessGameDao.getUserById("abc");
        assertThat(user).isEqualTo(null);
    }
}
