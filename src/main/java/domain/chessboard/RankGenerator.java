package domain.chessboard;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.ArrayList;
import java.util.List;

public class RankGenerator {
    public List<Square> generate(int row) {
        List<Square> result = new ArrayList<>();
        for (char column = 'a'; column <= 'h'; column++) {
            result.add(new Square(new Position(new File(column), new Rank(row))));
        }
        return result;
    }
}
