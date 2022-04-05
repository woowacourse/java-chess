package chess.dao;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberDaoTest {

    private final NeoMemberDao dao = new NeoMemberDao(new RollbackConnectionManager());

    @Test
    void save() {
        final NeoMember member = dao.save("eden", 26);
        assertThat(member.getName()).isEqualTo("eden");
    }
}
