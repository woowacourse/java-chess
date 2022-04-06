package chess.service;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.Winner;
import chess.domain.board.Position;
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

    public void save(Color turn) {
        final int boardId = boardDao.save(turn);
        pieceDao.save(chessGame.getBoard().getPiecesByPosition(), boardId);
    }

    public ChessBoardDto getBoard() {
        return ChessBoardDto.from(chessGame.getBoard().getPiecesByPosition());
    }

    public void move(Position source, Position target) {
        chessGame.move(source, target);
    }

    public boolean isRunning() {
        return chessGame.isRunning();
    }

    public double statusOfWhite() {
        return chessGame.statusOfWhite();
    }

    public double statusOfBlack() {
        return chessGame.statusOfBlack();
    }

    public void end() {
        chessGame.end();
    }

    public Winner findWinner() {
        return chessGame.findWinner();
    }

    public Color getTurn() {
        return chessGame.getTurn();
    }
}
