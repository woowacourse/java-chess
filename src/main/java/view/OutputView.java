package view;

import domain.chessboard.ChessBoard;
import domain.position.Rank;
import domain.position.File;
import domain.position.Position;
import java.util.Arrays;
import java.util.Collections;

public class OutputView {

    public static void printBoard(ChessBoard chessBoard) {
        Rank[] ranks = Rank.values();
        Arrays.sort(ranks, Collections.reverseOrder());
        for (Rank rank : ranks) {
            for (File file : File.values()) {
                System.out.print(chessBoard.getSymbol(Position.of(file, rank)));
            }
            System.out.println();
        }
        System.out.println();
    }
}
