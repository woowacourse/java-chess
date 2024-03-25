package view;

import domain.coordinate.Coordinate;
import domain.piece.base.ChessPiece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.translator.PieceTranslator;

public class OutputView {

    private static final int ROW_SIZE = 8;

    public void printGameGuide() {
        System.out.print("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3
                """);
    }

    public void printBoard(Map<Coordinate, ChessPiece> board) {
        List<ChessPiece> boardValues = new ArrayList<>(board.values());

        for (int row = 0; row < board.size(); row += ROW_SIZE) {
            List<ChessPiece> oneRowPieces = getOneRowPieces(boardValues, row);

            for (ChessPiece piece : oneRowPieces) {
                System.out.print(PieceTranslator.getName(piece));
            }
            System.out.println();
        }
    }

    private List<ChessPiece> getOneRowPieces(List<ChessPiece> boardValues, int row) {
        return boardValues.subList(row, Math.min(row + ROW_SIZE, boardValues.size()));
    }
}
