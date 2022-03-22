package chess.view;

import chess.domain.Piece;
import chess.domain.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.\n"
            + "게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(final Map<Position, Piece> board) {
        final List<Position> pieces = new ArrayList<>(board.keySet());
        for (int i = 0; i < board.size(); i++) {
            final Piece piece = board.get(pieces.get(i));
            if (piece == null) {
                System.out.print(".");
            }
            if (piece != null) {
                System.out.print(piece.getName());
            }
            if (i % 8 == 7) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
