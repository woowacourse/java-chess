package chess.view;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static chess.view.PieceType.render;

import chess.domain.Team;
import chess.domain.pieces.Score;
import chess.domain.position.Position;
import chess.domain.pieces.Piece;
import java.util.Map;

public final class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작: start");
        System.out.println("> 게임 종료: end");
        System.out.println("> 점수 확인: status");
        System.out.println("> 게임 초기화: reset");
        System.out.println("> 게임 이동: move source위치 target위치 - 예) move b2 b3");
    }

    public static void printBoard(Map<Position, Piece> board) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int row = 0; row < 8; row++) {
            appendColumns(board, stringBuilder, row);
        }
        stringBuilder.append(System.lineSeparator());
        appendFiles(stringBuilder);

        System.out.println(stringBuilder);
    }

    private static void appendColumns(final Map<Position, Piece> board, final StringBuilder stringBuilder, final int row) {
        for (int column = 0; column < 8; column++) {
            Piece piece = board.get(Position.of(row, column));
            char renderResult = render(piece, piece.getTeam());
            stringBuilder.append(renderResult);
        }
        stringBuilder.append(" (").append(Math.abs(8 - row)).append(")").append(System.lineSeparator());
    }

    private static void appendFiles(final StringBuilder stringBuilder) {
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

    public static void printScores(final Map<Team, Score> scores) {
        System.out.println("=========");
        System.out.println(WHITE.name() + "팀의 점수는 " + scores.get(WHITE).getScore() + "점 입니다.");
        System.out.println(BLACK.name() + "팀의 점수는 " + scores.get(BLACK).getScore() + "점 입니다.");
        System.out.println("=========");
    }
}
