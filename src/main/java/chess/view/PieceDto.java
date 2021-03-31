package chess.view;

import chess.domain.piece.Piece;

import java.util.List;
import java.util.stream.Collectors;

public class PieceDto {
    private final String name;
    private final String teamColor;
    private final double score;
    private final String currentPosition;
    private final List<PositionDto> movablePosition;

    public PieceDto(Piece piece) {
        this.name = piece.name();
        this.teamColor = piece.teamColor().toString();
        this.score = piece.score().value();
        this.currentPosition = new PositionDto(piece.currentPosition()).getPosition();
        this.movablePosition = piece.movablePositions()
                .stream()
                .map(PositionDto::new)
                .collect(Collectors.toList());
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

    public List<PositionDto> getMovablePosition() {
        return movablePosition;
    }
}
