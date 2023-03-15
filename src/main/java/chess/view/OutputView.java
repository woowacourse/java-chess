package chess.view;

import chess.domain.Square;
import chess.domain.Squares;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {
    public void printGameStart() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printGameCommand() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public void printChessBoard(Map<Square, Piece> pieces) {
        int count = 0;
        for (Square square : Squares.getSquares()) {
            if (count % 8 == 0) {
                System.out.println();
            }

            count++;
            if (pieces.containsKey(square)) {
                System.out.print(pieces.get(square).getPieceTypeName());
                continue;
            }
            System.out.print(".");
        }
    }
}
