package chess.service;

import chess.domain.game.board.ChessBoard;
import chess.domain.game.board.ChessBoardFactory;
import chess.domain.piece.ChessPiece;

import java.util.Map;

public class ChessService {

    private ChessBoard chessBoard;

    public Map<String, ChessPiece> start(){
        chessBoard.start();
        chessBoard = ChessBoardFactory.initBoard();
        return chessBoard.convertToMap();
    }
}
