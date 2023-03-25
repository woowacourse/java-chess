package chess.view;

import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public final class OutputView {

    private static final int CHESS_BOARD_WIDTH = 8;
    private static final int LINE_BREAK_INDEX = 1;

    public static void printStartPrefix() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessState(final Map<Position, Piece> piecePoint) {
        StringBuilder chessBoardView = new StringBuilder();
        for (Position position : piecePoint.keySet()) {
            Piece piece = piecePoint.get(position);
            String viewSymbolBy = ViewPieceSymbol.getViewSymbolBy(piece.getPieceSymbol(), piece.isBlack());

            checkLineBreak(chessBoardView);
            chessBoardView.append(viewSymbolBy);
        }

        System.out.println(chessBoardView);
    }

    private static void checkLineBreak(final StringBuilder chessBoardView) {
        if (chessBoardView.length() % (CHESS_BOARD_WIDTH + LINE_BREAK_INDEX) == 0) {
            chessBoardView.append(System.lineSeparator());
        }
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public static void printStatusScore(final double whiteScore, final double blackScore) {
        System.out.println("흰색 점수: " + whiteScore);
        System.out.println("검정색 점수: " + blackScore);
    }

    public static void printReStart() {
        System.out.println("이전 게임을 불러옵니다.");
    }

    public static void printWinner(final Camp winnerCamp) {
        System.out.println("승자 :" + ViewCamp.getCampName(winnerCamp));
    }
}
