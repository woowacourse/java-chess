package chess.view;

import chess.domain.Camp;
import chess.domain.Piece;
import java.util.List;

public class OutputView {
    public void printGameStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public void printChessBoard(final List<Piece> pieces) {
        for (int i = 1; i < pieces.size() + 1; i++) {
            String pieceRole = pieces.get(i - 1).getRole();
            if (pieces.get(i - 1).getCamp().equals(Camp.WHITE)) {
                pieceRole = pieceRole.toLowerCase();
            }
            System.out.print(pieceRole);
            if (i % 8 == 0) {
                System.out.println();
            }
        }
    }
}
