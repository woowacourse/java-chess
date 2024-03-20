package view;

import dto.BoardDto;

public class OutputView {
    private OutputView() {
        throw new UnsupportedOperationException("생성할 수 없습니다.");
    }

    public static void printBoard(BoardDto boardDto) {
        for (int vertical = BoardDto.VERTICAL_START_INDEX; vertical >= 0; vertical--) {
            printHorizontalLine(boardDto, vertical);
        }
    }

    private static void printHorizontalLine(BoardDto boardDto, int vertical) {
        StringBuilder sb = new StringBuilder();
        for (int horizontal = 0; horizontal < BoardDto.HORIZONTAL_END_INDEX; horizontal++) {
            sb.append(boardDto.getWithVerticalAndHorizontal(vertical, horizontal));
        }
        System.out.println(sb);
    }
}
