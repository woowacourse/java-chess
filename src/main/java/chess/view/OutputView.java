package chess.view;

import chess.dto.BoardDto;
import chess.dto.RowDto;
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
