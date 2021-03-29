package chess.view;

import chess.domain.Result;
import chess.domain.piece.Piece;
import chess.domain.position.AlphaColumns;
import chess.domain.position.NumberRows;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printBoard(Map<Position, Piece> chessBoard) {
        List<NumberRows> reversedNumberRows = Arrays.stream(NumberRows.values())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        for (NumberRows number : reversedNumberRows) {
            for (AlphaColumns alpha : AlphaColumns.values()) {
                System.out.print(chessBoard.get(Position.valueOf(alpha, number)).getPieceName());
            }
            System.out.println();
        }
    }

    public static void printInitMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printScore(Result result) {
        System.out.println("WHITE POINT: " + result.whiteScore());
        System.out.println("BLACK POINT: " + result.blackScore());
        System.out.println("승자: " + result.getWinner());
    }
}
