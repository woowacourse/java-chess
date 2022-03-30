package chess.domain.board;

import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private BoardFactory() {
    }

    public static Board createInitializedBoard() {
        return new Board(initBoard());
    }

    private static Map<Position, AbstractPiece> initBoard() {
        Map<Position, AbstractPiece> value = new HashMap<>();

        initializeSpecialPieces(value, YAxis.ONE, PieceColor.WHITE);
        initializeSpecialPieces(value, YAxis.EIGHT, PieceColor.BLACK);

        initializePawns(value, YAxis.TWO, PieceColor.WHITE);
        initializePawns(value, YAxis.SEVEN, PieceColor.BLACK);

        return value;
    }

    private static void initializePawns(Map<Position, AbstractPiece> value, YAxis yAxis, PieceColor pieceColor) {
        for (XAxis xAxis : XAxis.values()) {
            value.put(Position.from(xAxis, yAxis), new Pawn(pieceColor));
        }
    }

    private static void initializeSpecialPieces(Map<Position, AbstractPiece> value, YAxis yAxis,
                                                PieceColor pieceColor) {
        value.put(Position.from(XAxis.A, yAxis), new Rook(pieceColor));
        value.put(Position.from(XAxis.B, yAxis), new Knight(pieceColor));
        value.put(Position.from(XAxis.C, yAxis), new Bishop(pieceColor));
        value.put(Position.from(XAxis.D, yAxis), new Queen(pieceColor));
        value.put(Position.from(XAxis.E, yAxis), new King(pieceColor));
        value.put(Position.from(XAxis.F, yAxis), new Bishop(pieceColor));
        value.put(Position.from(XAxis.G, yAxis), new Knight(pieceColor));
        value.put(Position.from(XAxis.H, yAxis), new Rook(pieceColor));
    }
}
