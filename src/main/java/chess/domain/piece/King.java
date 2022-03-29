package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {
    private static final String NAME = "KING";
    private static final double SCORE = 0;
    private static final List<ChessPiece> blackTeamKing = new ArrayList<>();
    private static final List<ChessPiece> whiteTeamKing = new ArrayList<>();

    static {
        blackTeamKing.add(new King(Team.BLACK, new ChessBoardPosition('e', 8)));

        whiteTeamKing.add(new King(Team.WHITE, new ChessBoardPosition('e', 1)));
    }

    public static List<ChessPiece> create(Team team) {
        if (team.isBlack()) {
            return new ArrayList<>(blackTeamKing);
        }
        return new ArrayList<>(whiteTeamKing);
    }

    public King(Team team, ChessBoardPosition position) {
        super(NAME, SCORE, team, position);
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return Math.abs(highRow - lowRow);
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return Math.abs(highColumn - lowColumn);
    }

    private boolean isKingMovement(int rowDistance, int columnDistance) {
        return isFourCardinalMovement(rowDistance, columnDistance)
                || isFourDiagonalCardinalMovement(rowDistance, columnDistance);
    }

    private boolean isFourCardinalMovement(int rowDistance, int columnDistance) {
        return (rowDistance == 1 && columnDistance == 0) || (columnDistance == 1 && rowDistance == 0);
    }

    private boolean isFourDiagonalCardinalMovement(int rowDistance, int columnDistance) {
        return rowDistance == 1 && columnDistance == 1;
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
        return isKingMovement(rowDistance, columnDistance);
    }
}
