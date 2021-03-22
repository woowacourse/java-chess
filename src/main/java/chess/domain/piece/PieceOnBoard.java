package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.pieceinformations.PieceInformation;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.player.Score;
import chess.domain.position.Moves;
import chess.domain.position.Position;
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
    public State getState() {
        return state;
    }

    public Score getScore() {
        return new Score(pieceType.getScore());
    }

    @Override
    public void changePosition(Position end) {
        currentPosition = end;
    }

    @Override
    public String toString() {
        return teamColor.name() + "" + pieceType + "\n";
    }

    protected Set<Position> moveAsPossible(Moves direction, Position target,
        Map<Position, Piece> chessBoard) {
        Set<Position> candidates = new HashSet<>();
        Position position = direction.move(currentPosition);
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = direction.move(position);
        }
        return candidates;
    }


    protected Position moveOnce(Moves direction, Position target, Map<Position, Piece> chessBoard) {
        Position position = direction.move(currentPosition);
        if (movable(position, target, chessBoard)) {
            return position;
        }
        return Position.ERROR;
    }

    protected boolean movablePawn(Position target, Map<Position, Piece> chessBoard) {
        Set<Position> candidates = new HashSet<>();
        candidates.addAll(checkFront(chessBoard, currentPosition.moveFront(teamColor)));
        candidates.add(
            checkFrontDiagonal(target, chessBoard,
                currentPosition.moveFront(teamColor).moveRight()));
        candidates.add(
            checkFrontDiagonal(target, chessBoard,
                currentPosition.moveFront(teamColor).moveLeft()));
        return candidates.contains(target);
    }

    private Position checkFrontDiagonal(Position target, Map<Position, Piece> chessBoard,
        Position position) {
        if (isMeetEnemy(position, target, chessBoard)) {
            return position;
        }
        return Position.ERROR;
    }

    private Set<Position> checkFront(Map<Position, Piece> chessBoard, Position position) {
        Set<Position> candidates = new HashSet<>();
        if (validBlank(position, chessBoard)) {
            candidates.add(position);
            position = position.moveFront(teamColor);
            if (validBlank(position, chessBoard) && currentPosition.pawnLine(teamColor)) {
                candidates.add(position);
            }
        }
        return candidates;
    }


    private boolean movable(Position position, Position target, Map<Position, Piece> chessBoard) {
        return validBlank(position, chessBoard) || isMeetEnemy(position, target, chessBoard);
    }

    private boolean isMeetEnemy(Position position, Position target,
        Map<Position, Piece> chessBoard) {
        return position == target && isEnemyTeam(chessBoard.get(position));
    }

    private boolean validBlank(Position position, Map<Position, Piece> chessBoard) {
        return position != Position.ERROR && chessBoard.get(position) == Blank.INSTANCE;
    }

    private boolean isEnemyTeam(Piece comparePiece) {
        return teamColor != comparePiece.getColor();
    }

    //todo: 리팩토링중 컴파일 호환용
    protected Set<Position> moveAsPossible(Moves direction, Position target,
        ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();
        Position position = direction.move(currentPosition);
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = direction.move(position);
        }
        return candidates;
    }


    protected Position moveOnce(Moves direction, Position target, ChessBoard chessBoard) {
        Position position = direction.move(currentPosition);
        if (movable(position, target, chessBoard)) {
            return position;
        }
        return Position.ERROR;
    }

    protected boolean movablePawn(Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();
        candidates.addAll(checkFront(chessBoard, currentPosition.moveFront(teamColor)));
        candidates.add(
            checkFrontDiagonal(target, chessBoard,currentPosition.moveFront(teamColor).moveRight()));
        candidates.add(
            checkFrontDiagonal(target, chessBoard, currentPosition.moveFront(teamColor).moveLeft()));
        return candidates.contains(target);
    }

    private Position checkFrontDiagonal(Position target, ChessBoard chessBoard, Position position) {
        if (isMeetEnemy(position, target, chessBoard)) {
            return position;
        }
        return Position.ERROR;
    }

    private Set<Position> checkFront(ChessBoard chessBoard, Position position) {
        Set<Position> candidates = new HashSet<>();
        if (validBlank(position, chessBoard)) {
            candidates.add(position);
            position = position.moveFront(teamColor);
            if (validBlank(position, chessBoard) && currentPosition.pawnLine(teamColor)) {
                candidates.add(position);
            }
        }
        return candidates;
    }


    private boolean movable(Position position, Position target, ChessBoard chessBoard) {
        return validBlank(position, chessBoard) || isMeetEnemy(position, target, chessBoard);
    }

    private boolean isMeetEnemy(Position position, Position target,
        ChessBoard chessBoard) {
        final Piece piece = chessBoard.getPiece(position);
        return position == target && isEnemyTeam(piece);
    }

    private boolean validBlank(Position position, ChessBoard chessBoard) {
        return position != Position.ERROR && chessBoard.getPiece(position) == Blank.INSTANCE;
    }


}
