package chess.view;

import chess.domain.Point;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {

    public void printBoard(Map<Point, Piece> board) {
        StringBuilder builder = new StringBuilder();

        for (int rank = 8; rank > 0; rank--) {
            for (char file = 'a'; file <= 'h'; file++) {
                Piece piece = board.get(new Point(String.valueOf(file), rank));
                if (piece == null) {
                    builder.append(".");
                    continue;
                }
                builder.append(piece.getName());
            }
            builder.append(System.lineSeparator());
        }

        System.out.println(builder);
    }

    public void printGameStart() {
        System.out.printf("체스 게임을 시작합니다.%n게임 시작은 start, 종료는 end 명령을 입력하세요.%n");
    }
}
