package chess.dto;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;

public class SquareRenderer {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 0;

    public static Square render(String input) {
        File file = FileRenderer.renderToFile(String.valueOf(input.charAt(FILE_INDEX)));
        Rank rank = RankRenderer.renderToRank(String.valueOf(input.charAt(RANK_INDEX)));

        return Square.getInstanceOf(file, rank);
    }
}
