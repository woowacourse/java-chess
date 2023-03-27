package chess.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class OutputView {

	public static void printBoard(final Map<Position, Piece> chessBoard, final List<Rank> ranks,
		final List<File> files) {
		for (final Rank rank : sortRank(ranks)) {
			printEachPosition(chessBoard, files, rank);
		}
		System.out.println("\nabcdefgh\n");
	}

	private static void printEachPosition(final Map<Position, Piece> chessBoard, final List<File> files,
		final Rank rank) {
		for (final File file : sortFile(files)) {
			final Position position = Position.of(file, rank);
			System.out.print(chessBoard.get(position).name());
		}
		System.out.print("\t" + rank.value());
		System.out.println();
	}

	private static List<File> sortFile(final List<File> files) {
		final List<Character> values = new ArrayList<>();
		for (File file : files) {
			values.add(file.value());
		}
		values.sort(Comparator.naturalOrder());
		final List<File> sortedFiles = new ArrayList<>();
		for (Character value : values) {
			sortedFiles.add(File.from(value));
		}
		return sortedFiles;
	}

	private static List<Rank> sortRank(final List<Rank> ranks) {
		final List<Integer> values = new ArrayList<>();
		for (Rank rank : ranks) {
			values.add(rank.value());
		}
		values.sort(Comparator.reverseOrder());
		final List<Rank> sortedRanks = new ArrayList<>();
		for (Integer value : values) {
			sortedRanks.add(Rank.from(value));
		}
		return sortedRanks;
	}

	public static void printStatus(final double blackScore, final double whiteScore) {
		System.out.println("게임 상태");
		System.out.println("Black 팀: " + blackScore + "점");
		System.out.println("White 팀: " + whiteScore + "점");
		System.out.println(printWinnerTeam(blackScore, whiteScore));
		System.out.println();
	}

	private static String printWinnerTeam(double blackScore, double whiteScore) {
		if (blackScore > whiteScore) {
			return "Black 팀 승리";
		}
		if (whiteScore > blackScore) {
			return "White 팀 승리";
		}
		return "무승부";
	}

	public static void printErrorMessage(String errorMessage) {
		System.out.println("[ERROR] " + errorMessage);
	}

	public static void printKingDead() {
		System.out.println("King이 잡혀 게임을 종료합니다");
	}
}
