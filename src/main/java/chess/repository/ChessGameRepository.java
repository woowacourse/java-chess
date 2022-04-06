package chess.repository;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Optional;

import chess.converter.StringToStateConverter;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.repository.dao.ChessGameDao;
import chess.repository.dao.PieceDao;

public class ChessGameRepository implements GameRepository {

	private final ChessGameDao chessGameDao = new ChessGameDao();
	private final PieceDao pieceDao = new PieceDao();

	@Override
	public void save(ChessGame game) {
		List<PieceDto> pieceDtos = game.getBoard().entrySet().stream()
			.map(entry -> new PieceDto(entry.getValue(), entry.getKey()))
			.collect(toList());
		pieceDao.insertAll(pieceDtos, chessGameDao.insert(game));
	}

	@Override
	public Optional<ChessGame> findByName(String name) {
		String state;
		try {
			state = chessGameDao.selectStateByName(name);
		} catch (IllegalArgumentException exception) {
			return Optional.empty();
		}
		GameState gameState = StringToStateConverter.of(state, pieceDao.selectByGameName(name));

		return Optional.of(new ChessGame(name, gameState));
	}

	@Override
	public void updateStateOfGame(ChessGame game) {
		chessGameDao.updateState(game);
	}

	@Override
	public void updatePositionOfPiece(ChessGame game, Position from, Position to) {
		String gameName = game.getName();
		pieceDao.deleteByPosition(to, gameName);
		Piece piece = game.getPieceByPosition(to);

		pieceDao.updatePositionOfPiece(piece, from, to, gameName);
	}

	@Override
	public void remove(String name) {
		chessGameDao.delete(name);
	}

	@Override
	public List<String> findAllNames() {
		return chessGameDao.selectAllNames();
	}
}
