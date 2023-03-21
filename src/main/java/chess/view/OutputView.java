package chess.view;

import static chess.view.PieceRender.render;
import static java.util.stream.Collectors.toList;

import chess.domain.board.Rank;
import chess.domain.board.Square;
import java.util.List;

public final class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(List<Rank> board) {
        List<List<Square>> squares = board.stream()
                .map(Rank::getRank)
                .collect(toList());

        System.out.println(makeBoard(squares));
    }

    private static StringBuilder makeBoard(final List<List<Square>> squares) {
        StringBuilder stringBuilder = new StringBuilder();

        appendRanks(squares, stringBuilder);
        appendFiles(stringBuilder);

        return stringBuilder;
    }

    private static void appendRanks(final List<List<Square>> squares, final StringBuilder stringBuilder) {
        for (int row = 0; row < squares.size(); row++) {
            for (int col = 0; col < squares.get(0).size(); col++) {
                Square square = squares.get(row).get(col);

                stringBuilder.append(render(square.getPiece()));
            }
            stringBuilder.append(" (" + Math.abs(8 - row) + ")" + System.lineSeparator());
        }
    }

    private static void appendFiles(final StringBuilder stringBuilder) {
        stringBuilder.append(System.lineSeparator());
        for (char file = 'a'; file <= 'h'; file++) {
            stringBuilder.append(file);
        }
    }

    public static void printFinishMessage() {
        System.out.println(System.lineSeparator() + "체스 게임을 종료합니다.");
    }

    public static void printError(final String errorMessage) {
        System.out.println(ERROR_PREFIX + errorMessage);
    }

    public static void printCommandLine() {
        System.out.print("> ");
    }
}
