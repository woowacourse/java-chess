package chess.practice;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class PracticeTest {
    @Test
    void mapKeyTest() {
        Position position = Position.of(File.A, Rank.ONE);
        System.out.println(position.hashCode());
        Object[] objects = {Rank.ONE, File.A};
        System.out.println(Arrays.hashCode(objects));
    }
}
