package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardGenerator implements BoardGenerator {
    private static final ChessBoardGenerator INSTANCE = new ChessBoardGenerator();

    private static final List<PieceType> FIRST_LINE_WHITE_PIECES = List.of(
            PieceType.WHITE_ROOK,
            PieceType.WHITE_KNIGHT,
            PieceType.WHITE_BISHOP,
            PieceType.WHITE_QUEEN,
            PieceType.WHITE_KING,
            PieceType.WHITE_BISHOP,
            PieceType.WHITE_KNIGHT,
            PieceType.WHITE_ROOK
    );

    private static final List<PieceType> FIRST_LINE_BLACK_PIECES = List.of(
            PieceType.BLACK_ROOK,
            PieceType.BLACK_KNIGHT,
            PieceType.BLACK_BISHOP,
            PieceType.BLACK_QUEEN,
            PieceType.BLACK_KING,
            PieceType.BLACK_BISHOP,
            PieceType.BLACK_KNIGHT,
            PieceType.BLACK_ROOK
    );

    private ChessBoardGenerator() {
    }

    public static ChessBoardGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public Map<Position, Piece> generate() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createLineFromAnotherPieceType(ChessRank.EIGHT, FIRST_LINE_BLACK_PIECES));
        pieces.putAll(createLineFromOnePieceType(ChessRank.SEVEN, PieceType.BLACK_PAWN));

        pieces.putAll(createLineFromOnePieceType(ChessRank.TWO, PieceType.WHITE_PAWN));
        pieces.putAll(createLineFromAnotherPieceType(ChessRank.ONE, FIRST_LINE_WHITE_PIECES));
        return pieces;
    }

    private Map<Position, Piece> createLineFromAnotherPieceType(final ChessRank chessRank, final List<PieceType> types) {
        Map<Position, Piece> firstLine = new HashMap<>();
        firstLine.put(Position.of(ChessFile.A, chessRank), new Piece(types.get(ChessFile.A.index())));
        firstLine.put(Position.of(ChessFile.B, chessRank), new Piece(types.get(ChessFile.B.index())));
        firstLine.put(Position.of(ChessFile.C, chessRank), new Piece(types.get(ChessFile.C.index())));
        firstLine.put(Position.of(ChessFile.D, chessRank), new Piece(types.get(ChessFile.D.index())));
        firstLine.put(Position.of(ChessFile.E, chessRank), new Piece(types.get(ChessFile.E.index())));
        firstLine.put(Position.of(ChessFile.F, chessRank), new Piece(types.get(ChessFile.F.index())));
        firstLine.put(Position.of(ChessFile.G, chessRank), new Piece(types.get(ChessFile.G.index())));
        firstLine.put(Position.of(ChessFile.H, chessRank), new Piece(types.get(ChessFile.H.index())));

        return firstLine;
    }

    private Map<Position, Piece> createLineFromOnePieceType(final ChessRank chessRank, final PieceType type) {
        Map<Position, Piece> line = new HashMap<>();
        line.put(Position.of(ChessFile.A, chessRank), new Piece(type));
        line.put(Position.of(ChessFile.B, chessRank), new Piece(type));
        line.put(Position.of(ChessFile.C, chessRank), new Piece(type));
        line.put(Position.of(ChessFile.D, chessRank), new Piece(type));
        line.put(Position.of(ChessFile.E, chessRank), new Piece(type));
        line.put(Position.of(ChessFile.F, chessRank), new Piece(type));
        line.put(Position.of(ChessFile.G, chessRank), new Piece(type));
        line.put(Position.of(ChessFile.H, chessRank), new Piece(type));

        return line;
    }
}
