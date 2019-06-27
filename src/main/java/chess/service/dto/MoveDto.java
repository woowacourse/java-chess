package chess.service.dto;

import chess.domain.ChessGame;
import chess.domain.Point;

public class MoveDto {

    private Point source;
    private Point target;
    private ChessGame chessGame;

    public Point getSource() {
        return source;
    }

    public void setSource(Point source) {
        this.source = source;
    }

    public Point getTarget() {
        return target;
    }

    public void setTarget(Point target) {
        this.target = target;
    }

    public ChessGame getChessGame() {
        return chessGame;
    }

    public void setChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
    }
}
