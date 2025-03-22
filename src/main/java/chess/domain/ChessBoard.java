package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.None;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, ChessPiece> board = new HashMap<>();

    public ChessBoard() {
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                board.put(new Position(row, column), new None());
            }
        }

        board.put(new Position(Row.EIGHT, Column.A), new Rook());
        board.put(new Position(Row.EIGHT, Column.B), new Knight());
        board.put(new Position(Row.EIGHT, Column.C), new Bishop());
        board.put(new Position(Row.EIGHT, Column.D), new Queen());
        board.put(new Position(Row.EIGHT, Column.E), new King());
        board.put(new Position(Row.EIGHT, Column.F), new Bishop());
        board.put(new Position(Row.EIGHT, Column.G), new Knight());
        board.put(new Position(Row.EIGHT, Column.H), new Rook());

        board.put(new Position(Row.SEVEN, Column.A), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.B), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.C), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.D), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.E), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.F), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.G), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.H), new BlackPawn());

        board.put(new Position(Row.ONE, Column.A), new Rook());
        board.put(new Position(Row.ONE, Column.B), new Knight());
        board.put(new Position(Row.ONE, Column.C), new Bishop());
        board.put(new Position(Row.ONE, Column.D), new Queen());
        board.put(new Position(Row.ONE, Column.E), new King());
        board.put(new Position(Row.ONE, Column.F), new Bishop());
        board.put(new Position(Row.ONE, Column.G), new Knight());
        board.put(new Position(Row.ONE, Column.H), new Rook());

        board.put(new Position(Row.TWO, Column.A), new WhitePawn());
        board.put(new Position(Row.TWO, Column.B), new WhitePawn());
        board.put(new Position(Row.TWO, Column.C), new WhitePawn());
        board.put(new Position(Row.TWO, Column.D), new WhitePawn());
        board.put(new Position(Row.TWO, Column.E), new WhitePawn());
        board.put(new Position(Row.TWO, Column.F), new WhitePawn());
        board.put(new Position(Row.TWO, Column.G), new WhitePawn());
        board.put(new Position(Row.TWO, Column.H), new WhitePawn());
    }

    // 1. 도착지 자체가 말이 갈 수 있는 도착지인지 확인(Piece.canMove에 출발, 도착지 넘겨줌)
    // 2. 장애물 있는지 확인
    // 3. 도착지 말 색 확인
    public void movePiece(Position origin, Position destination) {
        ChessPiece movePiece = getPieceOfPosition(origin);

        if (movePiece.getClass().equals(Knight.class)) {
            // 장애물 있어도 됨
        }
        else if (movePiece.getClass().equals(BlackPawn.class) || movePiece.getClass().equals(WhitePawn.class)) {
            // 경로 달라짐
        }
        else {
            // 장애물 있는지 확인

        }

        // 도착지
    }

    public ChessPiece getPieceOfPosition(Position position) {
        return board.get(position);
    }
}
