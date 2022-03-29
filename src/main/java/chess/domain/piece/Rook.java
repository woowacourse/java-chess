package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {
    private static final int NO_DIFFERENCE = 0;
    private static final String NAME = "ROOK";
    private static final double SCORE = 5.0;
    private static final List<ChessPiece> blackTeamRook = new ArrayList<>();
    private static final List<ChessPiece> whiteTeamRook = new ArrayList<>();

    static {
        blackTeamRook.add(new Rook(Team.BLACK, new ChessBoardPosition('a', 8)));
        blackTeamRook.add(new Rook(Team.BLACK, new ChessBoardPosition('h', 8)));

        whiteTeamRook.add(new Rook(Team.WHITE, new ChessBoardPosition('a', 1)));
        whiteTeamRook.add(new Rook(Team.WHITE, new ChessBoardPosition('h', 1)));
    }

    public static List<ChessPiece> create(Team team) {
        if (team.isBlack()) {
            return new ArrayList<>(blackTeamRook);
        }
        return new ArrayList<>(whiteTeamRook);
    }

    public Rook(Team team, ChessBoardPosition position) {
        super(NAME, SCORE, team, position);
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
        return isReachable(targetPosition) && isUnobstructed(targetPosition, whiteChessMen, blackChessMen);
    }

    private boolean isReachable(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        return rowDistance == NO_DIFFERENCE || columnDistance == NO_DIFFERENCE;
    }

    private boolean isUnobstructed(ChessBoardPosition targetChessBoardPosition, ChessMen whiteChessMen,
                                   ChessMen blackChessMen) {
        return noChessMenInPath(targetChessBoardPosition, whiteChessMen)
                && noChessMenInPath(targetChessBoardPosition, blackChessMen);
    }

    private boolean noChessMenInPath(ChessBoardPosition targetChessBoardPosition, ChessMen chessMen) {
        return createPathPositions(targetChessBoardPosition)
                .stream()
                .noneMatch(chessMen::existChessPieceAt);
    }

    private List<ChessBoardPosition> createPathPositions(ChessBoardPosition targetChessBoardPosition) {
        int rowUnitChange = calculateUnitChange(targetChessBoardPosition.getRow(), position.getRow());
        int columnUnitChange = calculateUnitChange(targetChessBoardPosition.getColumn(), position.getColumn());
        List<ChessBoardPosition> pathPositions = new ArrayList<>();
        ChessBoardPosition currentBoardPosition = position.move(columnUnitChange, rowUnitChange);
        while (!currentBoardPosition.equals(targetChessBoardPosition)) {
            pathPositions.add(currentBoardPosition);
            currentBoardPosition = currentBoardPosition.move(columnUnitChange, rowUnitChange);
        }
        return pathPositions;
    }

    private int calculateUnitChange(int source, int target) {
        if (source == target) {
            return 0;
        }
        return (source - target) / Math.abs(source - target);
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return highRow - lowRow;
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return highColumn - lowColumn;
    }
}
