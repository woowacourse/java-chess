package chess.console.view;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {

    private static final char MIN_COLUMN = 'a';
    private static final char MAX_COLUMN = 'h';
    private static final char MIN_ROW = '1';
    private static final char MAX_ROW = '8';
    private static final String EMPTY_POSITION_OF_BOARD = ".";

    private OutputView() {
        throw new AssertionError();
    }

    public static void printGameGuide() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(Map<Position, Piece> pieces) {
        for (char row = MAX_ROW; row >= MIN_ROW; row--) {
            printChessBoardColumnsByRow(pieces, row);
            System.out.println();
        }
    }

    private static void printChessBoardColumnsByRow(Map<Position, Piece> pieces, char row) {
        for (char column = MIN_COLUMN; column <= MAX_COLUMN; column++) {
            Position position = Position.of(column, row);
            System.out.print(piecePrintName(pieces, position));
        }
    }

    private static String piecePrintName(Map<Position, Piece> pieces, Position position) {
        if (pieces.containsKey(position)) {
            Piece piece = pieces.get(position);
            return piece.convertedName();
        }
        return EMPTY_POSITION_OF_BOARD;
    }

    public static void printChessBoardStatus(Map<Color, Double> chessBoardStatus) {
        for (Color color : chessBoardStatus.keySet()) {
            printScoreStatus(color, chessBoardStatus.get(color));
        }
    }

    private static void printScoreStatus(Color color, Double score) {
        System.out.println(color + "의 점수는 " + score + "입니다.");
    }

    public static void printPromotionGuide() {
        System.out.println("폰이 프로모션에 도착했습니다. 프로모션을 진행해주세요.");
        System.out.println("Q, R, B, N 중 하나를 입력해주세요.");
    }
}
