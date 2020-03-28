package chess.domain;

import chess.domain.position.Position;

public class Menu {
	public static final String END = "end";
	private static final String START = "start";
	private static final String MOVE = "move";
	private static final String STATUS = "status";
	private static final String BLANK = " ";
	private static final String WRONG_MENU_MESSAGE = "잘못된 메뉴 입력입니다.";
	private static final String NOT_START_MESSAGE = "게임이 시작되지 않았습니다.";
	private static final char CHAR_INT_CHANGE_VALUE = '0';
	private static final int SECOND_INDEX = 1;
	private static final int FIRST_INDEX = 0;
	private static final int THIRD_INDEX = 2;

	private final String menu;

	public Menu(String menu) {
		validateAllowedMenu(menu);
		this.menu = menu;
	}

	private void validateAllowedMenu(String menu) {
		if (!(START.equals(menu) || END.equals(menu)
			|| MOVE.equals(menu.split(BLANK)[FIRST_INDEX]) || STATUS.equals(menu))) {
			throw new IllegalArgumentException(WRONG_MENU_MESSAGE);
		}
	}

	public void validateStart() {
		if (START.equals(menu) == false) {
			throw new UnsupportedOperationException(NOT_START_MESSAGE);
		}
	}

	public boolean isNotEnd() {
		return END.equals(menu) == false;
	}

	public boolean isMove() {
		return MOVE.equals((menu.split(BLANK)[FIRST_INDEX]));
	}

	public Position getStartPosition() {
		String[] menus = menu.split(BLANK);
		char x = menus[SECOND_INDEX].charAt(FIRST_INDEX);
		int y = menus[SECOND_INDEX].charAt(SECOND_INDEX) - CHAR_INT_CHANGE_VALUE;
		return Position.of(y, x);
	}

	public Position getTargetPosition() {
		String[] menus = menu.split(BLANK);
		char x = menus[THIRD_INDEX].charAt(FIRST_INDEX);
		int y = menus[THIRD_INDEX].charAt(SECOND_INDEX) - CHAR_INT_CHANGE_VALUE;
		return Position.of(y, x);
	}

	public boolean isStatus() {
		return STATUS.equals(menu);
	}
}
