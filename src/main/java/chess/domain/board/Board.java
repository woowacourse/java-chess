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
        this.board = new HashMap<>();
        initialSetting();
    }

    private void initialSetting() {
        initialPawnSetting();
        initialRookSetting();
        initialKnightSetting();
        initialBishopSetting();
        initialQueenSetting();
        initialKingSetting();
    }


    private void initialPawnSetting() {
        for (Column value : Column.values()) {
            board.put(new Location(value, Row.TWO), new WhitePawn(Color.WHITE));
        }
        for (Column value : Column.values()) {
            board.put(new Location(value, Row.SEVEN), new BlackPawn(Color.BLACK));
        }
    }

    private void initialRookSetting() {
        board.put(new Location(Column.A, Row.ONE), new Rook(Color.WHITE));
        board.put(new Location(Column.A, Row.EIGHT), new Rook(Color.BLACK));
        board.put(new Location(Column.H, Row.ONE), new Rook(Color.WHITE));
        board.put(new Location(Column.H, Row.EIGHT), new Rook(Color.BLACK));
    }

    private void initialKnightSetting() {
        board.put(new Location(Column.B, Row.ONE), new Knight(Color.WHITE));
        board.put(new Location(Column.B, Row.EIGHT), new Knight(Color.BLACK));
        board.put(new Location(Column.G, Row.ONE), new Knight(Color.WHITE));
        board.put(new Location(Column.G, Row.EIGHT), new Knight(Color.BLACK));
    }

    private void initialBishopSetting() {
        board.put(new Location(Column.C, Row.ONE), new Bishop(Color.WHITE));
        board.put(new Location(Column.C, Row.EIGHT), new Bishop(Color.BLACK));
        board.put(new Location(Column.F, Row.ONE), new Bishop(Color.WHITE));
        board.put(new Location(Column.F, Row.EIGHT), new Bishop(Color.BLACK));
    }

    private void initialQueenSetting() {
        board.put(new Location(Column.D, Row.ONE), new Queen(Color.WHITE));
        board.put(new Location(Column.D, Row.EIGHT), new Queen(Color.BLACK));
    }

    private void initialKingSetting() {
        board.put(new Location(Column.E, Row.ONE), new King(Color.WHITE));
        board.put(new Location(Column.E, Row.EIGHT), new King(Color.BLACK));
    }

    public Map<Location, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public void move(String sourceInput, String targetInput) {
        Location source = Location.of(sourceInput);
        Location target = Location.of(targetInput);
        Piece piece = findPieceAt(source);
        Path path = createPath(source, target);
        if (piece.canMove(path)) {
            board.remove(source);
            board.put(target, piece);
            return;
        }
        throw new IllegalArgumentException("유효하지 않은 움직임입니다.");
    }

    private Path createPath(Location source, Location target) {
        Piece movingPiece = findPieceAt(source);
        List<Direction> directions = Direction.createDirections(source, target);
        List<SquareState> squareStates = new ArrayList<>();
        Location currentLocation = source;
        for (Direction direction : directions) {
            currentLocation = currentLocation.move(direction);
            Piece locatedPiece = board.get(currentLocation);
            squareStates.add(findSquareStates(movingPiece, locatedPiece));
        }
        return Path.of(directions, squareStates);
    }

    private SquareState findSquareStates(Piece movingPiece, Piece locatedPiece) {
        if (locatedPiece == null) {
            return SquareState.EMPTY;
        }
        if (movingPiece.isAllyPiece(locatedPiece)) {
            return SquareState.ALLY;
        }
        return SquareState.ENEMY;
    }

    private Piece findPieceAt(Location source) {
        Piece piece = board.get(source);
        if (piece == null) {
            throw new IllegalArgumentException("말이 존재하지 않습니다.");
        }
        return piece;
    }
}
