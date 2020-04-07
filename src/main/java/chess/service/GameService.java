package chess.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.view.dto.BoardDTO;
import chess.view.dto.ScoreDTO;

public class GameService {
	private final Game game;

	public GameService(Game game) {
		this.game = game;
	}

	public List<ScoreDTO> calculateScore() {
		Map<Team, Double> status = game.status();
		return status.entrySet().stream()
			.map(entry -> new ScoreDTO(entry.getKey().name().toLowerCase(), entry.getValue()))
			.collect(Collectors.toList());
	}

	public void startNewGame() {
		game.start();
	}

	public List<BoardDTO> findAllPiecesOnBoard() {
		Board board = game.getBoard();
		Map<Position, Piece> pieces = board.getPieces();

		return pieces.entrySet().stream()
			.map(entry -> new BoardDTO(entry.getKey().toString(), entry.getValue().getSymbol().toLowerCase(),
				entry.getValue().getTeam().name().toLowerCase()))
			.collect(Collectors.toList());
	}

	public void move(Position from, Position to) {
		game.move(from, to);
	}

	public List<BoardDTO> findChangedPiecesOnBoard(Position from, Position to) {
		Board board = game.getBoard();
		List<BoardDTO> result = new ArrayList<>();
		result.add(new BoardDTO(from.toString(), board.findPiece(from).getSymbol().toLowerCase(),
			board.findPiece(from).getTeam().name().toLowerCase()));
		result.add(new BoardDTO(to.toString(), board.findPiece(to).getSymbol().toLowerCase(),
			board.findPiece(to).getTeam().name().toLowerCase()));
		return result;
	}
}
