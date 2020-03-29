package view;

import java.util.List;
import java.util.Map;

import domain.board.Board;
import domain.board.Rank;
import domain.piece.Piece;
import domain.piece.team.Team;

public class OutputView {
	private static final String EMPTY_CELL_SYMBOL = ".";

	public static void printChessGameStart() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
	}

	public static void printChessBoard(Board board) {
		List<Rank> ranks = board.getRanks();
		for (int rankIndex = ranks.size() - 1; rankIndex >= 0; rankIndex--) {
			List<Piece> pieces = ranks.get(rankIndex).getPieces();
			printRank(pieces);
		}
	}

	private static void printRank(List<Piece> pieces) {
		for (int columnIndex = 1; columnIndex <= 8; columnIndex++) {
			final int columnNumber = columnIndex;
			if (pieces.stream().anyMatch(piece -> equalsColumn(columnNumber, piece))) {
				pieces.stream()
					.filter(piece -> equalsColumn(columnNumber, piece))
					.map(Piece::showSymbol)
					.forEach(System.out::print);
				continue;
			}
			System.out.print(EMPTY_CELL_SYMBOL);
		}
		System.out.println();
	}

	public static void printScore(Map<Team, Double> score) {
		for (Team team : score.keySet()) {
			System.out.print(team + ":" + score.get(team) + "점\n");
		}
	}

	private static boolean equalsColumn(final int columnNumber, Piece piece) {
		return columnNumber == piece.getPosition().getColumn().getNumber();
	}
}
