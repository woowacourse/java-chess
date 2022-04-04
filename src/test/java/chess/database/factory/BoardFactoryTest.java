package chess.database.factory;

import chess.dao.BoardDao;
import chess.domain.state.BoardInitialize;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardFactoryTest {
    @Test
    void save() {
        BoardFactory.save(new BoardDao("a2", "p"), "1");
    }

    @Test
    void saveAll() {
        BoardFactory.saveAll(BoardInitialize.create());
        List<BoardDao> pieces = BoardFactory.findAll("1");
        assertThat(pieces.size()).isEqualTo(64);
    }

    @Test
    void findAll() {
        BoardFactory.saveAll(BoardInitialize.create());
        List<BoardDao> pieces = BoardFactory.findAll("1");
        assertThat(pieces.size()).isEqualTo(64);
    }

    @Test
    void findByPosition() {
        BoardDao piece = BoardFactory.findByPosition("a1");
        assertThat(piece.getSymbol()).isEqualTo("P");
    }

    @Test
    void delete() {
        BoardFactory.delete();
        List<BoardDao> pieces = BoardFactory.findAll("1");
        assertThat(pieces.size()).isEqualTo(0);
    }
}
