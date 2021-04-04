package chess.dto;

import chess.domain.ChessGame;
import chess.domain.piece.info.Color;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGameDto {
    private final List<PieceDto> pieces;
    private final Color turn;
    private final Map<Color, Double> totalScoreByColor;
    private final boolean isFinish;

    public ChessGameDto(ChessGame chessGame) {
        this.pieces = chessGame.getPiecesByAllPosition()
                .stream()
                .map(piece -> new PieceDto(piece.color(), piece.name(), piece.position().key()))
                .collect(Collectors.toList());
        this.turn = chessGame.turn();
        this.totalScoreByColor = chessGame.scoreStatus().totalScoreByColor();
        this.isFinish = !chessGame.runnable();
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }

    public Color getTurn() {
        return turn;
    }

    public Map<Color, Double> getTotalScoreByColor() {
        return totalScoreByColor;
    }

    public boolean isFinish() {
        return isFinish;
    }
}
