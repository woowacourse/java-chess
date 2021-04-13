package chess.dto;

import chess.domain.ChessGameManager;
import chess.domain.piece.Color;
import chess.domain.statistics.MatchResult;

import java.util.Map;

public class RunningGameResponse {
    private final Map<String, PieceDto> chessBoard;
    private final Color currentTurnColor;
    private final Map<Color, Double> colorsScore;
    private final MatchResult matchResult;
    private final boolean isEnd;

    public RunningGameResponse(Map<String, PieceDto> chessBoard, Color currentTurnColor, Map<Color, Double> colorsScore, MatchResult matchResult, boolean isEnd) {
        this.chessBoard = chessBoard;
        this.currentTurnColor = currentTurnColor;
        this.colorsScore = colorsScore;
        this.matchResult = matchResult;
        this.isEnd = isEnd;
    }

    public static RunningGameResponse from(ChessGameManager chessGameManager) {
        return new RunningGameResponse(
                ChessBoardDto.from(chessGameManager.getBoard()).board(),
                chessGameManager.getCurrentTurnColor(),
                chessGameManager.getStatistics().getColorsScore(),
                chessGameManager.getStatistics().getMatchResult(),
                chessGameManager.isEnd());
    }
}
