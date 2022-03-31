package chess.view;

import chess.domain.Rank;
import chess.domain.Row;
import chess.domain.Team;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {
    public static void printChessBoard(Map<Row, Rank> board) {
        System.out.println();
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
        System.out.println("이긴 팀은 " + team.name());
    }

    public static void printStatus(double whiteTeamScore, double blackTeamScore) {
        System.out.printf("WHITE 팀 점수 : %.1f\n" +
                "BLACK 팀 점수 : %.1f\n", whiteTeamScore, blackTeamScore);
    }
}
