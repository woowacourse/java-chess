package chess.view;

import chess.domain.piece.Piece;
import chess.domain.Rank;
import chess.domain.Row;
import chess.domain.Team;

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

    public static void printFinishedGame(Map<Row, Rank> board, Team team) {
        printChessBoard(board);
        System.out.println("이긴 팀은 "+ team.name());
    }

    public static void printStatus(double teamScore, double score) {
        System.out.printf("WHITE팀 점수는%f\n" +
                "BLACK팀 점수는%f\n", teamScore, score);
    }
}
