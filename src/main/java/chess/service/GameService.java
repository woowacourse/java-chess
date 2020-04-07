package chess.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.db.dao.BoardDao;
import chess.domain.Game;
import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.BlackPiecesFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.PiecesManager;
import chess.domain.piece.WhitePiecesFactory;

/**
 *    게임 서비스 클래스입니다.
 *
 *    @author AnHyungJu
 */
public class GameService {
	private final BoardDao boardDao;

	public GameService() {
		this.boardDao = new BoardDao();
	}

	public void makeNewGame() throws SQLException {
		Game game = new Game(new PiecesManager(WhitePiecesFactory.create(), BlackPiecesFactory.create()),
			new Board());

		for (File file : File.values()) {
			for (Rank rank : Rank.values()) {
				String position = file.getFile() + rank.getRow();
				Piece piece = game.getBoard().getBoard().get(Position.of(position));
				boardDao.updateBoard(position, piece);
			}
		}
	}

	public void movePiece(String source, String target) throws Exception {
		Game game = load();
		game.movePieceFromTo(Position.of(source), Position.of(target));
		Piece piece = game.getBoard().getBoard().get(Position.of(target));
		boardDao.updateBoard(source, null);
		boardDao.updateBoard(target, piece);
	}

	public Map<Position, Piece> loadBoard() throws Exception {
		Game game = load();
		return game.getBoard().getBoard();
	}

	public double[] status() throws Exception {
		return load().status();
	}

	public boolean isKingDie() throws Exception {
		return load().isKingDie();
	}

	private Game load() throws SQLException {
		Game game = new Game(new PiecesManager(loadWhitePieces(), loadBlackPieces()),
			new Board());

		for (File file : File.values()) {
			for (Rank rank : Rank.values()) {
				String position = file.getFile() + rank.getRow();
				Piece piece = boardDao.findPieceBy(position);
				game.getBoard().getBoard().put(Position.of(position), piece);
			}
		}
		return game;
	}

	private Pieces loadBlackPieces() throws SQLException {
		Map<Position, Piece> blackPieces = new HashMap<>();

		for (File file : File.values()) {
			for (Rank rank : Rank.values()) {
				String position = file.getFile() + rank.getRow();
				Piece piece = boardDao.findBlackPieceBy(position);
				if (piece != null) {
					blackPieces.put(Position.of(position), piece);
				}
			}
		}
		return new Pieces(blackPieces);
	}

	private Pieces loadWhitePieces() throws SQLException {
		Map<Position, Piece> whitePieces = new HashMap<>();

		for (File file : File.values()) {
			for (Rank rank : Rank.values()) {
				String position = file.getFile() + rank.getRow();
				Piece piece = boardDao.findWhitePieceBy(position);
				if (piece != null) {
					whitePieces.put(Position.of(position), piece);
				}
			}
		}
		return new Pieces(whitePieces);
	}
}