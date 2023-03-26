package chess.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserDaoTest {

    @Test
    void Test() {
        boolean b = UserDao.makeUserOf("a", "b", "c");

        String userNameOf = UserDao.getUserNameOf("b", "c");
        Assertions.assertThat(userNameOf).isEqualTo("a");
    }
}
