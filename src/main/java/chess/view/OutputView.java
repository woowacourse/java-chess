package chess.view;

import chess.domain.dto.ResponseDto;
import java.util.Arrays;

public class OutputView {

    private OutputView() {}

    public static void printExceptionMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    public static void printGameStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printResult(ResponseDto responseDto) {
        char[][] board = responseDto.getBoard();
        Arrays.stream(board)
            .forEach(System.out::println);
        double whiteScore = responseDto.getWhiteScore();
        double blackScore = responseDto.getBlackScore();
        if (whiteScore != -1 && blackScore != -1) {
            System.out.printf("검은색: %.1f점. 흰색: %.1f점\n", whiteScore, blackScore);
        }
        System.out.println();
    }
}
