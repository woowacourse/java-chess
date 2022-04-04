package chess.dao;

import chess.domain.state.BoardInitialize;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardDaoTest {
    @Test
    void save() {
        BoardDao pieceDao = new BoardDao("a1", "p");
        pieceDao.save(new BoardDao("a2", "p"), "1");
    }

    @Test
    void findAll() {
        BoardDao pieceDao = new BoardDao("a1", "p");
        pieceDao.saveAll(BoardInitialize.create());
        List<BoardDao> pieces = pieceDao.findAll("1");
        assertThat(pieces.size()).isEqualTo(64);
    }

    @Test
    void findByPosition() {
        BoardDao pieceDao = new BoardDao("a1", "P");
        BoardDao piece = pieceDao.findByPosition("a1");
        assertThat(piece.getSymbol()).isEqualTo("P");
    }

    @Test
    void delete() {
        BoardDao pieceDao = new BoardDao("a1", "P");
        pieceDao.delete();
        List<BoardDao> pieces = pieceDao.findAll("1");
        assertThat(pieces.size()).isEqualTo(0);
    }
}
