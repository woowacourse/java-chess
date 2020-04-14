package chess;

import chess.exception.EmptySourceException;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;
import chess.position.Direction;
import chess.position.Position;
import chess.strategy.PiecesInitStrategy;

import java.util.List;
import java.util.Map;

import static chess.piece.Team.NONE;
import static chess.piece.Team.WHITE;

public class Board {
    private final Map<Position, Piece> pieces;
    private Team turn;
    private boolean isFinished;

    public Board(Map<Position, Piece> pieces, Team turn, boolean isFinished) {
        this.pieces = pieces;
        this.turn = turn;
        this.isFinished = isFinished;
    }

    public Board(Map<Position, Piece> pieces, Team turn) {
        this(pieces, turn, false);
    }

    public Board(Map<Position, Piece> pieces) {
        this(pieces, WHITE, false);
    }

    public Board(PiecesInitStrategy piecesInitStrategy, Team turn) {
        this(piecesInitStrategy.init(), turn, false);
    }

    public Board(PiecesInitStrategy piecesInitStrategy) {
        this(piecesInitStrategy.init(), WHITE, false);
    }

    public void moveIfPossible(Position source, Position target) {
        Piece pieceToBeMoved = pieces.get(source);
        if (pieceToBeMoved.isEmpty()) {
            throw new EmptySourceException(source.getKey());
        }
        pieceToBeMoved.throwExceptionIfNotMovable(this, source, target);
        finishIfKilledEnemyKing(target);
        move(source, target);
        pieceToBeMoved.updateHasMoved();
        this.turn = turn.getOppositeTeam();
    }

    private void finishIfKilledEnemyKing(Position target) {
        if (isExistAt(target) && isEnemyKing(target)) {
            this.isFinished = true;
        }
    }

    public boolean isExistAt(Position position) {
        return !pieces.get(position).isEmpty();
    }

    private boolean isEnemyKing(Position position) {
        return getTeamOf(position).isNotSame(this.turn) && pieces.get(position).isKing();
    }

    public void move(Position source, Position target) {
        Piece piece = pieces.get(source);
        pieces.put(source, new Piece(NONE, PieceType.NONE));
        pieces.put(target, piece);
    }

    public boolean isExistAnyPieceAt(List<Position> traces) {
        return traces.stream()
                .anyMatch(this::isExistAt);
    }

    public int forwardMoveAmountOfRank(Position source, Position target) {
        int increaseAmountOfRank = source.increaseAmountOfRank(target);
        return isWhite(source) ? increaseAmountOfRank : -1 * increaseAmountOfRank;
    }

    private boolean isFrontLeft(Position source, Position target) {
        return target == frontLeftOf(source);
    }

    private boolean isFrontRight(Position source, Position target) {
        return target == frontRightOf(source);
    }

    private Position frontLeftOf(Position position) {
        if (turn.isWhite()) {
            return position.at(Direction.NORTH_WEST);
        }
        return position.at(Direction.SOUTH_EAST);
    }

    private Position frontRightOf(Position position) {
        if (turn.isWhite()) {
            return position.at(Direction.NORTH_EAST);
        }
        return position.at(Direction.SOUTH_WEST);
    }

    public boolean hasMoved(Position position) {
        return pieces.get(position).getHasMoved();
    }

    public boolean isExistEnemyFrontLeft(Position source, Position target) {
        return isExistAt(target)
                && isFrontLeft(source, target)
                && isNotSameTeamBetween(source, target);
    }

    public boolean isExistEnemyFrontRight(Position source, Position target) {
        return isExistAt(target)
                && isFrontRight(source, target)
                && isNotSameTeamBetween(source, target);
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public boolean isNotFinished() {
        return !isFinished();
    }

    public boolean isNotTurnOf(Position position) {
        return getTeamOf(position).isNotSame(this.turn);
    }

    private Team getTeamOf(Position position) {
        return pieces.get(position).getTeam();
    }

    public boolean isSameTeamBetween(Position position1, Position position2) {
        Piece piece1 = pieces.get(position1);
        Piece piece2 = pieces.get(position2);
        return piece1.isSameTeam(piece2);
    }

    public boolean isSameTeamBetween(Team team, Piece piece) {
        return piece.isSameTeam(team);
    }

    public boolean isNotSameTeamBetween(Position position1, Position position2) {
        return !isSameTeamBetween(position1, position2);
    }

    private boolean isWhite(Position position) {
        return pieces.get(position).isWhite();
    }

    public void finishGame() {
        this.isFinished = true;
    }

    public Map<Position, Piece> getPieces() {
        return this.pieces;
    }

    public Piece getPiece(Position position) {
        return pieces.get(position);
    }

    public Team getTurn() {
        return this.turn;
    }
}
