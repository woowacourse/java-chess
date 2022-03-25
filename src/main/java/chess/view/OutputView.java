package chess.view;

import chess.domain.Piece;
import chess.domain.Rank;
import chess.domain.Row;

import java.util.Map;

public class OutputView {
    public static void printChessBoard(Map<Row, Rank> board) {
        for (Rank rank : board.values()) {
            printRank(rank);
        }
    }

    private static void printRank(Rank rank) {
        for (Piece piece : rank.getPieces().values()) {
            System.out.print(PieceSymbolMapper.getSymbol(piece));
        }
        System.out.println();
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }
}
