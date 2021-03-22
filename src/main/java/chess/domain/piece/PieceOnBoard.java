package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.result.Score;
import chess.domain.state.State;
import chess.domain.state.TeamColor;
import java.util.HashSet;
import java.util.Set;

public abstract class PieceOnBoard implements Piece {

    private final TeamColor teamColor;
    private final PieceInformation pieceType;
    private State state;
    private Position position;

    public PieceOnBoard(TeamColor teamColor, PieceInformation pieceType) {
        this(teamColor, pieceType, Position.ERROR);
    }

    public PieceOnBoard(TeamColor teamColor, PieceInformation pieceType, Position position) {
        this.teamColor = teamColor;
        this.pieceType = pieceType;
        this.state = State.ALIVE;
        this.position = position;
    }

    public boolean movable(Position position, Position target, ChessBoard chessBoard) {
        return validBlank(position, chessBoard) || isMeetEnemy(position, target, chessBoard);
    }

    protected boolean isMeetEnemy(Position position, Position target,
        ChessBoard chessBoard) {
        return position == target && isEnemyTeam(chessBoard.getPiece(position));
    }

    protected boolean validBlank(Position position, ChessBoard chessBoard) {
        return position != Position.ERROR && chessBoard.isBlank(position);
    }


    protected Set<Position> moveDiagonal(Position source, Position target, ChessBoard chessBoard) {

        Set<Position> candidates = new HashSet<>();

        candidates.addAll(possibleLeftDown(source, target, chessBoard));
        candidates.addAll(possibleLeftUP(source, target, chessBoard));
        candidates.addAll(possibleRightDown(source, target, chessBoard));
        candidates.addAll(possibleRightUp(source, target, chessBoard));
        return candidates;
    }

    private Set<Position> possibleRightUp(Position source, Position target,
        ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position = source.moveRightUp();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveRightUp();
        }
        return candidates;
    }

    private Set<Position> possibleRightDown(Position source, Position target,
        ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position = source.moveRightDown();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveRightDown();
        }
        return candidates;
    }

    private Set<Position> possibleLeftUP(Position source, Position target,
        ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position = source.moveLeftUp();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveLeftUp();
        }
        return candidates;
    }

    private Set<Position> possibleLeftDown(Position source, Position target,
        ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position = source.moveLeftDown();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveLeftDown();
        }
        return candidates;
    }

    protected Set<Position> moveCross(Position source, Position target, ChessBoard chessBoard) {

        Set<Position> candidates = new HashSet<>();

        candidates.addAll(possibleUp(source, target, chessBoard));
        candidates.addAll(possibleDown(source, target, chessBoard));
        candidates.addAll(possibleLeft(source, target, chessBoard));
        candidates.addAll(possibleRight(source, target, chessBoard));
        return candidates;
    }

    private Set<Position> possibleUp(Position source, Position target,
        ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position = source.moveUp();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveUp();
        }
        return candidates;
    }

    private Set<Position> possibleDown(Position source, Position target,
        ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position = source.moveDown();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveDown();
        }
        return candidates;
    }

    private Set<Position> possibleLeft(Position source, Position target,
        ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position = source.moveLeft();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveLeft();
        }
        return candidates;
    }

    private Set<Position> possibleRight(Position source, Position target,
        ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position = source.moveRight();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveRight();
        }
        return candidates;
    }

    @Override
    public boolean isEnemyTeam(Piece comparePiece) {
        return teamColor != comparePiece.getColor();
    }

    @Override
    public TeamColor getColor() {
        return teamColor;
    }

    @Override
    public Character getColumn() {
        return position.getColumn();
    }

    @Override
    public void dead() {
        state = State.DEAD;
    }

    @Override
    public State getState() {
        return state;
    }

    public Position getPosition() {
        return position;
    }

    public Score getScore() {
        return new Score(pieceType.getScore());
    }

    @Override
    public String getPieceName() {
        if (teamColor == TeamColor.BLACK) {
            return pieceType.getName().toUpperCase();
        }
        return pieceType.getName().toLowerCase();
    }

    @Override
    public void setPosition(Position end) {
        position = end;
    }

    @Override
    public String toString() {
        return teamColor.name() + "" + pieceType + "\n";
    }

}
