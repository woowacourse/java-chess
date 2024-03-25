package chess.view;

import chess.domain.Status;
import chess.dto.BoardStatusDto;

public class OutputView {
    public static void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    public static void printGameStatus(BoardStatusDto boardStatusDto) {
        printChessBoard(boardStatusDto);
        printStatus(boardStatusDto.status());
    }

    private static void printChessBoard(BoardStatusDto boardStatusDto) {
        for (int i = 8; i >= 1; i--) {
            for (int j = 1; j <= 8; j++) {
                System.out.print(boardStatusDto.getPieceValue(i, j));
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printStatus(Status status) {
        switch (status) {
            case CHECKMATE -> System.out.println("체크메이트!");
            case CHECK -> System.out.println("체크!");
        }
    }
}
