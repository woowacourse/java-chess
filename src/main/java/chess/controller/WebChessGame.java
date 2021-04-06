package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Map;

public class WebChessGame {

    private final ChessBoard chessBoard;
    private Color turn;

    public WebChessGame() {
        this.chessBoard = new ChessBoard();
        this.turn = Color.WHITE;
    }

    public ChessBoard start() {
        chessBoard.initBoard();
        return chessBoard;
    }

    public boolean movable(String source, String target) {
        System.out.println(source);
        try {
            chessBoard.move(source, target);
            turn = turn.getOppositeColor();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getChessBoard();
    }
}
