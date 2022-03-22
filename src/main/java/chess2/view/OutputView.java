package chess2.view;

import chess2.dto.BoardDto;
import chess2.dto.RowDto;
import java.util.stream.Collectors;

public class OutputView {

    private static final String BLANK_LINE = System.lineSeparator();

    public static void printBoard(BoardDto dto) {
        String boardDisplay = dto.getDisplay()
                .stream()
                .map(RowDto::getDisplay)
                .collect(Collectors.joining(BLANK_LINE));
        print(boardDisplay);
    }

    private static void print(String value) {
        System.out.println(value);
    }
}
