package chess.domain.chessgame;

import chess.domain.Result;
import chess.domain.Status;
import chess.domain.chessboard.ChessBoard;
import chess.domain.position.Position;
import chess.view.OutputView;

public class Command {
	private static final String NOT_START_MESSAGE = "게임이 시작되지 않았습니다.";
	private static final char CHAR_INT_CHANGE_VALUE = '0';
	private static final int SECOND_INDEX = 1;
	private static final int THIRD_INDEX = 2;
	private static final int FIRST_INDEX = 0;
	private static final String BLANK = " ";

	private final Menu menu;
	private final String command;

	public Command(String command) {
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

	public Position createStartPosition() {
		return createPosition(SECOND_INDEX);
	}

	public Position createTargetPosition() {
		return createPosition(THIRD_INDEX);
	}

	private Position createPosition(int order) {
		String[] Coordinates = command.split(BLANK);
		char x = Coordinates[order].charAt(FIRST_INDEX);
		int y = Coordinates[order].charAt(SECOND_INDEX) - CHAR_INT_CHANGE_VALUE;
		return Position.of(y, x);
	}

	public void execute(ChessBoard chessBoard) {
		if (menu == Menu.MOVE) {
			chessBoard.move(createStartPosition(), createTargetPosition());
		}

		if (menu == Menu.STATUS) {
			Status status = chessBoard.createStatus();
			Result result = status.getResult();
			OutputView.printResult(result);
		}
	}
}
