package console.view;

import chess.domain.board.LineNumber;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.request.BoardAndTurnInfo;
import chess.request.ScoreResponse;

import java.util.Map;

public class OutputView {

    public static void printIntroduction() {
        System.out.println("> 체스 게임을 시작합니다." + System.lineSeparator() +
                "> 게임 시작 : start" + System.lineSeparator() +
                "> 게임 종료 : end" + System.lineSeparator() +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3" + System.lineSeparator() +
                "> 중간 점수 : status");
    }

    public static void printException(RuntimeException e) {
        System.out.println(e.getMessage());
    }

    public static void printEnd() {
        System.out.println("게임이 종료되었습니다.");
    }

    public static void printBoardAndTurn(BoardAndTurnInfo response) {
        for (int i = LineNumber.MAX; i >= LineNumber.MIN; i--) {
            printEachLine(response.getBoard(), i);
        }
        System.out.println("현재 턴 :" + response.getTurnColor());
    }

    private static void printEachLine(Map<Point, Piece> board, int i) {
        for (int j = LineNumber.MIN; j <= LineNumber.MAX; j++) {
            Point point = Point.of(j, i);
            Piece piece = board.get(point);
            System.out.print(PieceRepresentation.convertType(piece));
        }
        System.out.println();
    }

    public static void printStatus(ScoreResponse response) {
        for (int i = LineNumber.MAX; i >= LineNumber.MIN; i--) {
            printEachLine(response.getBoard(), i);
        }
        System.out.println(Color.WHITE + " : " + String.format("%.1f", response.getWhiteScore()));
        System.out.println(Color.BLACK + " : " + String.format("%.1f", response.getBlackScore()));
        printPresentWinner(response.getWhiteScore(), response.getBlackScore());
    }

    private static void printPresentWinner(double whiteScore, double blackScore) {
        if (whiteScore > blackScore) {
            System.out.println("흰 진영이 이기고 있습니다.");
            return;
        }
        if (whiteScore < blackScore) {
            System.out.println("검정 진영이 이기고 있습니다.");
            return;
        }
        System.out.println("두 진영의 점수가 같습니다.");
    }

    public static void printEnterCommand() {
        System.out.print(System.lineSeparator() + "명령을 입력해주세요: ");
    }
}
