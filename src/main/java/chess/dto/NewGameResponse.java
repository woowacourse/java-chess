package chess.dto;

import chess.domain.ChessGameManager;
import chess.domain.piece.Color;

import java.util.Map;

public class NewGameResponse {
    private final Map<String, PieceDto> chessBoard;
    private final Color currentTurnColor;
    private final Map<Color, Double> colorsScore;
    private final boolean isEnd;

    public NewGameResponse(Map<String, PieceDto> chessBoard, Color currentTurnColor, Map<Color, Double> colorsScore, boolean isEnd) {
        this.chessBoard = chessBoard;
        this.currentTurnColor = currentTurnColor;
        this.colorsScore = colorsScore;
        this.isEnd = isEnd;
    }

    public static NewGameResponse from(ChessGameManager chessGameManager) {
        return new NewGameResponse(
                ChessBoardDto.from(chessGameManager.getBoard()).board(),
                chessGameManager.getCurrentTurnColor(),
                chessGameManager.getStatistics().getColorsScore(),
                chessGameManager.isEnd());
    }
}
