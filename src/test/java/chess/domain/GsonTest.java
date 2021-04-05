package chess.domain;

import chess.controller.dto.PathResponseDto;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GsonTest {

    @Test
    void gsonTest() {
        List<String> foo = new ArrayList<>();
        foo.add("A");
        foo.add("B");
        foo.add("C");

        Path path = Path.of(Arrays.asList(
                Position.of("a2"),
                Position.of("a3"),
                Position.of("a4"),
                Position.of("a7"),
                Position.of("a8"),
                Position.of("b2"),
                Position.of("b3")

        ));

        PathResponseDto pathResponseDto = PathResponseDto.toPath(path);

        String json = new Gson().toJson(foo);
        System.out.println(json);

        String json2 = new Gson().toJson(path);
        System.out.println(json2);

        String json3 = new Gson().toJson(pathResponseDto);
        System.out.println(json3);
    }
}
