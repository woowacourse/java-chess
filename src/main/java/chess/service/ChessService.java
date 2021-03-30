package chess.service;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;

public class ChessService {
    public ChessService() {
    }

    public ChessBoard emptyBoard(){
        return ChessBoardFactory.initializeBoard();
    }
}
