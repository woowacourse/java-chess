package chess.service;

import chess.db.dao.ChessBoardStateDAO;
import chess.db.dao.ChessPieceDAO;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.CreateChessBoard;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.position.Position;

import java.util.Map;

public class CreateChessBoardService {
    public static ChessBoard createChessBoard(ChessPieceDAO chessPieceDAO,
                                              ChessBoardStateDAO chessBoardStateDAO) {
        Map<Position, ChessPiece> chessBoard =
                CreateChessBoard.from(chessPieceDAO.selectChessPiece());

        return new ChessBoard(chessBoard, chessBoardStateDAO.selectPlayerTurn());
    }
}
