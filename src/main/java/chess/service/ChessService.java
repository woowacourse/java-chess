package chess.service;

import chess.domain.game.board.ChessBoard;
import chess.domain.game.board.ChessBoardFactory;
import chess.domain.piece.ChessPiece;

import java.util.Map;

public class ChessService {

    private ChessBoard chessBoard = null;

    public Map<String, ChessPiece> start(){
        chessBoard = ChessBoardFactory.initBoard();
        chessBoard.start();
        return chessBoard.convertToMap();
    }
}
