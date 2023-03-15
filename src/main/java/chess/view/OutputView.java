package chess.view;

import java.util.List;

public class OutputView {

	public static void printBoard(BoardDto boardDto) {
		boardDto.getBoard().forEach(line -> System.out.println(printLine(line)));
	}

	private static String printLine(List<String> line) {
		return String.join("",line);
	}
}
