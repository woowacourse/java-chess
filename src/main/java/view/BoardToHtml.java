package view;

import java.util.List;

public class BoardToHtml {
	private final String boardHtml;

	private BoardToHtml(final String boardHtml) {
		this.boardHtml = boardHtml;
	}

	public static BoardToHtml of(List<List<String>> board) {
		final String stringBuilder = "<table>"
				+ createTable(board)
				+ "</table>";
		return new BoardToHtml(stringBuilder);
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
					.append(Images.getImageHtmlByInitial(string))
					.append("</td>");
			brightness = brightness.makeSwitch();
		}
		return stringBuilder;
	}

	public String getHtml() {
		return boardHtml;
	}
}
