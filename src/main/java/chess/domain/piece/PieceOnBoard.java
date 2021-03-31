package chess.domain.piece;

import chess.domain.pieceinformations.PieceInformation;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Moves;
import chess.domain.position.Position;
import chess.domain.team.Score;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class PieceOnBoard implements Piece {
    private final TeamColor teamColor;
    private final PieceInformation pieceType;
    private State state;
    private Position currentPosition;

    public PieceOnBoard(TeamColor teamColor, PieceInformation pieceType) {
        this.teamColor = teamColor;
        this.pieceType = pieceType;
        this.state = State.ALIVE;
    }

    public PieceOnBoard(TeamColor teamColor, PieceInformation pieceType, Position currentPosition) {
        this.teamColor = teamColor;
        this.pieceType = pieceType;
        this.state = State.ALIVE;
        this.currentPosition = currentPosition;
    }

    @Override
    public String getPieceName() {
        if (teamColor == TeamColor.BLACK) {
            return pieceType.getName().toUpperCase();
        }
        return pieceType.getName().toLowerCase();
    }

    @Override
    public String getPieceType(){
        return pieceType.name();
    }

    @Override
    public TeamColor getColor() {
        return teamColor;
    }

    @Override
    public Character getColumn() {
        return currentPosition.getColumn();
    }

    @Override
    public void dead() {
        state = State.DEAD;
    }

    @Override
    public boolean isAlive() {
        return state == State.ALIVE;
    }

    public Score getScore() {
        return new Score(pieceType.getScore());
    }

    @Override
    public void changePosition(Position newPosition) {
        currentPosition = newPosition;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public String toString() {
        return "PieceOnBoard{" +
                "teamColor=" + teamColor +
                ", pieceType=" + pieceType +
                ", state=" + state +
                ", currentPosition=" + currentPosition +
                '}';
    }

    protected Set<Position> moveDiagonalAsPossible(Position target,
                                                   Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        candidates.addAll(moveAsPossible(Moves.LEFT_DOWN, target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.LEFT_UP, target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.RIGHT_DOWN, target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.RIGHT_UP, target, chessBoard));
        return candidates;
    }

    protected Set<Position> moveCrossAsPossible(Position target,
                                                Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        candidates.addAll(moveAsPossible(Moves.UP, target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.DOWN, target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.LEFT, target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.RIGHT, target, chessBoard));
        return candidates;
    }

    protected Position moveOnce(Moves direction, Position target, Map<Position, Piece> chessBoard) {
        Position position = direction.move(currentPosition);
        if (movable(position, target, chessBoard)) {
            return position;
        }
        return null;
    }

    protected Set<Position> checkFrontDiagonal(Position target, Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        Position positionDiagonalRight = Moves.RIGHT.move(currentPosition.moveFront(teamColor));
        Position positionDiagonalLeft = Moves.LEFT.move(currentPosition.moveFront(teamColor));
        if (isMeetEnemy(positionDiagonalRight, target, chessBoard)) {
            candidates.add(positionDiagonalRight);
        }
        if (isMeetEnemy(positionDiagonalLeft, target, chessBoard)) {
            candidates.add(positionDiagonalLeft);
        }
        return candidates;
    }

    protected Set<Position> checkFront(Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        Position position = currentPosition.moveFront(teamColor);
        if (validBlank(position, chessBoard)) {
            candidates.add(position);
            position = position.moveFront(teamColor);
            checkOnStartLine(chessBoard, candidates, position);
        }
        return candidates;
    }

    private void checkOnStartLine(Map<Position, Piece> chessBoard, Set<Position> candidates, Position position) {
        if (validBlank(position, chessBoard) && currentPosition.pawnLine(teamColor)) {
            candidates.add(position);
        }
    }

    private Set<Position> moveAsPossible(Moves direction, Position target, Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        Position position = direction.move(currentPosition);
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = direction.move(position);
        }
        return candidates;
    }

    private boolean movable(Position position, Position target, Map<Position, Piece> chessBoard) {
        return validBlank(position, chessBoard) || isMeetEnemy(position, target, chessBoard);
    }

    private boolean isMeetEnemy(Position position, Position target, Map<Position, Piece> chessBoard) {
        return position == target && isEnemyTeam(chessBoard.get(position));
    }

    private boolean validBlank(Position position, Map<Position, Piece> chessBoard) {
        return position != null && chessBoard.get(position) == Blank.INSTANCE;
    }

    private boolean isEnemyTeam(Piece comparePiece) {
        return teamColor != comparePiece.getColor();
    }

}
