package chess.domain.piece;

import chess.domain.pieceinformations.PieceInformation;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Moves;
import chess.domain.position.Position;
import chess.domain.state.Score;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class PieceOnBoard implements Piece {
    private final TeamColor teamColor;
    private final PieceInformation pieceType;

    private State state;

    public PieceOnBoard(TeamColor teamColor, PieceInformation pieceType) {
        this.teamColor = teamColor;
        this.pieceType = pieceType;
        this.state = State.ALIVE;
    }

    public PieceOnBoard(TeamColor teamColor, PieceInformation pieceType, State state) {
        this.teamColor = teamColor;
        this.pieceType = pieceType;
        this.state = state;
    }

    @Override
    public String getPieceName() {
        if (teamColor == TeamColor.BLACK) {
            return pieceType.getName().toUpperCase();
        }
        return pieceType.getName().toLowerCase();
    }

    @Override
    public String getPieceType() {
        return pieceType.name();
    }

    @Override
    public TeamColor getColor() {
        return teamColor;
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
                '}';
    }

    protected Set<Position> moveDiagonalAsPossible(Position source, Position target,
                                                   Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        candidates.addAll(moveAsPossible(source, Moves.LEFT_DOWN, target, chessBoard));
        candidates.addAll(moveAsPossible(source, Moves.LEFT_UP, target, chessBoard));
        candidates.addAll(moveAsPossible(source, Moves.RIGHT_DOWN, target, chessBoard));
        candidates.addAll(moveAsPossible(source, Moves.RIGHT_UP, target, chessBoard));
        return candidates;
    }

    protected Set<Position> moveCrossAsPossible(Position source, Position target,
                                                Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        candidates.addAll(moveAsPossible(source, Moves.UP, target, chessBoard));
        candidates.addAll(moveAsPossible(source, Moves.DOWN, target, chessBoard));
        candidates.addAll(moveAsPossible(source, Moves.LEFT, target, chessBoard));
        candidates.addAll(moveAsPossible(source, Moves.RIGHT, target, chessBoard));
        return candidates;
    }

    protected Position moveOnce(Position source, Moves direction, Position target, Map<Position, Piece> chessBoard) {
        Position position = direction.move(source);
        if (movable(position, target, chessBoard)) {
            return position;
        }
        return null;
    }

    protected Set<Position> checkFrontDiagonal(Position source, Position target, Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        Position positionDiagonalRight = Moves.RIGHT.move(source.moveFront(teamColor));
        Position positionDiagonalLeft = Moves.LEFT.move(source.moveFront(teamColor));
        if (isMeetEnemy(positionDiagonalRight, target, chessBoard)) {
            candidates.add(positionDiagonalRight);
        }
        if (isMeetEnemy(positionDiagonalLeft, target, chessBoard)) {
            candidates.add(positionDiagonalLeft);
        }
        return candidates;
    }

    protected Set<Position> checkFront(Position source, Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        Position position = source.moveFront(teamColor);
        if (validBlank(position, chessBoard)) {
            candidates.add(position);
            position = position.moveFront(teamColor);
            checkOnStartLine(source, chessBoard, candidates, position);
        }
        return candidates;
    }

    protected void checkOnStartLine(Position source, Map<Position, Piece> chessBoard, Set<Position> candidates, Position position) {
        if (validBlank(position, chessBoard) && source.pawnLine(teamColor)) {
            candidates.add(position);
        }
    }

    private Set<Position> moveAsPossible(Position source, Moves direction, Position target, Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        Position position = direction.move(source);
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
        return teamColor.counterpart() == comparePiece.getColor();
    }

}
