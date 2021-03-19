package chess.service;

import chess.domain.grid.Grid;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class ChessService {
    private boolean isBlackTurn;
    private boolean gameOver;
    private Grid grid;

    public ChessService() {
        grid = new Grid();
        isBlackTurn = false;
        gameOver = false;
    }

    public Grid grid() {
        return grid;
    }

    public boolean isBlackTurn() {
        return isBlackTurn;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void changeTurn() {
        isBlackTurn = !isBlackTurn;
    }

    public double score(boolean isBlack) {
        return grid.score(isBlack);
    }

    public Piece piece(Position position) {
        return grid.piece(position);
    }

    public void move(Piece source, Piece target) {
        validateTurn(source);
        grid.move(source, target);
    }

    private void validateTurn(Piece sourcePiece) {
        if (!sourcePiece.isEmpty() && isBlackTurn() != sourcePiece.isBlack()) {
            throw new IllegalArgumentException("자신의 말만 옮길 수 있습니다.");
        }
    }
}
