package chess.service;

import chess.dao.ChessBoardDao;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.dto.BoardDto;
import chess.dto.ChessDto;

import java.util.List;

public class ChessService {
    private ChessBoardDao chessBoardDao;

    public ChessService(ChessBoardDao chessBoardDao) {
        this.chessBoardDao = chessBoardDao;
    }

    public ChessDto initChessBoard() throws Exception {
        ChessBoard chessBoard = ChessBoard.initPieces();
        List<Piece> pieces = chessBoard.getPieces();
        chessBoardDao.deleteAll();
        chessBoardDao.addPieces(pieces);

        return convertChessBoardState(chessBoard);
    }

    public ChessDto board() throws Exception {
        return convertChessBoardState(chessBoardDao.find());
    }

    public ChessDto move(Position sourcePosition, Position targetPosition) throws Exception {
        ChessBoard chessBoard = chessBoardDao.find();
        boolean isAttack = chessBoard.isPieceInPosition(targetPosition);
        chessBoard.movePiece(sourcePosition, targetPosition);

        if (isAttack) {
            chessBoardDao.deletePiece(targetPosition);
        }
        chessBoardDao.updatePiece(sourcePosition, targetPosition);
        return convertChessBoardState(chessBoard);
    }

    private ChessDto convertChessBoardState(ChessBoard chessBoard) {
        return new ChessDto(!chessBoard.isSurviveKings(), new BoardDto(chessBoard.getPieces()));
    }
}
