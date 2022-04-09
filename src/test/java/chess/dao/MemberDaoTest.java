package chess.dao;

import chess.domain.game.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberDaoTest {

    private final MemberDao<Member> dao = new ChessMemberDao(new ChessConnectionManager());
    private final ChessBoardDao BoardDao = new ChessBoardDao(new ChessConnectionManager());
    private int boardId;

    @BeforeEach
    void setup() {
        final Board board = BoardDao.save(new Board("에덴파이팅~!"));
        this.boardId = board.getId();
    }

    @AfterEach
    void setDown() {
        BoardDao.deleteAll();
    }

    @Test
    void save() {
        final Member member = dao.save("eden", boardId);
        assertThat(member.getName()).isEqualTo("eden");
    }
}
