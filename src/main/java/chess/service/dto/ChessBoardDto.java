package chess.service.dto;

import chess.domain.Point;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;

import java.util.Map;

public class ChessBoardDto {

    private Color currentOfTurn;
    private Map<Point, Piece> gameBoard;
    private Map<String, String> initWebBoard;

    public Color getCurrentOfTurn() {
        return currentOfTurn;
    }

    public void setCurrentOfTurn(Color currentOfTurn) {
        this.currentOfTurn = currentOfTurn;
    }

    public Map<Point, Piece> getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Map<Point, Piece> gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Map<String, String> getInitWebBoard() {
        return initWebBoard;
    }

    public void setInitWebBoard(Map<String, String> initWebBoard) {
        this.initWebBoard = initWebBoard;
    }
}
