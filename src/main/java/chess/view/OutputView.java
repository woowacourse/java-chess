package chess.view;

import chess.domain.ChessGame;
import chess.domain.Point;
import chess.domain.Result;
import chess.domain.piece.kind.Piece;

import java.util.Map;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

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
        Point point = Point.of(i, j);
        return pieces.get(point).getName();
    }

    public static void printScore(Result result) {
        System.out.println(WHITE + " 점수: " + result.getWhiteScore());
        System.out.println(BLACK + " 점수: " + result.getBlackScore());
        System.out.println("게임 결과: " + result.getWinner());
    }

    public static void noticeGameFinished() {
        System.out.println("게임이 끝났습니다.");
    }
}
