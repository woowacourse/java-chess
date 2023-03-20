package chess.view;

import chess.dto.BoardDto;

import java.util.List;

public class OutputView {
    private static final String CHESS_GAME_START_GUIDE_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String GAME_START_GUIDE_MESSAGE = "> 게임 시작 : start";
    private static final String GAME_END_GUIDE_MESSAGE = "> 게임 종료 : end";
    private static final String MOVE_COMMAND_GUIDE_MESSAGE = "게임 이동 : move source위치 target 위치 - 예. move b2 b3";

    public static void printStartGuideMessage() {
        System.out.println(CHESS_GAME_START_GUIDE_MESSAGE);
        System.out.println(GAME_START_GUIDE_MESSAGE);
        System.out.println(GAME_END_GUIDE_MESSAGE);
        System.out.println(MOVE_COMMAND_GUIDE_MESSAGE);
    }

    public static void printBoard(BoardDto boardDto) {
        List<List<String>> board = boardDto.getDto();

        for (List<String> line : board) {
            line.stream()
                    .forEach(square -> System.out.print(square));
            System.out.println();
        }
    }

    public static void printExceptionMessage(Exception exception){
        System.out.println(exception.getMessage());
    }
}
