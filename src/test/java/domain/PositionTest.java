package domain;

import chess.domain.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    @Test
    void Position이_잘_생성되었는지_확인() {
        List<String> positions = new ArrayList<>();
        List<String> rows = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
        List<String> columns = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");

        for (String column : columns) {
            for (String row : rows) {
                positions.add(column+row);
            }
        }

        assertTrue(positions.containsAll(Position.getPositions()));
    }

    @Test
    void 입력받은_Positon이_유효한_경우() {
        assertTrue(Position.isValidPostion("a4"));
    }

    @Test
    void 입력받은_Positon이_유효하지_않은_경우() {
        assertFalse(Position.isValidPostion("k1"));
    }
}
