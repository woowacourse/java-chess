package chess.domain.board;

import chess.domain.location.Column;
import chess.domain.location.Location;
import chess.domain.location.Row;
import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Location, Piece> board;

    public Board() {
        this.board = initialBoard();
    }

    private Map<Location, Piece> initialBoard() {
        Map<Location, Piece> initialBoard = new HashMap<>();
        initialPawnSetting(initialBoard);
        initialRookSetting(initialBoard);
        initialKnightSetting(initialBoard);
        initialBishopSetting(initialBoard);
        initialQueenSetting(initialBoard);
        initialKingSetting(initialBoard);
        return initialBoard;
    }

    private void initialPawnSetting(Map<Location, Piece> board) {
        for (Column value : Column.values()) {
            board.put(new Location(value, Row.TWO), new WhitePawn());
        }

        for (Column value : Column.values()) {
            board.put(new Location(value, Row.SEVEN), new BlackPawn());
        }
    }

    private void initialRookSetting(Map<Location, Piece> board) {
        board.put(new Location(Column.A, Row.ONE), new Rook(Color.WHITE));
        board.put(new Location(Column.A, Row.EIGHT), new Rook(Color.BLACK));
        board.put(new Location(Column.H, Row.ONE), new Rook(Color.WHITE));
        board.put(new Location(Column.H, Row.EIGHT), new Rook(Color.BLACK));
    }

    private void initialKnightSetting(Map<Location, Piece> board) {
        board.put(new Location(Column.B, Row.ONE), new Knight(Color.WHITE));
        board.put(new Location(Column.B, Row.EIGHT), new Knight(Color.BLACK));
        board.put(new Location(Column.G, Row.ONE), new Knight(Color.WHITE));
        board.put(new Location(Column.G, Row.EIGHT), new Knight(Color.BLACK));
    }

    private void initialBishopSetting(Map<Location, Piece> board) {
        board.put(new Location(Column.C, Row.ONE), new Bishop(Color.WHITE));
        board.put(new Location(Column.C, Row.EIGHT), new Bishop(Color.BLACK));
        board.put(new Location(Column.F, Row.ONE), new Bishop(Color.WHITE));
        board.put(new Location(Column.F, Row.EIGHT), new Bishop(Color.BLACK));
    }

    private void initialQueenSetting(Map<Location, Piece> board) {
        board.put(new Location(Column.D, Row.ONE), new Queen(Color.WHITE));
        board.put(new Location(Column.D, Row.EIGHT), new Queen(Color.BLACK));
    }

    private void initialKingSetting(Map<Location, Piece> board) {
        board.put(new Location(Column.E, Row.ONE), new King(Color.WHITE));
        board.put(new Location(Column.E, Row.EIGHT), new King(Color.BLACK));
    }

    public Map<Location, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public void tryMove(Location source, Location target) {
        Piece selectedPiece = findPieceAt(source);
        Path path = createPath(source, target);
        if (selectedPiece.canMove(path)) {
            selectedPiece.move();
            updateLocation(source, target, selectedPiece);
            return;
        }
        throw new IllegalArgumentException("유효하지 않은 움직임입니다.");
    }

    private void updateLocation(Location source, Location target, Piece movingPiece) {
        board.remove(source);
        board.put(target, movingPiece);
    }

    private Path createPath(Location source, Location target) {
        List<Direction> directions = Direction.createDirections(source, target);
        List<LocationState> locationStates = createPathState(source, directions);
        return Path.of(directions, locationStates);
    }

    private List<LocationState> createPathState(Location current, List<Direction> directions) {
        Piece movingPiece = findPieceAt(current);
        List<LocationState> locationStates = new ArrayList<>();
        for (Direction direction : directions) {
            current = current.move(direction);
            locationStates.add(findLocationStates(movingPiece, current));
        }
        return locationStates;
    }

    private LocationState findLocationStates(Piece movingPiece, Location current) {
        Piece locatedPiece = board.get(current);
        if (locatedPiece == null) {
            return LocationState.EMPTY;
        }
        if (movingPiece.isAlly(locatedPiece)) {
            return LocationState.ALLY;
        }
        return LocationState.ENEMY;
    }

    private Piece findPieceAt(Location source) {
        Piece piece = board.get(source);
        if (piece == null) {
            throw new IllegalArgumentException("말이 존재하지 않습니다.");
        }
        return piece;
    }
}
