package view;

import dto.ChessBoardDto;

public class OutputView {
    private OutputView() {
        throw new UnsupportedOperationException("생성할 수 없습니다.");
    }

    public static void printChessBoard(final ChessBoardDto chessBoardDto) {
        for (var vertical = ChessBoardDto.VERTICAL_START_INDEX; vertical >= 0; vertical--) {
            printHorizontalLine(chessBoardDto, vertical);
        }
    }

    private static void printHorizontalLine(final ChessBoardDto dto, int vertical) {
        final var sb = new StringBuilder();
        for (var horizontal = 0; horizontal < ChessBoardDto.HORIZONTAL_END_INDEX; horizontal++) {
            sb.append(dto.findByPointIndex(horizontal, vertical));
        }
        System.out.println(sb);
    }

    public static void printExceptionMessage(String message) {
        System.out.println("[ERROR] " + message);
    }
}
