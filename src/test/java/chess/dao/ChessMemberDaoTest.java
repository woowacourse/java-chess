package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Member;
import chess.model.Board;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessMemberDaoTest {

    private final ChessMemberDao dao = new ChessMemberDao(new ConnectionManager());
    private final ChessBoardDao chessBoardDao = new ChessBoardDao(new ConnectionManager());
    private int boardId;

    @BeforeEach
    void setup() {
        final Board board = chessBoardDao.save(new Board("에덴파이팅~!"));
        this.boardId = board.getId();
    }

    @AfterEach
    void setDown() {
        chessBoardDao.deleteAll();
    }

    @Test
    void getAllByBoardId() {
        dao.save("eden", boardId);
        final List<Member> members = dao.getAllByBoardId(boardId);

        assertThat(members.get(0).getName()).isEqualTo("eden");
    }

    @Test
    void save() {
        final Member member = dao.save("eden", boardId);

        assertThat(member.getName()).isEqualTo("eden");
    }

    @Test
    void saveAll() {
        List<Member> members = List.of(new Member("eden"), new Member("corinne"));
        dao.saveAll(members, boardId);
        final List<Member> savedMembers = dao.getAllByBoardId(boardId);

        assertThat(savedMembers.size()).isEqualTo(2);
    }
}
