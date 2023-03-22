package chess.controller;

import java.util.Arrays;
import java.util.List;

import chess.domain.Position;
import chess.view.Command;
import chess.view.CommandDto;

public class InputRenderer {

	private static final String POSITION_FORMAT_ERROR_MESSAGE1 = "source 위치와 target 위치를 입력해야 합니다. 예. move b2 b3";
	private static final String INVALID_COMMAND_ERROR_MESSAGE = "불가능한 명령입니다.";
	private static final String POSITION_FORMAT_ERROR_MESSAGE2 = "왼쪽부터 a ~ h인 가로 위치와 "
		+ " 아래부터 1 ~ 8인 세로 위치를 입력해야 합니다.";
	private static final int POSITION_FORMAT_EXPECTED_SIZE = 3;
	private static final int COMMAND_INDEX = 0;
	private static final int SOURCE_POSITION_INDEX = 1;
	private static final int TARGET_POSITION_INDEX = 2;
	private static final int RAW_POSITION_FORMAT_EXPECTED_SIZE = 2;
	private static final int COLUMN_INDEX = 0;
	private static final int ROW_INDEX = 1;
	private static final String MOVE_COMMAND_SPLIT_DELIMITER = " ";
	private static final char INITIAL_FILE = 'a';
	private static final char INITIAL_RANK = '1';

	public static CommandDto toCommandDto(final String string) {
		Command command = toCommand(string);
		if (command == Command.MOVE) {
			List<String> commandAndPositions = Arrays.asList(string.split(MOVE_COMMAND_SPLIT_DELIMITER));
			if (commandAndPositions.size() != POSITION_FORMAT_EXPECTED_SIZE) {
				throw new IllegalArgumentException(POSITION_FORMAT_ERROR_MESSAGE1);
			}
			Position sourcePosition = toPosition(commandAndPositions.get(SOURCE_POSITION_INDEX));
			Position targetPosition = toPosition(commandAndPositions.get(TARGET_POSITION_INDEX));
			return new CommandDto(command, sourcePosition, targetPosition);
		}
		return new CommandDto(command);
	}

	private static Command toCommand(final String string) {
		try {
			return Command.valueOf(getUpperCasedFirstWord(string));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(INVALID_COMMAND_ERROR_MESSAGE);
		}
	}

	private static String getUpperCasedFirstWord(String string) {
		return string.split(MOVE_COMMAND_SPLIT_DELIMITER)[COMMAND_INDEX].toUpperCase();
	}

	private static Position toPosition(String rawPosition) {
		if (rawPosition.length() != RAW_POSITION_FORMAT_EXPECTED_SIZE) {
			throw new IllegalArgumentException(POSITION_FORMAT_ERROR_MESSAGE2);
		}
		int column = getColumn(rawPosition);
		int row = getRow(rawPosition);
		return new Position(column, row);
	}

	private static int getColumn(String rawPosition) {
		char file = rawPosition.toCharArray()[COLUMN_INDEX];
		return file - INITIAL_FILE;
	}

	private static int getRow(String rawPosition) {
		char rank = rawPosition.toCharArray()[ROW_INDEX];
		return rank - INITIAL_RANK;
	}
}
