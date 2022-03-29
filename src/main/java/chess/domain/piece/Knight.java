package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {
    private static final String NAME = "KNIGHT";
    private static final double SCORE = 2.5;
    private static final List<ChessPiece> blackTeamKnight = new ArrayList<>();
    private static final List<ChessPiece> whiteTeamKnight = new ArrayList<>();

    static {
        blackTeamKnight.add(new Knight(Team.BLACK, new ChessBoardPosition('b', 8)));
        blackTeamKnight.add(new Knight(Team.BLACK, new ChessBoardPosition('g', 8)));

        whiteTeamKnight.add(new Knight(Team.WHITE, new ChessBoardPosition('b', 1)));
        whiteTeamKnight.add(new Knight(Team.WHITE, new ChessBoardPosition('g', 1)));
    }

    public static List<ChessPiece> create(Team team) {
        if (team.isBlack()) {
            return new ArrayList<>(blackTeamKnight);
        }
        return new ArrayList<>(whiteTeamKnight);
    }

    public Knight(Team team, ChessBoardPosition position) {
        super(NAME, SCORE, team, position);
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return Math.abs(highRow - lowRow);
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return Math.abs(highColumn - lowColumn);
    }

    public boolean isSamePosition(ChessBoardPosition nextPosition) {
        return position.equals(nextPosition);
    }

    @Override
    public void move(ChessBoardPosition targetPosition) {
        position = targetPosition;
    }

    @Override
    public boolean isMovable(ChessBoardPosition targetPosition, ChessMen whiteChessMen, ChessMen blackChessMen) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        return (rowDistance == 2 && columnDistance == 1) || (rowDistance == 1 && columnDistance == 2);
    }
}
