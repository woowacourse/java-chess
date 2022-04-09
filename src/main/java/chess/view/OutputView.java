package chess.view;

import static chess.domain.piece.Team.BLACK;

import chess.domain.board.Result;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private OutputView() {
    }

    public static void printGuideMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printErrorMessage(String message) {
        System.out.printf("[ERROR] %s %n", message);
    }

    public static void printBoard(Map<Position, Piece> board) {
        for (Row row : Row.reverseRows()) {
            printColumnWithRow(board, row);
            System.out.println();
        }
    }

    private static void printColumnWithRow(Map<Position, Piece> board, Row row) {
        for (Column column : Column.values()) {
            Position position = new Position(column, row);
            Piece piece = board.get(position);
            printStringOrDefault(piece);
        }
    }

    private static void printStringOrDefault(Piece piece) {
        if (piece != null) {
            System.out.print(changeCaseSensitive(piece));
            return;
        }
        System.out.print(".");
    }

    private static String changeCaseSensitive(Piece piece) {
        String name = piece.getName().substring(0, 1);
        if (piece.isSameTeam(BLACK)) {
            return name.toUpperCase(Locale.ROOT);
        }
        return name;
    }

    public static void printStatus(Result result) {
        Map<Team, Double> status = result.getValue();
        List<Team> winnerResult = result.getWinnerResult();

        for (Entry<Team, Double> value : status.entrySet()) {
            System.out.printf(value.getKey() + " : " + value.getValue() + "점%n");
        }
        if (winnerResult.size() == 2) {
            System.out.println("무승부 입니다!");
            return;
        }
        System.out.println("승리 팀은 : " + winnerResult.get(0) + " 입니다.");
    }

    public static void printMessage(String string) {
        System.out.println(string);
    }
}