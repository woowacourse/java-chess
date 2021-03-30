package chess.domain.dto.request;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

class MoveRequestTest {

    @Test
    void create() {
        final Gson gson = new Gson();
        final MoveRequest moveRequest = gson.fromJson("{'source': 'a1', 'target': 'a3'}",
            MoveRequest.class);
        assertThat(moveRequest.source()).isEqualTo("a1");
        assertThat(moveRequest.target()).isEqualTo("a3");
    }
}