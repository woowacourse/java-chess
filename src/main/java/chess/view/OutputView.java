package chess.view;

import chess.domain.piece.Piece;
import chess.domain.point.Point;
import java.util.Map;

public class OutputView {


    private static final int CHESS_BOARD_WIDTH = 8;
    private static final int LINE_BREAK_INDEX = 1;

    public void printChessState(Map<Point, Piece> piecePoint) {
        StringBuilder chessBoardView = new StringBuilder();
        for (Point point : piecePoint.keySet()) {
            Piece piece = piecePoint.get(point);
            String viewSymbolBy = ViewPieceSymbol.getViewSymbolBy(piece.getPieceSymbolName(), piece.isBlack());

            checkLineBreak(chessBoardView);
            chessBoardView.append(viewSymbolBy);
        }

        System.out.println(chessBoardView);
    }

    private static void checkLineBreak(StringBuilder chessBoardView) {
        if (chessBoardView.length() % (CHESS_BOARD_WIDTH + LINE_BREAK_INDEX) ==  0){
            chessBoardView.append("\n");
        }
    }
}
