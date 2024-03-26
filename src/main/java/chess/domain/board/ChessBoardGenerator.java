package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.type.*;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardGenerator {
    private final static ChessBoardGenerator INSTANCE = new ChessBoardGenerator();

    private ChessBoardGenerator() {
    }

    public static ChessBoardGenerator getInstance() {
        return INSTANCE;
    }

    public Map<Position, Piece> generate() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createFirstLine(ChessRank.EIGHT, PieceColor.BLACK));
        pieces.putAll(createSecondLine(ChessRank.SEVEN, PieceColor.BLACK));

        pieces.putAll(createSecondLine(ChessRank.TWO, PieceColor.WHITE));
        pieces.putAll(createFirstLine(ChessRank.ONE, PieceColor.WHITE));
        return pieces;
    }

    private Map<Position, Piece> createFirstLine(ChessRank chessRank, PieceColor color) {
        Map<Position, Piece> firstLine = new HashMap<>();
        firstLine.put(Position.of(ChessFile.A.value() + chessRank.value()), new Rook(color));
        firstLine.put(Position.of(ChessFile.B.value() + chessRank.value()), new Knight(color));
        firstLine.put(Position.of(ChessFile.C.value() + chessRank.value()), new Bishop(color));
        firstLine.put(Position.of(ChessFile.D.value() + chessRank.value()), new Queen(color));
        firstLine.put(Position.of(ChessFile.E.value() + chessRank.value()), new King(color));
        firstLine.put(Position.of(ChessFile.F.value() + chessRank.value()), new Bishop(color));
        firstLine.put(Position.of(ChessFile.G.value() + chessRank.value()), new Knight(color));
        firstLine.put(Position.of(ChessFile.H.value() + chessRank.value()), new Rook(color));

        return firstLine;
    }

    private Map<Position, Piece> createSecondLine(ChessRank chessRank, PieceColor color) {
        Map<Position, Piece> secondLine = new HashMap<>();
        secondLine.put(Position.of(ChessFile.A.value() + chessRank.value()), new Pawn(color));
        secondLine.put(Position.of(ChessFile.B.value() + chessRank.value()), new Pawn(color));
        secondLine.put(Position.of(ChessFile.C.value() + chessRank.value()), new Pawn(color));
        secondLine.put(Position.of(ChessFile.D.value() + chessRank.value()), new Pawn(color));
        secondLine.put(Position.of(ChessFile.E.value() + chessRank.value()), new Pawn(color));
        secondLine.put(Position.of(ChessFile.F.value() + chessRank.value()), new Pawn(color));
        secondLine.put(Position.of(ChessFile.G.value() + chessRank.value()), new Pawn(color));
        secondLine.put(Position.of(ChessFile.H.value() + chessRank.value()), new Pawn(color));

        return secondLine;
    }


}
