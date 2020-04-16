package chess.service;

import chess.db.dao.ChessBoardStateDAO;
import chess.db.dao.PieceDAO;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.position.Position;

import java.util.Map;

public class CreateChessBoardFromDB {
    public static ChessBoard createChessBoard(PieceDAO whitePieceDAO,PieceDAO blackPieceDAO,
                                       ChessBoardStateDAO chessBoardStateDAO) {
        Map<Position, ChessPiece> chessBoard =
                chess.domain.chessBoard.CreateChessBoard.from(whitePieceDAO.selectBoard(), blackPieceDAO.selectBoard());

        return new ChessBoard(chessBoard, chessBoardStateDAO.selectPlayerTurn());
    }
}
