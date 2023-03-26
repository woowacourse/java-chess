package chess.dao.connection;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@SuppressWarnings({"NonAsciiCharacters","SpellCheckingInspection"})
class ConnectionDriverTest {

    @Test
    void getConnection실행하면_반환값이_널이_아니다() {
        assertThat(new ConnectionDriver().getConnection())
                .isNotNull();
    }
}