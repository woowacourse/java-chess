package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.Board;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    private final BoardDao dao = new BoardDao(new ConnectionManager());

    @AfterEach
    void setDown() {
        dao.deleteAll();
    }

    @Test
    void saveTest() {
        final Board board = dao.save(new Board("개초보만"));
        assertThat(board.getTitle()).isEqualTo("개초보만");
        dao.deleteById(board.getId());
    }

    @Test
    void deleteBoard() {
        final Board board = dao.save(new Board("삭제예정"));
        int affectedRow = dao.deleteById(board.getId());
        assertThat(affectedRow).isEqualTo(1);
    }

    @Test
    void getByIdTest() {
        final Board board = dao.save(new Board("개초보만"));
        final Board foundBoard = dao.getById(board.getId());
        assertThat(foundBoard.getTitle()).isEqualTo("개초보만");
    }

    @Test
    void findAllTest() {
        final Board board1 = dao.save(new Board("개초보만"));
        final Board board2 = dao.save(new Board("왕허접만"));
        final List<Board> boards = dao.findAll();
        assertThat(boards.size()).isEqualTo(2);
    }
}
