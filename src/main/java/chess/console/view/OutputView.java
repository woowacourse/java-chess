package chess.console.view;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String EMPTY_PIECE = ".";

    private OutputView() {
    }

    public static void printGuideMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printErrorMessage(String message) {
        System.out.printf("[ERROR] %s%n", message);
    }

    public static void printBoard(Map<Position, Piece> board) {
        for (Row row : Row.reverseRows()) {
            printColumnWithRow(board, row);
        }
    }

    private static void printColumnWithRow(Map<Position, Piece> board, Row row) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Column column : Column.values()) {
            Piece piece = board.get(Position.of(column, row));
            stringBuilder.append(drawPrintString(piece));
        }
        System.out.println(stringBuilder);
    }

    private static String drawPrintString(Piece piece) {
        if (piece != null) {
            return drawName(piece);
        }
        return EMPTY_PIECE;
    }

    private static String drawName(Piece piece) {
        if (piece.isSameTeam(Team.BLACK)) {
            return piece.getName().toUpperCase();
        }
        return piece.getName().toLowerCase();
    }

    public static void printStatus(Map<Team, Double> status, Team result) {
        for (Entry<Team, Double> value : status.entrySet()) {
            System.out.printf("%s는 %f점 입니다.%n", value.getKey(), value.getValue());
        }
        if (result == null) {
            System.out.println("무승부 입니다.");
            return;
        }
        System.out.printf("승리 팀은 %s 입니다.", result);
    }

    public static void printCheck() {
        System.out.println("현재 check 상황입니다.");
    }

    public static void printStartWarning() {
        System.out.println("체스 게임을 시작해야 합니다.");
    }
}