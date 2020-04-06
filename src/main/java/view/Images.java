package view;

import java.util.Arrays;

public enum Images {
	ROOK_BLACK("R", "rook_black.png"),
	ROOK_WHITE("r", "rook_white.png"),
	KNIGHT_BLACK("N", "knight_black.png"),
	KNIGHT_WHITE("n", "knight_white.png"),
	BISHOP_BLACK("B", "bishop_black.png"),
	BISHOP_WHITE("b", "bishop_white.png"),
	QUEEN_BLACK("Q", "queen_black.png"),
	QUEEN_WHITE("q", "queen_white.png"),
	KING_BLACK("K", "king_black.png"),
	KING_WHITE("k", "king_white.png"),
	PAWN_BLACK("P", "pawn_black.png"),
	PAWN_WHITE("p", "pawn_white.png");

	private final String initial;
	private final String image;

	Images(final String initial, final String image) {
		this.initial = initial;
		this.image = image;
	}

	private static String getImageNameByInitial(String initial) {
		return Arrays.stream(values())
				.filter(images -> images.initial.equals(initial))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new)
				.image;
	}

	public static String getImageHtmlByInitial(String initial) {
		try {
			return "<img src=\"/images/"
					+ getImageNameByInitial(initial)
					+ "\">";
		} catch (IllegalArgumentException e) {
			return "";
		}
	}
}
