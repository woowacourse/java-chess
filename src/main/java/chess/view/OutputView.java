package chess.view;

import chess.dto.BoardDTO;
import chess.dto.LineDTO;
import java.util.stream.Collectors;

public class OutputView {
    public void printGameIntro() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public void printBoard(BoardDTO boardDTO) {
        String board = boardDTO.board().stream()
                .map(LineDTO::line)
                .map(line -> String.join("", line))
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(board);
    }
}
