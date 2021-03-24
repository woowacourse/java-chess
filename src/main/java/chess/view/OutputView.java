package chess.view;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.game.Side;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Arrays;
import java.util.Locale;

public final class OutputView {

    private static final String LINE = "-------------------";
    private static final String BLACK_SCORE_IS = "Black: ";
    private static final String WHITE_SCORE_IS = "White: ";
    private static final String CURRENT_TURN_IS = "Current Turn: ";

    private OutputView() {
    }

    public static void startGame() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(ChessGame chessGame) {
        printLine();
        print(chessGame.board());
        printLine();
        print(chessGame.currentTurn());
    }

    private static void print(Board board) {
        // TODO 뎁스 리팩터링
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                Position position = new Position(column, row);
                System.out.print(board.getPieceInitialByPosition(position));
            }
            System.out.println("  " + row.getLineName());
        }

        printEmptyLine();
        Arrays.stream(Column.values())
                .map(column -> column.name().toLowerCase(Locale.ROOT))
                .forEach(System.out::print);
        printEmptyLine();
    }

    private static void printEmptyLine() {
        System.out.println();
    }

    private static void print(Side side) {
        if (side != Side.NONE) {
            System.out.println(CURRENT_TURN_IS + side.name());
        }
    }

    private static void printLine() {
        System.out.println(LINE);
    }

    public static void print(Score score) {
        System.out.println(BLACK_SCORE_IS + score.blackScore());
        System.out.println(WHITE_SCORE_IS + score.whiteScore());
    }

    public static void printWinner(Side winner) {
        if (winner != Side.NONE) {
            System.out.printf("Winner is %s!!!\n", winner.name());
        }
    }

    public static void printError(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
