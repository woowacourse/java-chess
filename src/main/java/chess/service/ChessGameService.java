package chess.service;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.Winner;
import chess.domain.board.Position;
import chess.domain.dao.BoardDao;
import chess.domain.dao.PieceDao;
import chess.dto.ChessBoardDto;
import chess.dto.ResponseDto;

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
            chessGame.load(pieceDao.load(), boardDao.findTurn());
            return;
        }
        chessGame.start();
    }

    public ResponseDto move(Position source, Position target) {
        try {
            chessGame.move(source, target);
            savePieces(source, target);
            if (!isRunning()) {
                end();
                return new ResponseDto(301, "");
            }
            return new ResponseDto(302, "");
        } catch (Exception e) {
            return new ResponseDto(501, e.getMessage());
        }
    }

    private void savePieces(Position source, Position target) {
        if (pieceDao.isExistsPieces()) {
            updatePosition(source, target, chessGame.getTurn());
            return;
        }
        save(chessGame.getTurn());
    }

    private void updatePosition(Position source, Position target, Color turn) {
        final int boardId = boardDao.save(turn);
        pieceDao.updatePosition(source.stringName(), target.stringName(), boardId);
    }

    private void save(Color turn) {
        final int boardId = boardDao.save(turn);
        pieceDao.save(chessGame.getBoard().getPiecesByPosition(), boardId);
    }

    public void end() {
        chessGame.end();
        pieceDao.deleteByBoardId(1);
        boardDao.deleteBoard();
    }

    public ChessBoardDto getBoard() {
        return ChessBoardDto.from(chessGame.getBoard().getPiecesByPosition());
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

    public Winner findWinner() {
        return chessGame.findWinner();
    }

    public Color getTurn() {
        return chessGame.getTurn();
    }
}
