package chess.domain;

import chess.domain.piece.linear_moving_chess_piece.Bishop;
import chess.domain.piece.limited_moving_chess_piece.BlackPawn;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.limited_moving_chess_piece.King;
import chess.domain.piece.limited_moving_chess_piece.Knight;
import chess.domain.piece.limited_moving_chess_piece.None;
import chess.domain.piece.limited_moving_chess_piece.Pawn;
import chess.domain.piece.linear_moving_chess_piece.Queen;
import chess.domain.piece.linear_moving_chess_piece.Rook;
import chess.domain.piece.limited_moving_chess_piece.WhitePawn;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, ChessPiece> board = new HashMap<>();
    private final ChessPiece blackKing;
    private final ChessPiece whiteKing;

    public ChessBoard() {
        blackKing = new King(Color.BLACK);
        whiteKing = new King(Color.WHITE);

        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                board.put(new Position(row, column), new None());
            }
        }

        board.put(new Position(Row.EIGHT, Column.A), new Rook(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.B), new Knight(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.C), new Bishop(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.D), new Queen(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.E), blackKing);
        board.put(new Position(Row.EIGHT, Column.F), new Bishop(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.G), new Knight(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.H), new Rook(Color.BLACK));

        board.put(new Position(Row.SEVEN, Column.A), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.B), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.C), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.D), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.E), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.F), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.G), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.H), new BlackPawn());

        board.put(new Position(Row.ONE, Column.A), new Rook(Color.WHITE));
        board.put(new Position(Row.ONE, Column.B), new Knight(Color.WHITE));
        board.put(new Position(Row.ONE, Column.C), new Bishop(Color.WHITE));
        board.put(new Position(Row.ONE, Column.D), new Queen(Color.WHITE));
        board.put(new Position(Row.ONE, Column.E), whiteKing);
        board.put(new Position(Row.ONE, Column.F), new Bishop(Color.WHITE));
        board.put(new Position(Row.ONE, Column.G), new Knight(Color.WHITE));
        board.put(new Position(Row.ONE, Column.H), new Rook(Color.WHITE));

        board.put(new Position(Row.TWO, Column.A), new WhitePawn());
        board.put(new Position(Row.TWO, Column.B), new WhitePawn());
        board.put(new Position(Row.TWO, Column.C), new WhitePawn());
        board.put(new Position(Row.TWO, Column.D), new WhitePawn());
        board.put(new Position(Row.TWO, Column.E), new WhitePawn());
        board.put(new Position(Row.TWO, Column.F), new WhitePawn());
        board.put(new Position(Row.TWO, Column.G), new WhitePawn());
        board.put(new Position(Row.TWO, Column.H), new WhitePawn());
    }

    public ChessPiece getPieceOfPosition(Position position) {
        return board.get(position);
    }

    public void moveAndCapturePiece(Position origin, Position destination) {

        ChessPiece movePiece = getPieceOfPosition(origin);
        List<Movement> route = movePiece.findRoute(origin, destination);
        boolean isExistHurdleOnRoute = checkHurdleExistOnRouteWithoutDestination(origin, route);
        ChessPiece targetPiece = getPieceOfPosition(destination);
        movePiece.validateCanMove(route, isExistHurdleOnRoute, targetPiece);

        if (movePiece.getClass().equals(BlackPawn.class) || movePiece.getClass().equals(WhitePawn.class)) {
            ((Pawn) movePiece).isMoved();
        }

        targetPiece.capture();
        board.put(origin, new None());
        board.put(destination, movePiece);
    }

    private boolean checkHurdleExistOnRouteWithoutDestination(Position origin, List<Movement> route) {
        List<Movement> routeWithoutDestination = route.subList(0, route.size() - 1);
        Position origin2 = origin;
        for (Movement movement : routeWithoutDestination) {
            if (origin2.canMove(movement)) {
                origin2 = origin2.move(movement);
                if (!getPieceOfPosition(origin2).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkOppositeKingCaptured(Color color) {
        if (color == Color.BLACK) {
            return whiteKing.isCaptured();
        }
        if (color == Color.WHITE) {
            return blackKing.isCaptured();
        }
        return false;
    }
}
