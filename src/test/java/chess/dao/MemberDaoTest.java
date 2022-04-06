package chess.dao;

import chess.domain.game.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberDaoTest {

    private final MemberDao dao = new MemberDao(new ChessConnectionManager());
    private final BoardDao boardDao = new BoardDao(new ChessConnectionManager());
    private int boardId;

    @BeforeEach
    void setup() {
        final Board board = boardDao.save(new Board("에덴파이팅~!"));
        this.boardId = board.getId();
    }

    @AfterEach
    void setDown() {
        boardDao.deleteAll();
    }

    @Test
    void save() {
        final Member member = dao.save("eden", boardId);
        assertThat(member.getName()).isEqualTo("eden");
    }
}
