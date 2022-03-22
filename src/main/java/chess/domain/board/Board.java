package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Board {
    private final Map<Position, Piece> value;

    private Board(Map<Position, Piece> value) {
        this.value = value;
    }

    public static Board createInitializedBoard() {
        return new Board(initBoard());
    }

    private static Map<Position, Piece> initBoard() {
        Map<Position, Piece> value = new HashMap<>();

        initializeSpecialPieces(value, YAxis.ONE, PieceColor.WHITE);
        initializeSpecialPieces(value, YAxis.EIGHT, PieceColor.BLACK);

        initializePawns(value, YAxis.TWO, PieceColor.WHITE);
        initializePawns(value, YAxis.SEVEN, PieceColor.WHITE);

        return value;
    }

    private static void initializePawns(Map<Position, Piece> value, YAxis yAxis, PieceColor pieceColor) {
        for (XAxis xAxis : XAxis.values()) {
            value.put(Position.from(xAxis, yAxis), PieceFactory.createPawn(pieceColor));
        }
    }

    private static void initializeSpecialPieces(Map<Position, Piece> value, YAxis yAxis, PieceColor pieceColor) {
        value.put(Position.from(XAxis.A, yAxis), PieceFactory.createRook(pieceColor));
        value.put(Position.from(XAxis.B, yAxis), PieceFactory.createNight(pieceColor));
        value.put(Position.from(XAxis.C, yAxis), PieceFactory.createBishop(pieceColor));
        value.put(Position.from(XAxis.D, yAxis), PieceFactory.createQueen(pieceColor));
        value.put(Position.from(XAxis.E, yAxis), PieceFactory.createKing(pieceColor));
        value.put(Position.from(XAxis.F, yAxis), PieceFactory.createBishop(pieceColor));
        value.put(Position.from(XAxis.G, yAxis), PieceFactory.createNight(pieceColor));
        value.put(Position.from(XAxis.H, yAxis), PieceFactory.createRook(pieceColor));
    }

    public Optional<Piece> find(Position position) {
        return Optional.ofNullable(value.get(position));
    }
}
