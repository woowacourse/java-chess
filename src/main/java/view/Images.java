package view;

public enum Images {
	R("rook_black.png"),
	r("rook_white.png"),
	N("knight_black.png"),
	n("knight_white.png"),
	B("bishop_black.png"),
	b("bishop_white.png"),
	Q("queen_black.png"),
	q("queen_white.png"),
	K("king_black.png"),
	k("king_white.png"),
	P("pawn_black.png"),
	p("pawn_white.png");

	private final String image;

	Images(final String image) {
		this.image = image;
	}

	private static String getImageName(String initial) {
		return valueOf(initial).image;
	}

	public static String getImageHtml(String initial) {
		try {
			return "<img src=\"/images/"
					+ getImageName(initial)
					+ "\">";
		} catch (IllegalArgumentException e) {
			return "";
		}
	}
}
