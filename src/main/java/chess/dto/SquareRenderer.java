package chess.dto;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;

public class SquareRenderer {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    public static String render(Square square) {
        File file = square.getFile();
        Rank rank = square.getRank();

        return FileRenderer.render(file) + RankRenderer.render(rank);
    }

    public static Square render(String input) {
        File file = FileRenderer.render(String.valueOf(input.charAt(FILE_INDEX)));
        Rank rank = RankRenderer.render(String.valueOf(input.charAt(RANK_INDEX)));

        return Square.getInstanceOf(file, rank);
    }
}
