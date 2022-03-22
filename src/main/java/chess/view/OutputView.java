package chess.view;

import chess.dto.BoardDto;
import chess.dto.RowDto;
import java.util.stream.Collectors;

public class OutputView {

    private static final String BLANK_LINE = System.lineSeparator();
    private static final String GAME_START_ANNOUNCEMENT_MESSAGE = "체스 게임을 시작합니다." + BLANK_LINE;
    private static final String START_OR_END_INPUT_INSTRUCTION_MESSAGE = "게임 시작은 start, 종료는 end 명령을 입력하세요.";

    public static void printGameStartAnnouncement() {
        print(GAME_START_ANNOUNCEMENT_MESSAGE + START_OR_END_INPUT_INSTRUCTION_MESSAGE);
    }

    public static void printBoard(BoardDto dto) {
        final String boardDisplay = dto.getDisplay()
                .stream()
                .map(RowDto::getDisplay)
                .collect(Collectors.joining(BLANK_LINE));
        print(boardDisplay + BLANK_LINE);
    }

    private static void print(String value) {
        System.out.println(value);
    }
}
