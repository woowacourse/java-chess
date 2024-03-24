package chess.view;

import chess.dto.BoardDto;
import chess.dto.RankDto;

public final class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final CharSequence RANK_DELIMITER = "";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printException(Exception e) {
        System.out.println(ERROR_PREFIX + e.getMessage() + NEWLINE);
    }

    public void printChessBoard(BoardDto boardDto) {
        boardDto.getRanks()
            .forEach(this::printRank);
    }

    private void printRank(RankDto rankDto) {
        String rank = String.join(RANK_DELIMITER, rankDto.getRank());
        System.out.println(rank);
    }
}
