package chess.board;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> pieceByPosition;
    private boolean isEnd;

    public ChessBoard() {
        Map<Position, Piece> pieceByPosition = new HashMap<>();
        initializeChessBoard(pieceByPosition);
        this.pieceByPosition = pieceByPosition;
        this.isEnd = false;
    }

    public boolean existsPiece(Position position) {
        return pieceByPosition.containsKey(position);
    }

    public boolean existsPieceInPath(Position source, Movement movement) {
        if (!source.canMoveByDirections(movement)) {
            return false;
        }
        Position currentPosition = source;
        for (Direction direction : movement.path()) {
            if (!currentPosition.canMoveByDirection(direction)) {
                return false;
            }
            Position nextPosition = currentPosition.moveByDirection(direction);
            if (existsPiece(nextPosition)) {
                return false;
            }
            currentPosition = currentPosition.moveByDirection(direction);
        }
        return true;
    }

    public boolean equalsByPosition(Position position, Piece piece) {
        if (!existsPiece(position)) {
            return false;
        }
        return piece == pieceByPosition.get(position);
    }

    public boolean hasProperTeam(Position source, Team team) {
        if (!existsPiece(source)) {
            return false;
        }
        Piece piece = pieceByPosition.get(source);
        return piece.team() == team;
    }

    public boolean canMove(Position source, Position destination) {
        if (!existsPiece(source)) {
            return false;
        }
        Piece piece = pieceByPosition.get(source);
        return piece.canMove(source, destination, this);
    }

    public boolean canAttack(Position source, Position destination) {
        if (!existsPiece(source)) {
            return false;
        }
        Piece piece = pieceByPosition.get(source);
        return piece.canAttack(source, destination, this);
    }

    public void move(Position source, Position destination) {
        if (!existsPiece(source)) {
            throw new IllegalArgumentException(source + ": 위치에 기물이 없습니다.");
        }
        Piece piece = pieceByPosition.get(source);
        if (!piece.canMove(source, destination, this)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }

        pieceByPosition.remove(source, piece);
        pieceByPosition.put(destination, piece);
        piece.moved();
        System.out.println(piece.type().getTitleByTeam(piece.team()) + ": 빈 곳으로 이동했당");
    }

    public void attack(Position source, Position destination) {
        if (!existsPiece(source)) {
            throw new IllegalArgumentException(source + ": 위치에 기물이 없습니다.");
        }
        Piece piece = pieceByPosition.get(source);
        if (!piece.canAttack(source, destination, this)) {
            throw new IllegalArgumentException("공격할 수 없습니다.");
        }
        Piece attacked = pieceByPosition.get(destination);
        if (attacked.type() == PieceType.KING) {
            isEnd = true;
        }

        pieceByPosition.remove(source, piece);
        pieceByPosition.put(destination, piece);
        piece.moved();
        System.out.println(
                piece.type().getTitleByTeam(piece.team()) + ": " +
                        attacked.type().getTitleByTeam(piece.team()) + " 먹었당");
    }

    public boolean isEnd() {
        return isEnd;
    }

    public Map<Position, Piece> getPieceByPosition() {
        return pieceByPosition;
    }

    private void initializeChessBoard(Map<Position, Piece> pieceByPosition) {
        pieceByPosition.put(new Position(1, 1), new Rook(Team.WHITE));
        pieceByPosition.put(new Position(1, 2), new Knight(Team.WHITE));
        pieceByPosition.put(new Position(1, 3), new Bishop(Team.WHITE));
        pieceByPosition.put(new Position(1, 4), new Queen(Team.WHITE));
        pieceByPosition.put(new Position(1, 5), new King(Team.WHITE));
        pieceByPosition.put(new Position(1, 6), new Bishop(Team.WHITE));
        pieceByPosition.put(new Position(1, 7), new Knight(Team.WHITE));
        pieceByPosition.put(new Position(1, 8), new Rook(Team.WHITE));

        pieceByPosition.put(new Position(2, 1), new Pawn(Team.WHITE));
        pieceByPosition.put(new Position(2, 2), new Pawn(Team.WHITE));
        pieceByPosition.put(new Position(2, 3), new Pawn(Team.WHITE));
        pieceByPosition.put(new Position(2, 4), new Pawn(Team.WHITE));
        pieceByPosition.put(new Position(2, 5), new Pawn(Team.WHITE));
        pieceByPosition.put(new Position(2, 6), new Pawn(Team.WHITE));
        pieceByPosition.put(new Position(2, 7), new Pawn(Team.WHITE));
        pieceByPosition.put(new Position(2, 8), new Pawn(Team.WHITE));

        pieceByPosition.put(new Position(8, 1), new Rook(Team.BLACK));
        pieceByPosition.put(new Position(8, 2), new Knight(Team.BLACK));
        pieceByPosition.put(new Position(8, 3), new Bishop(Team.BLACK));
        pieceByPosition.put(new Position(8, 4), new Queen(Team.BLACK));
        pieceByPosition.put(new Position(8, 5), new King(Team.BLACK));
        pieceByPosition.put(new Position(8, 6), new Bishop(Team.BLACK));
        pieceByPosition.put(new Position(8, 7), new Knight(Team.BLACK));
        pieceByPosition.put(new Position(8, 8), new Rook(Team.BLACK));

        pieceByPosition.put(new Position(7, 1), new Pawn(Team.BLACK));
        pieceByPosition.put(new Position(7, 2), new Pawn(Team.BLACK));
        pieceByPosition.put(new Position(7, 3), new Pawn(Team.BLACK));
        pieceByPosition.put(new Position(7, 4), new Pawn(Team.BLACK));
        pieceByPosition.put(new Position(7, 5), new Pawn(Team.BLACK));
        pieceByPosition.put(new Position(7, 6), new Pawn(Team.BLACK));
        pieceByPosition.put(new Position(7, 7), new Pawn(Team.BLACK));
        pieceByPosition.put(new Position(7, 8), new Pawn(Team.BLACK));
    }
}
