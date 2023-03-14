package view;

import domain.Board;
import domain.Rank;
import domain.Square;

public class OutputView {

    public void printBoard(Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rank rank : board.getRanks()) {
            stringBuilder.insert(0, makeRank(rank));
        }
        System.out.println(stringBuilder);
    }

    private StringBuilder makeRank(Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Square square : rank.getSquare()) {
            stringBuilder.append(PieceTypeMapper.getTarget(square.getPiece()));
        }
        stringBuilder.append(System.lineSeparator());
        return stringBuilder;
    }
}
