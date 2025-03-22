package chess;

import chess.piece.Bishop;
import chess.piece.EmptyPiece;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public static ChessBoard createInitialBoard() {
        Map<Position, Piece> piecePositionInfo = new HashMap<>();
        for (Column col : Column.values()) {
            piecePositionInfo.put(new Position(Row.TWO, col), new Pawn(TeamColor.WHITE));
        }
        piecePositionInfo.put(new Position(Row.ONE, Column.A), new Rook(TeamColor.WHITE));
        piecePositionInfo.put(new Position(Row.ONE, Column.H), new Rook(TeamColor.WHITE));

        piecePositionInfo.put(new Position(Row.ONE, Column.B), new Knight(TeamColor.WHITE));
        piecePositionInfo.put(new Position(Row.ONE, Column.G), new Knight(TeamColor.WHITE));

        piecePositionInfo.put(new Position(Row.ONE, Column.C), new Bishop(TeamColor.WHITE));
        piecePositionInfo.put(new Position(Row.ONE, Column.F), new Bishop(TeamColor.WHITE));

        piecePositionInfo.put(new Position(Row.ONE, Column.D), new Queen(TeamColor.WHITE));

        piecePositionInfo.put(new Position(Row.ONE, Column.E), new King(TeamColor.WHITE));

        // 흑 팀
        for (Column col : Column.values()) {
            piecePositionInfo.put(new Position(Row.SEVEN, col), new Pawn(TeamColor.BLACK));
        }

        piecePositionInfo.put(new Position(Row.EIGHT, Column.A), new Rook(TeamColor.BLACK));
        piecePositionInfo.put(new Position(Row.EIGHT, Column.H), new Rook(TeamColor.BLACK));

        piecePositionInfo.put(new Position(Row.EIGHT, Column.B), new Knight(TeamColor.BLACK));
        piecePositionInfo.put(new Position(Row.EIGHT, Column.G), new Knight(TeamColor.BLACK));

        piecePositionInfo.put(new Position(Row.EIGHT, Column.C), new Bishop(TeamColor.BLACK));
        piecePositionInfo.put(new Position(Row.EIGHT, Column.F), new Bishop(TeamColor.BLACK));

        piecePositionInfo.put(new Position(Row.EIGHT, Column.D), new Queen(TeamColor.BLACK));

        piecePositionInfo.put(new Position(Row.EIGHT, Column.E), new King(TeamColor.BLACK));

        return new ChessBoard(piecePositionInfo);
    }


    public Piece findPieceBy(Position position) {
        return board.getOrDefault(position, EmptyPiece.getInstance());
    }


    public void move(Piece piece, Position start, Position target) {
        boolean availablePath = piece.availablePath(start, target);
        if (!availablePath) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }

        List<Position> routes = piece.findAllRouteToTarget(start, target);
        List<Piece> piecesOnRoute = routes.stream()
                .map(this::findPieceBy)
                .toList();

        boolean canMove = piece.canMove(piecesOnRoute, start, target);

        if (!canMove) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }

        board.remove(start);
        board.put(target, piece);
    }
}
