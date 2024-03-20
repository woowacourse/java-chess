package view;

import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.Map;

public class OutputView {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    public void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.printf("게임 시작은 %s, 종료는 %s 명령을 입력하세요.%n", START_COMMAND, END_COMMAND);
    }

    public void printBoard(Map<Position, Piece> board) {
        for (int rank = 8; rank >= 1; rank--) {
            printOneRank(board, rank);
        }
        System.out.println();
    }

    private void printOneRank(Map<Position, Piece> board, int rank) {
        for (int file = 1; file <= 8; file++) {
            Piece piece = board.get(new Position(new File(file), new Rank(rank)));
            System.out.print(pieceToString(piece));
        }
        System.out.println();
    }

    private String pieceToString(Piece piece) {
        // TODO: 메소드 라인 줄여보기
        if (piece == null) {
            return ".";
        }

        String pieceText = "";
        if (piece instanceof Bishop) {
            pieceText = "B";
        }
        if (piece instanceof King) {
            pieceText = "K";
        }
        if (piece instanceof Knight) {
            pieceText = "N";
        }
        if (piece instanceof Pawn) {
            pieceText = "P";
        }
        if (piece instanceof Queen) {
            pieceText = "Q";
        }
        if (piece instanceof Rook) {
            pieceText = "R";
        }

        if (piece.isWhite()) {
            return pieceText.toLowerCase();
        }
        return pieceText;
    }
}
