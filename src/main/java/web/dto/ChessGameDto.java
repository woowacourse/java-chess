package web.dto;

import static domain.board.Board.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.board.Rank;
import domain.piece.Piece;
import domain.piece.team.Team;
import web.UnicodeConverter;

public class ChessGameDto {
	private String title;
	private String boardStringLine;
	private List<String> reverseBoard;
	private List<Rank> originalBoard;
	private String turn;
	private Map<String, Double> score;

	public ChessGameDto(String title, String board, String turn) {
		this.title =title;
		this.boardStringLine = board;
		this.turn = turn;
	}

	public ChessGameDto(String title, List<Rank> originalBoard, String turn, Map<Team, Double> score) {
		this.title = title;
		this.boardStringLine = boardToString(boardToSymbol(originalBoard));
		this.turn = turn;
		this.score = scoreConverter(score);
		reverseBoard = boardToUnicode(boardToSymbol(originalBoard));
	}

	private List<String> boardToSymbol(List<Rank> inputBoard) {
		List<String> board = new ArrayList<>();
		for (Rank rank : inputBoard) {
			for (int i = MIN_COLUMN_COUNT; i <= MAX_COLUMN_COUNT; i++) {
				final int columnNumber = i;
				String pieceSymbol = rank.getPieces().stream()
					.filter(p -> p.equalsColumn(columnNumber))
					.map(Piece::showSymbol)
					.findFirst()
					.orElse(".");
				board.add(pieceSymbol);
			}
		}
		return board;
	}

	private String boardToString(List<String> board) {
		return String.join("", board);
	}

	private Map<String, Double> scoreConverter(Map<Team, Double> inputScore) {
		return inputScore.entrySet().stream()
			.collect(Collectors.toMap(k -> k.getKey().getName(), Map.Entry::getValue));
	}

	private List<String> boardToUnicode(List<String> board) {
		return board.stream()
			.map(UnicodeConverter::convert)
			.collect(Collectors.toList());
	}

	public String getBoardStringLine() {
		return boardStringLine;
	}

	public Map<String, Double> getScore() {
		return score;
	}

	public String getTitle() {
		return title;
	}

	public String getTurn() {
		return turn;
	}

	public List<String> getReverseBoard() {
		return reverseBoard;
	}
}
