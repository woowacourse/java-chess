package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    private static final int DEFAULT_DISTANCE = 1;
    private static final int OPTIONAL_DISTANCE = 1;
    private static final int WHITE_INITIAL_ROW_POSITION = 2;
    private static final int BLACK_INITIAL_ROW_POSITION = 7;
    private static final String NAME = "PAWN";
    private static final double SCORE = 1;
    private static final List<ChessPiece> blackTeamPawn = new ArrayList<>();
    private static final List<ChessPiece> whiteTeamPawn = new ArrayList<>();

    static {
        blackTeamPawn.add(new Pawn(Team.BLACK, new ChessBoardPosition('a', 7)));
        blackTeamPawn.add(new Pawn(Team.BLACK, new ChessBoardPosition('b', 7)));
        blackTeamPawn.add(new Pawn(Team.BLACK, new ChessBoardPosition('c', 7)));
        blackTeamPawn.add(new Pawn(Team.BLACK, new ChessBoardPosition('d', 7)));
        blackTeamPawn.add(new Pawn(Team.BLACK, new ChessBoardPosition('e', 7)));
        blackTeamPawn.add(new Pawn(Team.BLACK, new ChessBoardPosition('f', 7)));
        blackTeamPawn.add(new Pawn(Team.BLACK, new ChessBoardPosition('g', 7)));
        blackTeamPawn.add(new Pawn(Team.BLACK, new ChessBoardPosition('h', 7)));

        whiteTeamPawn.add(new Pawn(Team.WHITE, new ChessBoardPosition('a', 2)));
        whiteTeamPawn.add(new Pawn(Team.WHITE, new ChessBoardPosition('b', 2)));
        whiteTeamPawn.add(new Pawn(Team.WHITE, new ChessBoardPosition('c', 2)));
        whiteTeamPawn.add(new Pawn(Team.WHITE, new ChessBoardPosition('d', 2)));
        whiteTeamPawn.add(new Pawn(Team.WHITE, new ChessBoardPosition('e', 2)));
        whiteTeamPawn.add(new Pawn(Team.WHITE, new ChessBoardPosition('f', 2)));
        whiteTeamPawn.add(new Pawn(Team.WHITE, new ChessBoardPosition('g', 2)));
        whiteTeamPawn.add(new Pawn(Team.WHITE, new ChessBoardPosition('h', 2)));
    }

    public static List<ChessPiece> create(Team team) {
        if (team.isBlack()) {
            return new ArrayList<>(blackTeamPawn);
        }
        return new ArrayList<>(whiteTeamPawn);
    }

    public Pawn(Team team, ChessBoardPosition position) {
        super(NAME, SCORE, team, position);
    }

    private boolean isWhiteInitialPosition() {
        return position.isSameRow(WHITE_INITIAL_ROW_POSITION);
    }

    private boolean isBlackInitialPosition() {
        return position.isSameRow(BLACK_INITIAL_ROW_POSITION);
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return highRow - lowRow;
    }

    public boolean isSamePosition(ChessBoardPosition anotherPosition) {
        return position.equals(anotherPosition);
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return Math.abs(highColumn - lowColumn);
    }

    @Override
    public void move(ChessBoardPosition targetPosition) {
        position = targetPosition;
    }

    @Override
    public boolean isMovable(ChessBoardPosition targetPosition, ChessMen whiteChessMen, ChessMen blackChessMen) {
        if (isDiagonalMove(targetPosition)) {
            return enemyExistsInTargetPosition(targetPosition, getEnemy(whiteChessMen, blackChessMen));
        }

        if (team.isWhite()) {
            return isMovableWhite(targetPosition, whiteChessMen, blackChessMen);
        }

        return isMovableBlack(targetPosition, whiteChessMen, blackChessMen);
    }

    private boolean isDiagonalMove(ChessBoardPosition targetPosition) {
        if (team.isWhite()) {
            return isDiagonalMoveWhite(targetPosition);
        }
        return isDiagonalMoveBlack(targetPosition);
    }

    private boolean isDiagonalMoveBlack(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        return rowDistance == DEFAULT_DISTANCE && columnDistance == DEFAULT_DISTANCE;
    }

    private boolean isDiagonalMoveWhite(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(targetPosition.getRow(), position.getRow());
        int columnDistance = calculateColumnDistance(targetPosition.getColumn(), position.getColumn());
        return rowDistance == DEFAULT_DISTANCE && columnDistance == DEFAULT_DISTANCE;
    }

    private ChessMen getEnemy(ChessMen whiteChessMen, ChessMen blackChessMen) {
        if (team.isWhite()) {
            return blackChessMen;
        }
        return whiteChessMen;
    }

    private boolean isMovableBlack(ChessBoardPosition targetPosition, ChessMen whiteChessMen, ChessMen blackChessMen) {
        if (isBlackInitialPosition()) {
            return isMovableBlackInitialPosition(targetPosition, whiteChessMen, blackChessMen);
        }
        return calculateRowDistance(position.getRow(), targetPosition.getRow()) == DEFAULT_DISTANCE;
    }

    private boolean isMovableBlackInitialPosition(ChessBoardPosition targetPosition, ChessMen whiteChessMen,
                                                  ChessMen blackChessMen) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        if (rowDistance == DEFAULT_DISTANCE + OPTIONAL_DISTANCE) {
            return isUnobstructed(whiteChessMen, blackChessMen, -DEFAULT_DISTANCE);
        }
        return rowDistance == DEFAULT_DISTANCE;
    }

    private boolean isMovableWhite(ChessBoardPosition targetPosition, ChessMen whiteChessMen, ChessMen blackChessMen) {
        if (isWhiteInitialPosition()) {
            return isMovableWhiteInitialPosition(targetPosition, whiteChessMen, blackChessMen);
        }
        return calculateRowDistance(targetPosition.getRow(), position.getRow()) == DEFAULT_DISTANCE;
    }

    private boolean isMovableWhiteInitialPosition(ChessBoardPosition targetPosition, ChessMen whiteChessMen,
                                                  ChessMen blackChessMen) {
        int rowDistance = calculateRowDistance(targetPosition.getRow(), position.getRow());
        if (rowDistance == DEFAULT_DISTANCE + OPTIONAL_DISTANCE) {
            return isUnobstructed(whiteChessMen, blackChessMen, DEFAULT_DISTANCE);
        }
        return rowDistance == DEFAULT_DISTANCE;
    }

    private boolean isUnobstructed(ChessMen whiteChessMen, ChessMen blackChessMen, int forwardDirection) {
        ChessBoardPosition pathPosition = new ChessBoardPosition(position.getColumn(),
                position.getRow() + forwardDirection);
        return !whiteChessMen.existChessPieceAt(pathPosition) && !blackChessMen.existChessPieceAt(pathPosition);
    }
}
