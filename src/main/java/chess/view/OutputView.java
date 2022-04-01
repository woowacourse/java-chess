package chess.view;

import chess.dto.BoardDto;
import chess.dto.ScoreDto;
import java.util.List;

public class OutputView {

    private static final String STATUS_DELIMITER = " : ";
    private static final String POINT_RESULT_MESSAGE_FORMAT = "승부 결과 : %s\n";

    public static void announce(BoardDto boardDto) {
        List<List<String>> dto = boardDto.getDto();
        for (List<String> line : dto) {
            System.out.println(String.join(" ", line));
        }
    }

    public static void announceScoreDto(ScoreDto scoreDto) {
        for (String team : scoreDto.getScore().keySet()) {
            System.out.println(team + STATUS_DELIMITER + scoreDto.getScore().get(team));
        }
        System.out.println();
        System.out.printf(POINT_RESULT_MESSAGE_FORMAT, scoreDto.getWinner());
    }
}
