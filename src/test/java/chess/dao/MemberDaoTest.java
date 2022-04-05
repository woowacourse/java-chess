package chess.dao;

import chess.domain.game.NeoBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberDaoTest {

    private final NeoMemberDao dao = new NeoMemberDao(new ChessConnectionManager());
    private final NeoBoardDao boardDao = new NeoBoardDao(new ChessConnectionManager());
    private int boardId;

    @BeforeEach
    void setup() {
        final NeoBoard neoBoard = boardDao.save(new NeoBoard("에덴파이팅~!"));
        this.boardId = neoBoard.getId();
    }

    @AfterEach
    void setDown() {
        boardDao.deleteAll();
    }

    @Test
    void save() {
        final NeoMember member = dao.save("eden", boardId);
        assertThat(member.getName()).isEqualTo("eden");
    }
}
