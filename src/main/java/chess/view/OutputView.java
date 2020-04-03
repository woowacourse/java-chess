package chess.view;

import java.util.ArrayList;
import java.util.List;

import chess.domain.ChessBoard;

public class OutputView {
	private static List<List<String>> emptyBoard;

	public static void printInitMessage() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작 : start");
		System.out.println("게임 종료 : end");
	}

	public static void printInitBoard(ChessBoard chessBoard) {
		chessBoard.getPieces()
				.forEach(piece -> emptyBoard.get(-piece.getPosition().getRow().getSymbol() + 8)
						.set(piece.getPosition().getCol().getValue() - 1, piece.getName()));
		for (List<String> strings : emptyBoard) {
			for (String string : strings) {
				System.out.print(string);
			}
			System.out.println();
		}
	}

	public static void createEmptyBoard(int col, int row) {
		emptyBoard = new ArrayList<>();
		for (int i = 0; i < row; i++) {
			createColumn(col);
		}
	}

	private static void createColumn(int col) {
		List<String> emptyRow = new ArrayList<>();
		for (int j = 0; j < col; j++) {
			emptyRow.add(".");
		}
		emptyBoard.add(emptyRow);
	}

	public static void printScore(double blackScore, double whiteScore) {
		System.out.println("총 점수");
		System.out.println("흑 : " + blackScore);
		System.out.println("백 : " + whiteScore);
	}
}
