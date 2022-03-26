package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> squares;

    public Board() {
        this.squares = initialize();
    }

    private Map<Position, Piece> initialize() {
        Map<Position, Piece> squares = new HashMap<>();

        initEmptyPieces(squares);
        initNotPawnSquares(squares, Rank.ONE, Color.WHITE);
        initPawnPieces(squares, Rank.TWO, Color.WHITE);
        initPawnPieces(squares, Rank.SEVEN, Color.BLACK);
        initNotPawnSquares(squares, Rank.EIGHT, Color.BLACK);

        return squares;
    }

    private void initEmptyPieces(Map<Position, Piece> squares) {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                squares.put(new Position(file, rank), new EmptyPiece());
            }
        }
    }

    private void initPawnPieces(Map<Position, Piece> squares, Rank rank, Color color) {
        for (File file : File.values()) {
            squares.replace(new Position(file, rank), new Pawn(color));
        }
    }

    private void initNotPawnSquares(Map<Position, Piece> squares, Rank rank, Color color) {
        squares.replace(new Position(File.A, rank), new Rook(color));
        squares.replace(new Position(File.B, rank), new Knight(color));
        squares.replace(new Position(File.C, rank), new Bishop(color));
        squares.replace(new Position(File.D, rank), new Queen(color));
        squares.replace(new Position(File.E, rank), new King(color));
        squares.replace(new Position(File.F, rank), new Bishop(color));
        squares.replace(new Position(File.G, rank), new Knight(color));
        squares.replace(new Position(File.H, rank), new Rook(color));
    }

    public Piece findByPosition(Position position) {
        return squares.get(position);
    }

    public void move(Position from, Position to) {
        Piece sourcePiece = squares.get(from);
        Piece targetPiece = squares.get(to);

        // (테스트 필요)이동할 위치가 같은 색깔이 아님을 검증한다.
        validateNotSameColor(sourcePiece, targetPiece);

        if (!sourcePiece.canMove(this, from, to) && !targetPiece.isEmpty()) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }

        // 장애물이 없음을 검증한다
        validateNotHurdle(from, to);

        squares.replace(to, sourcePiece);
        squares.replace(from, new EmptyPiece());
    }

    private void validateNotSameColor(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameColor(targetPiece)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNotHurdle(Position from, Position to) {
        List<Position> route = findByPosition(from).getRoute(from, to);

        for (Position position : route) {
            if (!findByPosition(position).isEmpty()) {
                throw new IllegalArgumentException("이동할 수 없다.");
            }
        }
    }

//    private boolean isOppositeColor(Piece sourcePiece, Piece targetPiece) {
//        return !targetPiece.isEmpty() && !sourcePiece.isSameColor(targetPiece);
//    }
}
