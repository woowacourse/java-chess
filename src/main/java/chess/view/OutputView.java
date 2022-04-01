package chess.view;

import chess.domain.Score;
import chess.domain.WinResult;
import chess.domain.piece.PieceColor;
import chess.dto.BoardDto;
import chess.dto.StatusDto;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printGameStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
    }

    public static void printPlayingCommandMessage() {
        System.out.println("> 게임 이동 : move source 위치 target 위치 (예. move b2 b3)");
        System.out.println("> 게임 현황 : status");
        System.out.println("> 게임 종료 : end");
    }

    public static void printBoard(BoardDto board) {
        Map<Integer, String> piecesByRow = board.getPiecesByRow();
        System.out.println("------------------------");
        for (int rank = board.getFirstRank(); rank <= board.getLastRank(); rank++) {
            System.out.println(piecesByRow.get(rank) + "\t(rank" + rank + ")");
        }
        System.out.println(board.getFileNames());
        System.out.println("------------------------");
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printStatus(StatusDto status) {
        Map<PieceColor, Score> scores = status.getScoresByColor();
        WinResult winResult = status.getWinResult();

        for (PieceColor color : scores.keySet()) {
            Score score = scores.get(color);
            System.out.println(color.name() + ": " + score.getValue() + "점");
        }
        if (winResult == WinResult.DRAW) {
            System.out.println("동점입니다.");
            return;
        }
        System.out.println(winResult + " 진영이 이기고 있습니다.");
        System.out.println();
    }
}
