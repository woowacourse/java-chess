package chess.view;

import chess.domain.square.Square;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {

    public void printGameStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printBoard(final Map<Square, Piece> pieces) {
        StringBuilder builder = new StringBuilder();
        int cnt = 0;
        for (Piece value : pieces.values()) {

            builder.append(PieceView.findValue(value));
            cnt++;
            if (cnt % 8 == 0) {
                builder.append(System.lineSeparator());
            }
        }
        System.out.println(builder);
    }

}
