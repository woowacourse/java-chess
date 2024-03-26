package chess.view;

import chess.domain.Position;
import chess.domain.Status;
import chess.domain.piece.abstractPiece.Piece;
import chess.dto.BoardStatusDto;
import java.util.Map;

public class OutputView {
    public static void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    public static void printGameStatus(BoardStatusDto boardStatusDto) {
        printChessBoard(boardStatusDto.board());
        printStatus(boardStatusDto.status());
    }

    private static void printChessBoard(Map<Position, Piece> board) {
        for (int i = 8; i >= 1; i--) {
            for (int j = 1; j <= 8; j++) {
                printPiece(board, i, j);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printPiece(Map<Position, Piece> board, int i, int j) {
        if (board.containsKey(Position.of(i, j))) {
            Piece piece = board.get(Position.of(i, j));
            System.out.print(CharacterViewer.convertToString(piece.team(), piece.kind()));
            return;
        }
        System.out.print(".");
    }

    private static void printStatus(Status status) {
        switch (status) {
            case CHECKMATE -> System.out.println("체크메이트!");
            case CHECK -> System.out.println("체크!");
        }
    }
}
