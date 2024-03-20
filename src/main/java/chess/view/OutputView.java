package chess.view;

import chess.domain.dto.BoardDto;
import java.util.List;

public class OutputView {

    public static void printBoard(BoardDto boardDto) {
        List<List<String>> rawBoard = boardDto.getBoard();

        for (int i = 7; i >= 0; i--) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                stringBuilder.append(rawBoard.get(i).get(j));
            }
            System.out.println(stringBuilder);
        }
    }
}
