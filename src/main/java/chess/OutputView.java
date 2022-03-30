package chess;

import java.util.List;

public class OutputView {

    public static void announce(BoardDto boardDto) {
        List<List<String>> dto = boardDto.getDto();
        for (List<String> line : dto) {
            System.out.println(String.join(" ", line));
        }
    }
}
