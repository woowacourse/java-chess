package chess.service;

import chess.dao.ChessGame.ChessGameDao;
import chess.dao.ChessGame.dto.FindResponseDto;
import chess.dao.ChessGame.dto.SaveRequestDto;
import chess.dao.Piece.PieceDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.position.Position;

import java.util.Map;

public class ChessGameService {

    private static final long TEMPORAL_ID = 1;

    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;

    public ChessGameService(final ChessGameDao chessGameDao, final PieceDao pieceDao) {
        this.chessGameDao = chessGameDao;
        this.pieceDao = pieceDao;
    }

    public ChessGame findChessGame() {
        final FindResponseDto findResponseDto = chessGameDao.findById(TEMPORAL_ID);
        if (findResponseDto == null) {
            return null;
        }
        return new ChessGame(findResponseDto.getId(),
                new Board(pieceDao.findAllById(TEMPORAL_ID)), findResponseDto.getTurn());
    }

    public ChessGame save() {
        Turn turn = Turn.WHITE;
        final Long id = chessGameDao.save(new SaveRequestDto(turn));
        final ChessGame chessGame = new ChessGame(id, BoardFactory.generateBoard(), turn);
        final Map<Position, Piece> board = chessGame.getBoard().getBoard();

        for (final Map.Entry<Position, Piece> entry : board.entrySet()) {
            pieceDao.insert(id, entry.getKey(), entry.getValue());
        }
        return chessGame;
    }

    public void update(final ChessGame chessGame, final Position sourcePosition, final Position targetPosition) {
        final Piece piece = findSourcePiece(chessGame, targetPosition);

        pieceDao.delete(chessGame.getId(), targetPosition);
        pieceDao.delete(chessGame.getId(), sourcePosition);
        pieceDao.insert(chessGame.getId(), targetPosition, piece);
        pieceDao.insert(chessGame.getId(), sourcePosition, new Empty(Type.EMPTY, Side.NEUTRALITY));
        chessGameDao.updateTurn(chessGame.getId(), chessGame.getTurn());
    }

    private Piece findSourcePiece(final ChessGame chessGame, final Position sourcePosition) {
        final Board board = chessGame.getBoard();
        final Piece piece = board.getPiece(sourcePosition);

        return piece;
    }

    public void delete() {
        chessGameDao.delete();
        pieceDao.deleteAll();
    }
}
