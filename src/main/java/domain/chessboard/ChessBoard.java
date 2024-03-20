package domain.chessboard;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    public static final int RANK_SIZE = 8;
    private final List<RankLine> ranksLine;

    public ChessBoard() {
        ranksLine = new ArrayList<>();
        for (int row = 0; row < RANK_SIZE; row++) {
            ranksLine.add(new RankLine(new RankGenerator(), row));
        }
    }

    public int size() {
        return ranksLine.size();
    }

    public List<RankLine> getRanksLine() {
        return ranksLine;
    }
}
