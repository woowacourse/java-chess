package chess.domain;

public class Menu {
	private static final String START = "start";
	private static final String END = "end";

	private final String menu;

	public Menu(String menu) {
		validateAllowedMenu(menu);
		this.menu = menu;
	}

	private void validateAllowedMenu(String menu) {
		if (!(START.equals(menu) || END.equals(menu))) {
			throw new IllegalArgumentException();
		}
	}

	public boolean isStart() {
		return START.equals(menu);
	}
}
