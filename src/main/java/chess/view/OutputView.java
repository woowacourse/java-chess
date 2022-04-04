package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.board.TeamScore;
import chess.domain.piece.Piece;
import java.util.List;

public class OutputView {

    public static void printChessCommandInfo() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printChessBoard(Board board) {
        for (Rank rank : Rank.reverseValues()) {
            List<Piece> rankPieces = board.getPiecesByRank(rank);
            printEachRank(rankPieces);
        }
    }

    private static void printEachRank(List<Piece> rankPieces) {
        for (Piece rankPiece : rankPieces) {
            System.out.print(rankPiece.getNameIndex());
        }
        System.out.println();
    }

    public static void printScore(TeamScore score) {
        System.out.printf("%s팀 점수: %.2f\n", score.getTeam(), score.getScore());
    }
}
