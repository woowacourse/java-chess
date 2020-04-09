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
import chess.repository.GameDAO;
import chess.view.dto.requestdto.PositionRequestDTO;
import chess.view.dto.responsedto.BoardDTO;
import chess.view.dto.responsedto.GameDTO;
import chess.view.dto.responsedto.ScoreDTO;

public class GameService {
	private GameDAO gameDAO;

	public GameService(GameDAO gameDAO) {
		this.gameDAO = gameDAO;
	}

	public List<ScoreDTO> calculateScore() {
		Game game = gameDAO.findById(1);
		Map<Team, Double> status = game.status();
		return status.entrySet().stream()
			.map(entry -> new ScoreDTO(entry.getKey().name().toLowerCase(), entry.getValue()))
			.collect(Collectors.toList());
	}

	public void changeState(String request) {
		if (!"start".equals(request) && !"end".equals(request)) {
			throw new IllegalArgumentException("유효한 명령어가 아닙니다.");
		}
		Game game = gameDAO.findById(1);
		if ("start".equals(request)) {
			game.start();
		} else {
			game.end();
		}
		gameDAO.update(game);
	}

	public List<BoardDTO> findAllPiecesOnBoard() {
		Game game = gameDAO.findById(1);
		Board board = game.getBoard();
		Map<Position, Piece> pieces = board.getPieces();

		return pieces.entrySet().stream()
			.map(entry -> new BoardDTO(entry.getKey().toString(), entry.getValue().getSymbol()))
			.collect(Collectors.toList());
	}

	public void move(PositionRequestDTO positionRequestDTO) {
		Position from = Position.of(positionRequestDTO.getFrom());
		Position to = Position.of(positionRequestDTO.getTo());
		Game game = gameDAO.findById(1);
		game.move(from, to);
		gameDAO.update(game);
	}

	public List<BoardDTO> findChangedPiecesOnBoard(PositionRequestDTO positionRequestDTO) {
		Position from = Position.of(positionRequestDTO.getFrom());
		Position to = Position.of(positionRequestDTO.getTo());
		Game game = gameDAO.findById(1);
		Board board = game.getBoard();
		List<BoardDTO> result = new ArrayList<>();
		result.add(new BoardDTO(from.toString(), board.findPiece(from).getSymbol()));
		result.add(new BoardDTO(to.toString(), board.findPiece(to).getSymbol()));
		return result;
	}

	public GameDTO getCurrentState() {
		Game game = gameDAO.findById(1);
		return GameDTO.of(game.getTurn(), game.getStateType());
	}

	public String getWinner() {
		Game game = gameDAO.findById(1);
		return game.findWinner().name().toLowerCase();
	}

	public boolean isNotFinish() {
		Game game = gameDAO.findById(1);
		return game.isNotEnd();
	}
}
