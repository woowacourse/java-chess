package chess.dao;

import chess.domain.game.BoardInitializer;
import chess.domain.game.NeoBoard;
import chess.domain.pieces.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NeoBoardDaoTest {

    private final NeoBoardDao dao = new NeoBoardDao(new RollbackConnectionManager());

    @Test
    void saveTest() {
        final NeoBoard neoBoard = dao.save(new NeoBoard("개초보만"));
        assertThat(neoBoard.getRoomTitle()).isEqualTo("개초보만");
    }

    @Test
    void findByIdTest() {
        final NeoBoard neoBoard = dao.findById(1);
        assertAll(
                () -> assertThat(neoBoard.getRoomTitle()).isEqualTo("네오방"),
                () -> assertThat(neoBoard.getTurn()).isEqualTo(Color.WHITE)
        );
    }

    @Test
    void initBoard() {
        BoardInitializer boardInitializer = new BoardInitializer();
        dao.init(new NeoBoard("에덴파이팅"), boardInitializer.initialize());
    }

    @Test
    void deleteBoard() {
        final NeoBoard neoBoard = dao.save(new NeoBoard("aaa"));
        int affectedRow = dao.deleteById(neoBoard.getId());
        assertThat(affectedRow).isEqualTo(1);
    }
}
