package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.game.Result;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Collections;
import java.util.Map;

public class WebChessGame {

    private final ChessBoard chessBoard;
    private Color turn;

    public WebChessGame() {
        this(new ChessBoard(), Color.WHITE);
    }

    public WebChessGame(ChessBoard chessBoard, Color turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    public boolean moved(String source, String target) {
        if (validTurn(source) && movable(source, target)) {
            turn = turn.getOppositeColor();
            return true;
        }
        return false;
    }

    public boolean isOver() {
        return chessBoard.isOver();
    }

    public boolean movable(String source, String target) {
        try {
            chessBoard.move(source, target);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean validTurn(String source) {
        if (turn != chessBoard.getPiece(Position.of(source)).getColor()) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Map<Position, Piece> getChessBoardMap() {
        return Collections.unmodifiableMap(chessBoard.getChessBoard());
    }

    public Piece getPiece(Position position) {
        return chessBoard.getPiece(position);
    }

    public String getTurn() {
        return turn.toString();
    }

    public Result getResult() {
        Result result = new Result();
        result.add(Color.BLACK, chessBoard.score(Color.BLACK));
        result.add(Color.WHITE, chessBoard.score(Color.WHITE));
        return result;
    }
}
