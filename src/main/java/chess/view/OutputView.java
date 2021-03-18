package chess.view;

import chess.domain.board.LineDto;

import java.util.List;

public class OutputView {
    public static void printGameStart(List<LineDto> lineDtos) {
        for (LineDto lineDto : lineDtos) {
            for (String piece : lineDto.getPieces()) {
                System.out.print(piece);
            }
            System.out.println();
        }
    }
}
