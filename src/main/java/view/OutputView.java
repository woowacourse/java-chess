package view;

import dto.BoardDto;

public class OutputView {
    private OutputView() {
        throw new UnsupportedOperationException("생성할 수 없습니다.");
    }

    public static void printBoard(final BoardDto boardDto) {
        for (int vertical = BoardDto.VERTICAL_START_INDEX; vertical >= 0; vertical--) {
            printHorizontalLine(boardDto, vertical);
        }
    }

    public static void printException(final String exceptionMessage) {
        System.out.println(exceptionMessage);
    }


    private static void printHorizontalLine(final BoardDto boardDto, final int vertical) {
        final StringBuilder sb = new StringBuilder();
        for (int horizontal = 0; horizontal < BoardDto.HORIZONTAL_END_INDEX; horizontal++) {
            sb.append(boardDto.getWithVerticalAndHorizontal(vertical, horizontal));
        }
        System.out.println(sb);
    }
}
