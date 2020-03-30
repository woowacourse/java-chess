package chess.view;

import java.util.Map;
import java.util.Objects;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.dto.ResponseDto;

public class OutputView {
	private static final String NEW_LINE = System.lineSeparator();

	public static void printGameStart() {
		System.out.println("체스 게임을 시작합니다." + NEW_LINE +
			"게임 시작 : start" + NEW_LINE +
			"게임 종료 : end " + NEW_LINE +
			"게임 이동 : move source -> target (예 : move b2 b3) ");
	}

	public static void printResponse(ResponseDto response) {
		Map<Position, String> boardDto = response.getBoardDto();
		System.out.println();
		printBoard(boardDto);
		printWinner(response);
	}

	private static void printBoard(Map<Position, String> boardDto) {
		for (Row row : Row.values()) {
			printBoardByColumn(boardDto, row);
			System.out.println();
		}
	}

	private static void printBoardByColumn(Map<Position, String> boardDto, Row row) {
		for (Column column : Column.values()) {
			printEachCell(boardDto, row, column);
		}
	}

	private static void printEachCell(Map<Position, String> boardDto, Row row, Column column) {
		String piece = boardDto.entrySet()
			.stream()
			.filter(entry -> {
				Position position = entry.getKey();
				return position.getColumn() == column && position.getRow() == row;
			})
			.map(Map.Entry::getValue)
			.findAny()
			.orElse(" ");
		System.out.print("[" + piece + "]");
	}

	private static void printWinner(ResponseDto response) {
		if (Objects.nonNull(response.getTurn())) {
			System.out.println(response.getTurn().getTeam().name() + "  is win!!!!!!");
		}
	}

	public static void printException(Exception e) {
		System.out.println(e.getMessage());
	}
}
