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
import chess.view.MoveCommand;
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

    private static void validatePiece(Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("말이 존재하지 않습니다.");
        }
    }

    private void initialPawnSetting(Map<Location, Piece> board) {
        for (Column value : Column.values()) {
            board.put(new Location(value, Row.TWO), new WhitePawn());
            board.put(new Location(value, Row.SEVEN), new BlackPawn());
        }
    }

    private void initialRookSetting(Map<Location, Piece> board) {
        board.put(new Location(Column.A, Row.ONE), new Rook(Color.WHITE));
        board.put(new Location(Column.H, Row.ONE), new Rook(Color.WHITE));
        board.put(new Location(Column.A, Row.EIGHT), new Rook(Color.BLACK));
        board.put(new Location(Column.H, Row.EIGHT), new Rook(Color.BLACK));
    }

    private void initialKnightSetting(Map<Location, Piece> board) {
        board.put(new Location(Column.B, Row.ONE), new Knight(Color.WHITE));
        board.put(new Location(Column.G, Row.ONE), new Knight(Color.WHITE));
        board.put(new Location(Column.B, Row.EIGHT), new Knight(Color.BLACK));
        board.put(new Location(Column.G, Row.EIGHT), new Knight(Color.BLACK));
    }

    private void initialQueenSetting(Map<Location, Piece> board) {
        board.put(new Location(Column.D, Row.ONE), new Queen(Color.WHITE));
        board.put(new Location(Column.D, Row.EIGHT), new Queen(Color.BLACK));
    }

    private void initialKingSetting(Map<Location, Piece> board) {
        board.put(new Location(Column.E, Row.ONE), new King(Color.WHITE));
        board.put(new Location(Column.E, Row.EIGHT), new King(Color.BLACK));
    }

    public void tryMove(MoveCommand moveCommand) {
        Piece sourcePiece = findPieceAt(moveCommand.getSource());
        Route route = createPath(moveCommand);
        if (sourcePiece.canMove(route)) {
            move(moveCommand, sourcePiece);
            return;
        }
        throw new IllegalArgumentException("유효하지 않은 움직임입니다.");
    }

    private void move(MoveCommand moveCommand, Piece movingPiece) {
        board.remove(moveCommand.getSource());
        board.put(moveCommand.getTarget(), movingPiece);
    }

    private Route createPath(MoveCommand moveCommand) {
        List<Direction> directions = DirectionFinder.createDirections(moveCommand.getSource(), moveCommand.getTarget());
        List<SquareState> squareStates = createPathState(moveCommand.getSource(), directions);
        return new Route(directions, squareStates);
    }

    private List<SquareState> createPathState(Location current, List<Direction> directions) {
        Piece movingPiece = findPieceAt(current);
        List<SquareState> squareStates = new ArrayList<>();
        Location moved = current;
        for (Direction direction : directions) {
            moved = moved.move(direction);
            Piece movedPiece = board.get(moved);
            squareStates.add(checkSquareStates(movingPiece, movedPiece));
        }
        return squareStates;
    }

    private SquareState checkSquareStates(Piece movingPiece, Piece movedPiece) {
        if (movedPiece == null) {
            return SquareState.EMPTY;
        }
        if (movingPiece.isAllyPiece(movedPiece)) {
            return SquareState.ALLY;
        }
        return SquareState.ENEMY;
    }

    private void initialBishopSetting(Map<Location, Piece> board) {
        board.put(new Location(Column.C, Row.ONE), new Bishop(Color.WHITE));
        board.put(new Location(Column.F, Row.ONE), new Bishop(Color.WHITE));
        board.put(new Location(Column.C, Row.EIGHT), new Bishop(Color.BLACK));
        board.put(new Location(Column.F, Row.EIGHT), new Bishop(Color.BLACK));
    }

    private Piece findPieceAt(Location source) {
        Piece piece = board.get(source);
        validatePiece(piece);
        return piece;
    }

    public Map<Location, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
