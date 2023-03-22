package chess.view;

import chess.domain.board.Position;
import chess.domain.pieces.Piece;
import chess.dto.BoardResultDto;
import java.util.Map;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다." + LINE_SEPARATOR
                + "> 게임 시작 : start" + LINE_SEPARATOR
                + "> 게임 종료 : end" + LINE_SEPARATOR
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(final BoardResultDto boardResultDto) {
        System.out.println();
        for (char row = '8'; row >= '1'; row--) {
            printLine(boardResultDto.getBoard(), row);
            System.out.println();
        }
    }

    private void printLine(final Map<Position, Piece> board, final char row) {
        for (char col = 'a'; col <= 'h'; col++) {
            String position = String.valueOf(col) + String.valueOf(row);
            System.out.print(board.get(Position.from(position)).getName());
        }
    }
}
