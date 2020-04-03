package view;

import java.util.List;

public class BoardToTable {
	private final String boardHtml;

	private BoardToTable(final String boardHtml) {
		this.boardHtml = boardHtml;
	}

	public static BoardToTable of(List<List<String>> board) {
		final StringBuilder stringBuilder = new StringBuilder("<table>");
		stringBuilder.append(createTable(board));
		stringBuilder.append("</table>");
		return new BoardToTable(stringBuilder.toString());
	}

	private static StringBuilder createTable(final List<List<String>> board) {
		StringBuilder stringBuilder = new StringBuilder();
		Brightness brightness = Brightness.BRIGHT;
		for (List<String> strings : board) {
			stringBuilder.append("<tr>");
			stringBuilder.append(createRow(strings, brightness));
			stringBuilder.append("</tr>");
			brightness = brightness.makeSwitch();
		}
		return stringBuilder;
	}

	private static StringBuilder createRow(final List<String> strings, Brightness brightness) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String string : strings) {
			stringBuilder.append("<td ")
					.append("class=")
					.append(brightness.getBrightness())
					.append(">")
					.append(Images.getImageHtml(string))
					.append("</td>");
			brightness = brightness.makeSwitch();
		}
		return stringBuilder;
	}

	public String getBoardHtml() {
		return boardHtml;
	}
}
