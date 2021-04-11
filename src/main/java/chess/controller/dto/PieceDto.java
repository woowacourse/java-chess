package chess.controller.dto;

import chess.domain.piece.Piece;

public class PieceDto {
    private final String name;
    private final String teamColor;
    private final double score;
    private final String currentPosition;

    public PieceDto(Piece piece) {
        this(piece.name(), piece.teamColor().toString(),
                piece.score().value(), new PositionDto(piece.currentPosition()).getPosition());
    }

    public PieceDto(String name, String teamColor, double score, String currentPosition) {
        this.name = name;
        this.teamColor = teamColor;
        this.score = score;
        this.currentPosition = currentPosition;
    }

    public String getName() {
        return name;
    }

    public String getTeamColor() {
        return teamColor;
    }

    public double getScore() {
        return score;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }
}
