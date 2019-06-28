package chess.service.dto;

import chess.domain.ChessGame;
import chess.domain.Point;

public class MoveInfoDto {

    private ChessGame chessGame;
    private Point source;
    private Point target;

    public MoveInfoDto(ChessGame chessGame, Point source, Point target) {
        this.chessGame = chessGame;
        this.source = source;
        this.target = target;
    }

    public ChessGame getChessGame() {
        return chessGame;
    }

    public void setChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

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
}
