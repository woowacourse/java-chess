package chess.dao;

import chess.domain.game.BoardInitializer;
import chess.domain.game.NeoBoard;
import chess.domain.pieces.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NeoBoardDaoTest {

    private final NeoBoardDao dao = new NeoBoardDao(new ChessConnectionManager());

    @AfterEach
    void setDown() {
        dao.deleteAll();
    }

    @Test
    void saveTest() {
        final NeoBoard neoBoard = dao.save(new NeoBoard("개초보만"));
        assertThat(neoBoard.getRoomTitle()).isEqualTo("개초보만");
        dao.deleteById(neoBoard.getId());
    }

    @Test
    void findByIdTest() {
        final NeoBoard neoBoard = dao.save(new NeoBoard("개초보만"));
        final NeoBoard foundBoard = dao.findById(neoBoard.getId());
        assertAll(
                () -> assertThat(foundBoard.getRoomTitle()).isEqualTo("개초보만"),
                () -> assertThat(foundBoard.getTurn()).isEqualTo(Color.WHITE)
        );
    }

    @Test
    void initBoard() {
        BoardInitializer boardInitializer = new BoardInitializer();
        final NeoBoard edenFightingBoard = new NeoBoard("에덴파이팅");
        dao.init(edenFightingBoard, boardInitializer.initialize());
    }

    @Test
    void deleteBoard() {
        final NeoBoard neoBoard = dao.save(new NeoBoard("aaa"));
        int affectedRow = dao.deleteById(neoBoard.getId());
        assertThat(affectedRow).isEqualTo(1);
    }
}
