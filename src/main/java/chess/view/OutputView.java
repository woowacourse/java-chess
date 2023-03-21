package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public final class OutputView {

    private static final int CHESS_BOARD_WIDTH = 8;
    private static final int LINE_BREAK_INDEX = 1;

    ViewChessBoardPosition viewChessBoardPosition;

    public OutputView(ViewChessBoardPosition viewChessBoardPosition) {
        this.viewChessBoardPosition = viewChessBoardPosition;
    }

    public void printStartPrefix() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessState(Map<Position, Piece> piecePoint) {
        StringBuilder chessBoardView = new StringBuilder();
        for (Position position : viewChessBoardPosition.getPositions()) {
            Piece piece = piecePoint.get(position);
            String viewSymbolBy = getViewSymbol(piecePoint, position, piece);

            checkLineBreak(chessBoardView);
            chessBoardView.append(viewSymbolBy);
        }

        System.out.println(chessBoardView);
    }

    private String getViewSymbol(Map<Position, Piece> piecePoint, Position position,Piece piece) {
        if (!piecePoint.containsKey(position)) {
            return ".";
        }
        return ViewPieceSymbol.getViewSymbolBy(piece.getPieceSymbol(), piece.isBlack());
    }

    private static void checkLineBreak(StringBuilder chessBoardView) {
        if (chessBoardView.length() % (CHESS_BOARD_WIDTH + LINE_BREAK_INDEX) == 0) {
            chessBoardView.append(System.lineSeparator());
        }
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
