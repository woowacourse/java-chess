package service;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.board.Position;
import chess.domain.dao.BoardDao;
import chess.domain.dao.PieceDao;
import chess.domain.piece.Piece;
import chess.dto.ChessBoardDto;
import java.util.Map;

public class ChessGameService {

    private final PieceDao pieceDao;
    private final BoardDao boardDao;
    private ChessGame chessGame;

    public ChessGameService(PieceDao pieceDao, BoardDao boardDao) {
        this.pieceDao = pieceDao;
        this.boardDao = boardDao;
    }

    public void start() {
        chessGame = new ChessGame();
        if (pieceDao.isExistsPieces()) {
            chessGame.load(pieceDao.load(), boardDao.getCurrentTurn());
            return;
        }
        chessGame.start();
    }

    public void save(Map<Position, Piece> pieces, Color turn) {
        final int boardId = boardDao.save(turn);
        pieceDao.save(pieces, boardId);
    }

    public ChessBoardDto getBoard() {
        return ChessBoardDto.from(chessGame.getBoard().getPiecesByPosition());
    }
}
