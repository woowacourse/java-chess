package chess.view;

import chess.controller.dto.BoardDto;
import chess.controller.dto.PieceDto;
import chess.controller.dto.PositionDto;

import java.util.Map;

import static chess.controller.CommandType.END;
import static chess.controller.CommandType.MOVE;
import static chess.controller.CommandType.START;

public final class OutputView {
    private static final int BOARD_SIZE = 8;
    private static final char BLANK = '.';
    private static final String START_MESSAGE = "> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : %s\n" +
            "> 게임 종료 : %s\n" +
            "> 게임 이동 : %s source위치 target위치 - 예. move b2 b3";

    public static void print(final String message) {
        System.out.println(message);
    }

    public static void printStartMessage() {
        print(String.format(START_MESSAGE, START.name().toLowerCase(),
                END.name().toLowerCase(), MOVE.name().toLowerCase()));
    }

    public static void printBoard(final BoardDto boardDto) {
        final StringBuilder boardMessage = new StringBuilder();
        for (int rank = BOARD_SIZE - 1; rank >= 0; rank--) {
            boardMessage.append(makeFileMessage(boardDto, rank)).append(System.lineSeparator());
        }
        print(boardMessage.toString());
    }

    private static String makeFileMessage(final BoardDto boardDto, final int rank) {
        final StringBuilder fileMessage = new StringBuilder();
        for (int file = 0; file < BOARD_SIZE; file++) {
            fileMessage.append(getPieceName(boardDto, rank, file));
        }
        return fileMessage.toString();
    }

    private static char getPieceName(final BoardDto boardDto, final int rank, final int file) {
        final PositionDto positionDto = PositionDto.from(rank, file);
        final Map<PositionDto, PieceDto> board = boardDto.getBoard();
        if (board.containsKey(positionDto)) {
            return PieceName.findMessage(board.get(positionDto));
        }
        return BLANK;
    }
}
