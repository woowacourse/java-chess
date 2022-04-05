package chess.service;

import chess.domain.game.board.ChessBoard;
import chess.domain.game.board.ChessBoardFactory;
import chess.domain.piece.ChessPiece;
import chess.domain.position.Position;
import chess.view.OutputView;

import java.util.Map;

public class ChessService {

    private ChessBoard chessBoard = null;

    public void start(){
        chessBoard = ChessBoardFactory.initBoard();
        chessBoard.start();
    }

    public Map<String, ChessPiece> getCurrentBoard() {
        return chessBoard.convertToMap();
    }

    public Map<String, ChessPiece> move(String source, String target){
        chessBoard.move(new Position(source), new Position(target));
        return getCurrentBoard();
    }
}
