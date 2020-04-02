package chess.domain.chessgame;

import chess.domain.position.Position;

public class ChessMenu {
	private static final String NOT_START_MESSAGE = "게임이 시작되지 않았습니다.";
	private static final int SECOND_INDEX = 1;
	private static final int THIRD_INDEX = 2;
	private static final String BLANK = " ";

	private final Menu menu;
	private final String command;

	public ChessMenu(String command) {
		this.menu = Menu.of(command);
		this.command = command;
	}

	public void validateStart() {
		if (menu != Menu.START) {
			throw new UnsupportedOperationException(NOT_START_MESSAGE);
		}
	}

	public boolean isNotEnd() {
		return menu != Menu.END;
	}

	public boolean isMove() {
		return menu == Menu.MOVE;
	}

	public boolean isStatus() {
		return menu == Menu.STATUS;
	}

	public Position createStartPosition() {
		return createPosition(SECOND_INDEX);
	}

	public Position createTargetPosition() {
		return createPosition(THIRD_INDEX);
	}

	private Position createPosition(int order) {
		String[] Coordinates = command.split(BLANK);
		return Position.of(Coordinates[order]);
	}
}
