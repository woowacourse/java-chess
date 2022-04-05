package service;

import chess.domain.ChessGame;
import chess.domain.dao.BoardDao;
import chess.domain.dao.PieceDao;
import chess.dto.ChessBoardDto;

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

    public ChessBoardDto getBoard() {
        return ChessBoardDto.from(chessGame.getBoard().getPiecesByPosition());
    }

    public void load() {

    }
}
