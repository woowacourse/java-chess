package chess.domain.board;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class BoardTest {
    @Test
    void create() {
//        List<String> arrange = Arrays.asList("RNBKQBKR","PPPPPPPP","........","........","........","........","pppppppp","rnbkqbkr");
        List<String> arrange = Arrays.asList("PPPPPPPP", "PPPPPPPP", "PPPPPPPP", "PPPPPPPP", "PPPPPPPP", "PPPPPPPP", "PPPPPPPP", "PPPPPPPP");
        Board board = BoardFactory.create(arrange);

        System.out.println(board.get(Point.of(1, 1)));
    }


}
