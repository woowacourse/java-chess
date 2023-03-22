package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.List;

public class OutputView {
    private static final int BOARD_LINE_SIZE = 8;

    public void printGameStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(final List<Piece> pieces) {
        for (int i = 1; i < pieces.size() + 1; i++) {
            String pieceRole = PieceMessage.getMessage(pieces.get(i - 1).getRole());
            pieceRole = this.checkPieceCamp(pieces, i, pieceRole);
            System.out.print(pieceRole);
            this.printNewLine(i);
        }
    }

    private String checkPieceCamp(final List<Piece> pieces, final int i, String pieceRole) {
        if (pieces.get(i - 1).getTeam().equals(Team.WHITE)) {
            pieceRole = pieceRole.toLowerCase();
        }
        return pieceRole;
    }

    private void printNewLine(final int i) {
        if (i % BOARD_LINE_SIZE == 0) {
            System.out.println();
        }
    }

    public void printErrorMessage(final String message) {
        System.err.println(message);
    }
}
