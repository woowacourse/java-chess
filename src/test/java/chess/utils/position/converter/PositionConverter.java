package chess.utils.position.converter;

import chess.domain.position.type.File;
import chess.domain.position.type.Rank;

public class PositionConverter {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int RANK_SIZE = 8;

    public static int convertToCellsStatusIndex(String position) {
        String rankValue = String.valueOf(position.charAt(RANK_INDEX));
        String fileValue = String.valueOf(position.charAt(FILE_INDEX));
        Rank rank = Rank.of(rankValue);
        File file = File.of(fileValue);
        int rankOrder = rank.getValue();
        int fileOrder = file.getOrder();
        return calculateIndex(rankOrder, fileOrder);
    }

    private static int calculateIndex(int rankOrder, int fileOrder) {
        int countRanksBeforePosition = RANK_SIZE - rankOrder;
        return (RANK_SIZE * countRanksBeforePosition) + fileOrder - 1;
    }
}
