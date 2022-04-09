package chess.dao;

import chess.domain.game.BoardInitializer;
import chess.domain.game.Board;
import chess.domain.pieces.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BoardDaoTest {

    private final BoardDao<Board> dao = new ChessBoardDao(new ChessConnectionManager());

    @AfterEach
    void setDown() {
        dao.deleteAll();
    }

    @Test
    void saveTest() {
        final Board board = dao.save(new Board("개초보만"));
        assertThat(board.getRoomTitle()).isEqualTo("개초보만");
        dao.deleteById(board.getId());
    }

    @Test
    void getByIdTest() {
        final Board board = dao.save(new Board("개초보만"));
        final Board foundBoard = dao.getById(board.getId());
        assertAll(
                () -> assertThat(foundBoard.getRoomTitle()).isEqualTo("개초보만"),
                () -> assertThat(foundBoard.getTurn()).isEqualTo(Color.WHITE)
        );
    }

    @Test
    void initBoard() {
        BoardInitializer boardInitializer = new BoardInitializer();
        final Board edenFightingBoard = new Board("에덴파이팅");
        dao.init(edenFightingBoard, boardInitializer.initialize());
    }

    @Test
    void deleteBoard() {
        final Board board = dao.save(new Board("aaa"));
        int affectedRow = dao.deleteById(board.getId());
        assertThat(affectedRow).isEqualTo(1);
    }

    @Test
    void findAllTest() {
        final Board board1 = dao.save(new Board("개초보만"));
        final Board board2 = dao.save(new Board("왕허접만"));
        assertThat(dao.findAll().size()).isEqualTo(2);
    }
}
