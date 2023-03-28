package chess.service;

import chess.ChessGame;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import dao.ChessStatusDao;
import dao.PieceDao;
import java.util.List;
import java.util.Map;

public final class ChessService {

    private final PieceDao pieceDao;
    private final ChessStatusDao chessStatusDao;

    public ChessService(final PieceDao pieceDao, final ChessStatusDao chessStatusDao) {
        this.pieceDao = pieceDao;
        this.chessStatusDao = chessStatusDao;
    }

    public List<String> readGameIds() {
        return chessStatusDao.readGameIds();
    }

    public ChessGame readChessGame(final String gameId) {
        final Map<Position, Piece> board = pieceDao.read(gameId);
        if (board.isEmpty()) {
            return null;
        }

        Color turn = Color.valueOf(chessStatusDao.readTurn(gameId));
        return new ChessGame(new Board(board), turn);
    }

    public String createChessStatus(ChessGame chessGame) {
        return chessStatusDao.create(chessGame.turn().name());
    }

    public void createChessGame(ChessGame chessGame, String gameId) {
        final Map<Position, Piece> board = chessGame.board();
        for (final Map.Entry<Position, Piece> entry : board.entrySet()) {
            final Position position = entry.getKey();
            final Piece piece = entry.getValue();
            pieceDao.createPiece(position, piece, gameId);
        }
    }

    public void deleteAll(final String gameId) {
        pieceDao.deleteAll(gameId);
    }

    public void update(final ChessGame chessGame, final Position from, final Position to, final String gameId) {
        chessStatusDao.update(chessGame.turn().name(), gameId);
        pieceDao.update(from, to, gameId);
    }

    private static String convert(final Position position) {
        return position.file().command() + position.rank().command();
    }
}
