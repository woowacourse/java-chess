package chess.board;

import chess.board.File;
import chess.board.Rank;

public class Square {
    private final File file;
    private final Rank rank;

    public Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public String getName() {
        return file.getValue() + rank.getValue();
    }
}
