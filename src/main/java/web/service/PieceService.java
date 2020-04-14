package web.service;

import static domain.board.Board.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.Score;
import domain.board.BoardGame;
import domain.board.Rank;
import domain.command.MoveCommand;
import domain.piece.Piece;
import domain.piece.team.Team;
import web.dao.PieceDao;
import web.dao.TurnDao;
import web.dto.BoardDto;
import web.dto.PieceDto;
import web.dto.TurnDto;
import web.util.PieceFactory;
import web.util.ScoreConverter;

public class PieceService {
	private static final String BLANK = "";
	private PieceDao pieceDao = PieceDao.getInstance();
	private TurnDao turnDao = TurnDao.getInstance();

	public BoardDto createPieces() throws SQLException {
		BoardGame boardGame = new BoardGame();
		addTurn(boardGame);
		return addBoard(boardGame);
	}

	public BoardDto move(String command) throws SQLException {
		List<Piece> pieces = pieceDao.findAll();
		TurnDto turnDto = turnDao.find();

		BoardGame boardGame = new BoardGame(pieces, turnDto.getTurn());
		MoveCommand moveCommand = new MoveCommand(command);
		boardGame.move(moveCommand);

		String originalPosition = moveCommand.getSourcePosition().toString();
		String newPosition = moveCommand.getTargetPosition().toString();
		updateTurn(boardGame);
		return updateBoard(boardGame, originalPosition, newPosition);
	}

	public BoardDto load() throws SQLException {
		List<Piece> pieces = pieceDao.findAll();
		TurnDto turnDto = turnDao.find();
		BoardGame boardGame = new BoardGame(pieces, turnDto.getTurn());

		List<String> symbols = convert(boardGame.getReverse());
		Map<Team, Double> score = Score.calculateScore(boardGame.getPieces(), Team.values());
		return new BoardDto(symbols, ScoreConverter.convert(score));
	}

	private void addTurn(BoardGame boardGame) throws SQLException {
		Team turn = boardGame.getTurn();
		TurnDto turnDto = new TurnDto(turn.getName());
		turnDao.add(turnDto);
	}

	private void updateTurn(BoardGame boardGame) throws SQLException {
		Team turn = boardGame.getTurn();
		TurnDto turnDto = new TurnDto(turn.getName());
		turnDao.update(turnDto);
	}

	private BoardDto addBoard(BoardGame boardGame) throws SQLException {
		List<Piece> pieces = boardGame.getPieces();
		for (Piece piece : pieces) {
			addPieces(piece);
		}
		List<String> symbols = convert(boardGame.getReverse());
		Map<Team, Double> score = Score.calculateScore(boardGame.getPieces(), Team.values());
		return new BoardDto(symbols, ScoreConverter.convert(score));
	}

	private void addPieces(Piece piece) throws SQLException {
		Team team = piece.getTeam();
		String position = piece.getPosition().toString();
		PieceDto pieceDto = new PieceDto(piece.showSymbol(), team.getName(), position);
		pieceDao.add(pieceDto);
	}

	private BoardDto updateBoard(BoardGame boardGame, String originalPosition, String newPosition) throws
		SQLException {
		pieceDao.update(originalPosition, newPosition);
		List<String> symbols = convert(boardGame.getReverse());
		Map<Team, Double> score = Score.calculateScore(boardGame.getPieces(), Team.values());
		return new BoardDto(symbols, ScoreConverter.convert(score));
	}

	private List<String> convert(List<Rank> board) {
		List<String> pieces = new ArrayList<>();
		for (Rank rank : board) {
			for (int i = MIN_COLUMN_COUNT; i <= MAX_COLUMN_COUNT; i++) {
				final int columnNumber = i;
				String pieceSymbol = rank.getPieces().stream()
					.filter(p -> p.equalsColumn(columnNumber))
					.map(Piece::showSymbol)
					.findFirst()
					.orElse(BLANK);
				pieces.add(PieceFactory.convert(pieceSymbol));
			}
		}
		return pieces;
	}
}