package view;

import static domain.board.Board.*;

import java.util.List;
import java.util.Map;

import domain.board.Board;
import domain.board.BoardGame;
import domain.board.Rank;
import domain.piece.Piece;
import domain.piece.team.Team;

public class OutputView {
	private static final String CHESS_GAME_START_MESSAGE = "체스 게임을 시작합니다.";
	private static final String CHESS_GAME_END_MESSAGE = "게임이 종료되었습니다.";
	private static final String INPUT_COMMAND_MESSAGE = "게임 시작은 start, 종료는 end 명령을 입력하세요.";
	private static final String EMPTY_CELL_SYMBOL = ".";
	private static final String COLON = ":";
	private static final String SCORE_MESSAGE = "점";

	public static void printGameIsEnd() {
		System.out.println(CHESS_GAME_END_MESSAGE);
	}

	public static void printChessGameStart() {
		System.out.println(CHESS_GAME_START_MESSAGE);
		System.out.println(INPUT_COMMAND_MESSAGE);
	}

	public static void printChessBoard(BoardGame board) {
		List<Rank> ranks = board.getBoard().getRanks();
		for (int rankIndex = ranks.size() - 1; rankIndex >= 0; rankIndex--) {
			List<Piece> pieces = ranks.get(rankIndex).getPieces();
			printRank(pieces);
		}
	}

	private static void printRank(List<Piece> rank) {
		for (int columnIndex = MIN_COLUMN_COUNT; columnIndex <= MAX_COLUMN_COUNT; columnIndex++) {
			final int columnNumber = columnIndex;
			String pieceSymbol = rank.stream()
				.filter(p -> equalsColumn(columnNumber, p))
				.map(Piece::showSymbol)
				.findFirst()
				.orElse(EMPTY_CELL_SYMBOL);
			System.out.print(pieceSymbol);
		}
		System.out.println();
	}

	public static void printScore(Map<Team, Double> score) {
		for (Team team : score.keySet()) {
			System.out.println(team + COLON + score.get(team) + SCORE_MESSAGE);
		}
	}

	private static boolean equalsColumn(final int columnNumber, Piece piece) {
		return columnNumber == piece.getPosition().getColumn().getNumber();
	}
}
