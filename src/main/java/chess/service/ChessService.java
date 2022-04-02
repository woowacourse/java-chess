package chess.service;

import chess.Board;
import chess.dao.BoardDao;
import chess.dao.BoardDaoImpl;
import chess.dao.PieceDao;
import chess.dao.PieceDaoImpl;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.PieceFactory;
import chess.piece.Pieces;
import chess.piece.position.Position;

import java.util.List;
import java.util.Map;

public class ChessService {

    private final BoardDao boardDao;
    private final PieceDao pieceDao;

    public ChessService(){
        boardDao = new BoardDaoImpl();
        pieceDao = new PieceDaoImpl();
    }

    public Board loadGame(){

        List<Piece> pieces = pieceDao.findAllByBoardId(1L);
        return Board.create(Pieces.from(pieces));
    }
}
