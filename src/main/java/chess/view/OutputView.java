package chess.view;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;

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
        Piece[][] pieces = chessGame.getBoard();
        for (int i = 0; i < 8; i++) {
            StringBuilder oneLine = new StringBuilder();
            makeOneLine(pieces, i, oneLine);
            System.out.println(oneLine.toString());
        }
        System.out.println();
    }

    private static void makeOneLine(Piece[][] pieces, int i, StringBuilder oneLine) {
        for (int j = 0; j < 8; j++) {
            oneLine.append(makeOneCharacter(pieces, i, j));
        }
    }

    private static String makeOneCharacter(Piece[][] pieces, int i, int j) {
        if (pieces[i][j] != null) {
            return pieces[i][j].getName();
        }
        return ".";
    }
}
