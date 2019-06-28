package chess.service.dto;

import chess.domain.ChessGame;
import chess.domain.pieces.Color;

import java.util.Map;

public class ChessGameDto {

    private Color currentOfTurn;
    private Map<String, String> initWebBoard;
    private ChessGame chessGame;

    public Map<String, String> getInitWebBoard() {
        return initWebBoard;
    }

    public void setInitWebBoard(Map<String, String> initWebBoard) {
        this.initWebBoard = initWebBoard;
    }

    public ChessGame getChessGame() {
        return chessGame;
    }

    public void setChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public Color getCurrentOfTurn() {
        return currentOfTurn;
    }

    public void setCurrentOfTurn(Color currentOfTurn) {
        this.currentOfTurn = currentOfTurn;
    }
}
