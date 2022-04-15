package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.board.Board;
import chess.model.piece.Initializer;
import chess.model.status.Ready;
import chess.model.status.Running;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessBoardDaoTest {

    private final ChessBoardDao dao = new ChessBoardDao(new ConnectionManager());
    private Board board;

    @BeforeEach
    void setup() {
        board = dao.save(new Board(new Ready()));
    }

    @AfterEach
    void setDown() {
        dao.deleteAll();
    }

    @Test
    void saveTest() {

        assertThat(board.getStatus()).isInstanceOf(Ready.class);
    }

    @Test
    void deleteBoard() {
        int affectedRow = dao.deleteById(board.getId());

        assertThat(affectedRow).isEqualTo(1);
    }

    @Test
    void getByIdTest() {
        final Board foundBoard = dao.getById(board.getId());

        assertThat(foundBoard.getStatus()).isInstanceOf(Ready.class);
    }

    @Test
    void initBoard() {
        final Board edenFightingBoard = new Board(new Running());
        Board board = dao.init(edenFightingBoard, Initializer.initialize());

        assertThat(board.getStatus().name()).isEqualTo("running");
    }
}
