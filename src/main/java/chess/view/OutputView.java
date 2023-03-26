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

    public void printChessBoard(List<Piece> pieces) {
        for (int i = 1; i < pieces.size() + 1; i++) {
            String pieceType = PieceMessage.getMessage(pieces.get(i - 1).getPieceType());
            pieceType = checkPieceCamp(pieces, i, pieceType);
            System.out.print(pieceType);
            printNewLine(i);
        }
    }

    private String checkPieceCamp(List<Piece> pieces, int i, String pieceType) {
        if (pieces.get(i - 1).getTeam().equals(Team.WHITE)) {
            pieceType = pieceType.toLowerCase();
        }
        return pieceType;
    }

    private void printNewLine(int i) {
        if (i % BOARD_LINE_SIZE == 0) {
            System.out.println();
        }
    }

    public void printGameEndMessage() {
        System.out.println("게임이 종료되었습니다.");
    }

    public void printScoreMessage(double whiteScore, double blackScore) {
        System.out.println("WHITE 팀 (" + whiteScore + ")점, BLACK 팀 (" + blackScore + ") 입니다.");
    }

    public void printWinnerMessage(Team winner) {
        if (winner == Team.EMPTY) {
            System.out.println("무승부입니다.");
        }
        System.out.println(winner.name() + " 팀이 승리하였습니다.");
    }

    public void printErrorMessage(String message) {
        System.err.println(message);
    }
}
