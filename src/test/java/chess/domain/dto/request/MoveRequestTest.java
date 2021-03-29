package chess.domain.dto.request;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

class MoveRequestTest {

    @Test
    void create() {
        final Gson gson = new Gson();
        final MoveRequest moveRequest = gson.fromJson("{'source': 'a1', 'target': 'a3'}",
            MoveRequest.class);
        System.out.println(moveRequest.source());
        System.out.println(moveRequest.target());
    }
}