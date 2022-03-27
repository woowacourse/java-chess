package chess.view;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {

    private static final String CHESS_BOARD_EMPTY_PRINT_MESSAGE = ".";

    private OutputView() {
        throw new AssertionError();
    }

    public static void printGameGuide() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 프로모션 : 변경을 원하는 체스말(Q, B, R, N) - 예. R");
    }

    public static void printChessBoard(Map<Position, Piece> pieces) {
        for (char row = '8'; row >= '1'; row--) {
            printColumnPieces(pieces, row);
            System.out.println();
        }
    }

    private static void printColumnPieces(Map<Position, Piece> pieces, char row) {
        for (char column = 'a'; column <= 'h'; column++) {
            Position position = Position.of(column, row);
            System.out.print(piecePrintName(pieces, position));
        }
    }

    private static String piecePrintName(Map<Position, Piece> pieces, Position position) {
        if (pieces.containsKey(position)) {
            Piece piece = pieces.get(position);
            return piece.convertedName();
        }
        return CHESS_BOARD_EMPTY_PRINT_MESSAGE;
    }

    public static void printChessBoardStatus(Map<Color, Double> chessBoardStatus) {
        for (Color color : chessBoardStatus.keySet()) {
            printScoreStatus(color, chessBoardStatus.get(color));
        }
    }

    private static void printScoreStatus(Color color, Double score) {
        System.out.printf("%s의 점수는 %.1f입니다.%n", color, score);
    }

    public static void printPromotionGuide() {
        System.out.println("현재 폰이 프로모션 위치에 도착했습니다.");
        System.out.println("Q, R, B, N 중 하나를 입력하여 변경할 기물을 선택하세요.");
    }

    public static void printWinnerAndGameEnd(Color color) {
        System.out.printf("%s가 이겨 게임이 종료됩니다.", color);
    }
}
