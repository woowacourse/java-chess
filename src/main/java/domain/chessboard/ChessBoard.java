package domain.chessboard;

import domain.coordinate.Position;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    private static final int NUMBER_OF_NONE_LINES = 4;

    private final List<Rank> chessBoard;

    public ChessBoard(final List<Rank> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard generate() {
        final List<Rank> chessBoard = new ArrayList<>();
        chessBoard.add(Rank.initRankToRank(RankFactory.OTHERS_BLACK));
        chessBoard.add(Rank.initRankToRank(RankFactory.PAWN_BLACK));
        for (int i = 0; i < NUMBER_OF_NONE_LINES; i++) {
            chessBoard.add(Rank.initRankToRank(RankFactory.EMPTY));
        }
        chessBoard.add(Rank.initRankToRank(RankFactory.PAWN_WHITE));
        chessBoard.add(Rank.initRankToRank(RankFactory.OTHERS_WHITE));

        return new ChessBoard(new ArrayList<>(chessBoard));
    }

    public List<Rank> getChessBoard() {
        return new ArrayList<>(chessBoard);
    }

    public Square findSquare(Position position) {
        return chessBoard.get(position.getY())
                .getRank()
                .get(position.getX());
    }

}
