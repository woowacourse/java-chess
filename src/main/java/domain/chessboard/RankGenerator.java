package domain.chessboard;

import domain.piece.Position;
import java.util.ArrayList;
import java.util.List;

public class RankGenerator {
    public List<Square> generate(int row) {
        List<Square> result = new ArrayList<>();
        for (int column = 0; column < 8; column++) {
            result.add(new Square(new Position(column, row)));
        }
        return result;
    }
}
