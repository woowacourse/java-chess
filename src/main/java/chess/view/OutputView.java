package chess.view;

import chess.domain.ChessBoard;
import chess.domain.Result;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static final String RESULT_FORMAT = "%s: %.1f점 - %s%n";

    private OutputView() {
    }

    public static void gameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료: end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        for (List<Piece> rank : chessBoard.getChessBoard()) {
            for (Piece piece : rank) {
                System.out.print(piece.getName());
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printResult(Result result) {
        Map<Color, Double> score = result.getResult();
        Map<Color, String> winOrLose = result.getWinOrLose();
        System.out.printf(RESULT_FORMAT, Color.BLACK.name(), score.get(Color.BLACK),
            winOrLose.get(Color.BLACK));
        System.out.printf(RESULT_FORMAT, Color.WHITE.name(), score.get(Color.WHITE),
            winOrLose.get(Color.WHITE));
    }
}
