package chess.dto;

import chess.domain.ChessGameManager;
import chess.domain.piece.Color;

import java.util.Map;

public class NewGameResponse {
    private final Map<String, PieceDto> chessBoard;
    private final Color currentTurnColor;
    private final Map<Color, Double> colorsScore;

    public NewGameResponse(Map<String, PieceDto> chessBoard, Color currentTurnColor, Map<Color, Double> colorsScore) {
        this.chessBoard = chessBoard;
        this.currentTurnColor = currentTurnColor;
        this.colorsScore = colorsScore;
    }

    public static NewGameResponse from(ChessGameManager chessGameManager) {
        return new NewGameResponse(
                ChessBoardDto.from(chessGameManager.getBoard()).board(),
                chessGameManager.getCurrentTurnColor(),
                chessGameManager.getStatistics().getColorsScore());
    }
}
