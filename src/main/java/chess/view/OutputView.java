package chess.view;

import chess.domain.CheckState;
import chess.domain.Position;
import chess.dto.BoardDto;

import java.util.Map;

public class OutputView {

    public static void printChessBoard(BoardDto boardDto) {
        for (int row = 8; row >= 1; row--) {
            printChessRow(boardDto, row);
        }
        System.out.println();
    }

    private static void printChessRow(BoardDto boardDto, int row) {
        for (int column = 1; column <= 8; column++) {
            System.out.print(boardDto.identifyPositionCharacter(row, column));
        }
        System.out.println();
    }

    public static void printCheck(CheckState checkState) {
        String checkMessage = switch(checkState) {
            case CHECK -> "체크 !\n";
            case CHECK_MATE -> "체크 메이트 !\n";
            case SAFE -> "";
        };

        System.out.print(checkMessage);
    }
}
