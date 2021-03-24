package chess.view;

import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.Score;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.kind.Piece;

public class OutputView {
    public static final String START = "start";
    public static final String END = "end";
    private static final String START_GUIDE_MESSAGE = "체스 게임을 시작합니다.";
    private static final String START_INPUT_GUIDE_MESSAGE = "게임 시작은 " + START + ", 종료는 " + END + " 명령을 입력하세요.";

    private OutputView() {
    }

    public static void printStartGuideMessage() {
        System.out.println(START_GUIDE_MESSAGE);
        System.out.println(START_INPUT_GUIDE_MESSAGE);
    }

    public static void printBoard(ChessGame chessGame) {
        Map<Point, Piece> pieces = chessGame.getBoard();
        for (int i = 0; i < 8; i++) {
            StringBuilder oneLine = new StringBuilder();
            makeOneLine(pieces, i, oneLine);
            System.out.println(oneLine.toString());
        }
        System.out.println();
    }

    private static void makeOneLine(Map<Point, Piece> pieces, int i, StringBuilder oneLine) {
        for (int j = 0; j < 8; j++) {
            oneLine.append(makeOneCharacter(pieces, i, j));
        }
    }

    private static String makeOneCharacter(Map<Point, Piece> pieces, int i, int j) {
        Point point = Point.valueOf(i, j);
        return pieces.get(point).getName();
    }

    public static void printScore(Color color, Score score) {
        System.out.println(color.name() + score.getScore());
    }

    public static void noticeGameFinished() {
        System.out.println("게임이 끝났습니다. 새로운 게임을 시작하시려면 start, 종료를 원하시면 end를 입력하세요.");
    }

    public static void printError(String message) {
        System.out.println(message + " 다시 입력해주세요.");
    }

    public static void printWinner(Color biggerColor) {
        if (biggerColor.equals(Color.NOTHING)) {
            System.out.println("무승부입니다.");
            return;
        }
        System.out.println(biggerColor.getName() + "이 승리했습니다.");
    }
}
