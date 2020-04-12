package web.dto;

import java.util.List;
import java.util.Map;

import domain.Score;
import domain.board.Board;
import domain.board.Rank;
import domain.piece.team.Team;
import web.util.BoardConverter;
import web.util.ScoreConverter;

public class ChessGameDto {
	private String title;
	private String symbol;
	private List<String> unicodeBoard;
	private List<String> symbols;
	private List<Rank> board;
	private String turn;
	private Map<String, Double> score;

	public ChessGameDto(String title, String symbol, String turn) {
		this.title = title;
		this.symbol = symbol;
		this.symbols = BoardConverter.toSymbolsFromSymbol(symbol);
		this.unicodeBoard = BoardConverter.boardToUnicode(symbols);
		this.board = BoardConverter.toBoardFromSymbol(symbol);
		this.score = ScoreConverter.convert(Score.calculateScore(new Board(board).getPieces(), Team.values()));
		this.turn = turn;
	}

	public ChessGameDto(String title, List<Rank> board, String turn, Map<Team, Double> score) {
		this.title = title;
		this.board = board;
		this.symbol = BoardConverter.toSymbolFromBoard(board);
		this.symbols = BoardConverter.toSymbolsFromSymbol(symbol);
		this.turn = turn;
		this.score = ScoreConverter.convert(score);
		this.unicodeBoard = BoardConverter.boardToUnicode(symbols);
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

	public List<String> getSymbols() {
		return symbols;
	}

	public String getSymbol() {
		return symbol;
	}

	public List<Rank> getBoard() {
		return board;
	}

	public List<String> getUnicodeBoard() {
		return unicodeBoard;
	}
}
