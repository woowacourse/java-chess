package chess.view;

import chess.domain.dto.BoardDto;
import chess.domain.dto.ResponseDto;
import chess.domain.dto.ResponseType;
import chess.domain.dto.ScoreDto;
import chess.domain.team.Winner;
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
        if (isBoardResponse(responseDto)) {
            BoardDto boardDto = (BoardDto) responseDto.getValue();
            Arrays.stream(boardDto.getValue())
                .forEach(System.out::println);
            System.out.println();
            return;
        }
        ScoreDto scoreDto = (ScoreDto) responseDto.getValue();
        double whiteScore = scoreDto.getWhiteScore();
        double blackScore = scoreDto.getBlackScore();
        Winner winner = scoreDto.getWinner();
        System.out.println("화이트: " + whiteScore);
        System.out.println("블랙: " + blackScore);
        System.out.println("승자: " + winner.getValue());
    }

    private static boolean isBoardResponse(ResponseDto responseDto) {
        return responseDto.getType() == ResponseType.BOARD;
    }
}
