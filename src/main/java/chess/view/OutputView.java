package chess.view;

import chess.domain.Square;
import chess.domain.Squares;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {
    public void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(Map<Square, Piece> pieces) {
        int count = 0;
        for (Square square : Squares.getSquares()) {
            if (count % 8 == 0) {
                System.out.println();
            }
            count++;
            if (pieces.containsKey(square)) {
                Piece piece = pieces.get(square);

                String name = piece.getPieceTypeName();
                if (piece.getColor() == Color.BLACK) {
                    name = name.toUpperCase();
                }
                System.out.print(name);
                continue;
            }
            System.out.print(".");
        }
        System.out.println("\n");
    }
}
