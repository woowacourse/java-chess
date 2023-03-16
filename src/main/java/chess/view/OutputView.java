package chess.view;

import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import java.util.List;

public class OutputView {
    public void printGameStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(final List<Piece> pieces) {
        for (int i = 1; i < pieces.size() + 1; i++) {
            String pieceRole = pieces.get(i - 1).role().getInitial();
            if (pieces.get(i - 1).camp().equals(Camp.WHITE)) {
                pieceRole = pieceRole.toLowerCase();
            }
            System.out.print(pieceRole);
            if (i % 8 == 0) {
                System.out.println();
            }
        }
    }
}
