package domain.chessboard;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardFactory {

    private static final List<Rank> chessBoard;

    private static final int NUMBER_OF_NONE_LINES = 4;

    static {
        chessBoard = new ArrayList<>();
        chessBoard.add(Rank.initRankToRank(InitRank.OTHERS_BLACK));
        chessBoard.add(Rank.initRankToRank(InitRank.PAWN_BLACK));
        for (int i = 0; i < NUMBER_OF_NONE_LINES; i++) {
            chessBoard.add(Rank.initRankToRank(InitRank.EMPTY));
        }
        chessBoard.add(Rank.initRankToRank(InitRank.PAWN_WHITE));
        chessBoard.add(Rank.initRankToRank(InitRank.OTHERS_WHITE));
    }

    public static ChessBoard generate() {
        return new ChessBoard(new ArrayList<>(chessBoard));
    }

}
