package chess.view;

import java.util.List;

import chess.domain.BoardConverter;
import chess.domain.ChessBoard;

public class OutputView {
	private OutputView() {
	}

	public static void printInitMessage() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작 : start");
		System.out.println("게임 종료 : end");
	}

	public static void printInitBoard(ChessBoard chessBoard) {
		List<List<String>> stringBoard = BoardConverter.makeStringBoard(chessBoard.getPieces());
		for (List<String> strings : stringBoard) {
			for (String string : strings) {
				System.out.print(string);
			}
			System.out.println();
		}
	}

	public static void printScore(double blackScore, double whiteScore) {
		System.out.println("총 점수");
		System.out.println("흑 : " + blackScore);
		System.out.println("백 : " + whiteScore);
	}
}
