package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> squares;

    public Board() {
        this.squares = initialize();
    }

    private Map<Position, Piece> initialize() {
        Map<Position, Piece> squares = new HashMap<>();

        initEmptyPieces(squares);
        initNotPawnSqaures(squares, Rank.ONE, Color.BLACK);
        initPawnPieces(squares, Rank.TWO, Color.BLACK);
        initPawnPieces(squares, Rank.SEVEN, Color.WHITE);
        initNotPawnSqaures(squares, Rank.EIGHT, Color.WHITE);

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

    private void initNotPawnSqaures(Map<Position, Piece> squares, Rank rank, Color color) {
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
}
