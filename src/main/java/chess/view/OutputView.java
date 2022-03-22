package chess.view;

import chess.Board;
import chess.Position;
import chess.dto.PositionResponseDto;
import java.util.List;

public class OutputView {

    private static final int LINE_BREAK_UNIT = 8;

    public static void printStartGame() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printBoard(final PositionResponseDto positionResponseDto, Board board) {
        System.out.println(appendBoard(board, positionResponseDto.getPositions()));
    }

    private static String appendBoard(Board board, List<Position> positions) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < positions.size(); i++) {
            appendNewLine(stringBuilder, i);
            stringBuilder.append(board.getPiece(positions.get(i)));
        }
        return stringBuilder.toString();
    }

    private static void appendNewLine(StringBuilder stringBuilder, int i) {
        if (i % LINE_BREAK_UNIT == 0) {
            stringBuilder.append("\n");
        }
    }
}
