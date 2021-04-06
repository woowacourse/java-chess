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

    public void start() {
        chessBoard.initBoard();
    }

    public boolean movable(String source, String target) {
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
