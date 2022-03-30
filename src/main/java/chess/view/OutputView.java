package chess.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import chess.domain.Status;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Rank;
import chess.domain.position.Square;

public class OutputView {
	private static final String MESSAGE_START = "> 체스 게임을 시작합니다.";
	private static final String MESSAGE_INPUT_START = "> 게임 시작 : start";
	private static final String MESSAGE_INPUT_END = "> 게임 종료 : end";
	private static final String MESSAGE_INPUT_MOVE = "> 게임 이동 : move source 위치 target 위치 - 예. move b2 b3";
	private static final String MESSAGE_INPUT_STATUS = "> 결과 출력 : status";
	private static final String RESULT_FORMAT = "%s : %.1f점%n";
	private static final String MESSAGE_GAME_END = "king 잡았다!\n";

	public static void announceStart() {
		System.out.println(MESSAGE_START);
		System.out.println(MESSAGE_INPUT_START);
		System.out.println(MESSAGE_INPUT_END);
		System.out.println(MESSAGE_INPUT_MOVE);
		System.out.println(MESSAGE_INPUT_STATUS);
	}

	public static void showBoard(Map<Square, Piece> board) {
		List<Piece> pieces = new ArrayList<>(board.values());
		printBoard(pieces);
		System.out.println();
	}

	private static void printBoard(List<Piece> pieces) {
		List<List<Piece>> splitBoardByRank = Lists.partition(pieces, Rank.values().length);
		ListIterator<List<Piece>> iterator = splitBoardByRank.listIterator(splitBoardByRank.size());

		while (iterator.hasPrevious()) {
			List<String> names = getNames(iterator.previous());
			printOneRank(names);
		}
	}

	private static void printOneRank(List<String> pieceName) {
		pieceName.forEach(System.out::print);
		System.out.println();
	}

	private static List<String> getNames(List<Piece> splitPiece) {
		return splitPiece.stream()
			.map(Piece::getEmoji)
			.collect(Collectors.toList());
	}

	public static void printMessage(String message) {
		System.out.println(message);
	}

	public static void showScore(Status status, Color color) {
		System.out.printf(RESULT_FORMAT, color.getName(), status.calculateScore(color));
	}

	public static void printKingDieMessage() {
		System.out.println(MESSAGE_GAME_END);
	}
}
