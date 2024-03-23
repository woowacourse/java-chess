package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardGenerator implements BoardGenerator {
    private final static ChessBoardGenerator INSTANCE = new ChessBoardGenerator();

    private ChessBoardGenerator() {
    }

    public static ChessBoardGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public Map<Position, Piece> generate() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createBlackFirstLine(ChessRank.EIGHT));
        pieces.putAll(createBlackSecondLine(ChessRank.SEVEN));

        pieces.putAll(createWhiteSecondLine(ChessRank.TWO));
        pieces.putAll(createWhiteFirstLine(ChessRank.ONE));
        return pieces;
    }

    private Map<Position, Piece> createBlackFirstLine(final ChessRank chessRank) {
        Map<Position, Piece> firstLine = new HashMap<>();
        firstLine.put(Position.of(ChessFile.A.value() + chessRank.value()), new Piece(PieceType.BLACK_ROOK));
        firstLine.put(Position.of(ChessFile.B.value() + chessRank.value()), new Piece(PieceType.BLACK_KNIGHT));
        firstLine.put(Position.of(ChessFile.C.value() + chessRank.value()), new Piece(PieceType.BLACK_BISHOP));
        firstLine.put(Position.of(ChessFile.D.value() + chessRank.value()), new Piece(PieceType.BLACK_QUEEN));
        firstLine.put(Position.of(ChessFile.E.value() + chessRank.value()), new Piece(PieceType.BLACK_KING));
        firstLine.put(Position.of(ChessFile.F.value() + chessRank.value()), new Piece(PieceType.BLACK_BISHOP));
        firstLine.put(Position.of(ChessFile.G.value() + chessRank.value()), new Piece(PieceType.BLACK_KNIGHT));
        firstLine.put(Position.of(ChessFile.H.value() + chessRank.value()), new Piece(PieceType.BLACK_ROOK));

        return firstLine;
    }

    private Map<Position, Piece> createWhiteFirstLine(final ChessRank chessRank) {
        Map<Position, Piece> firstLine = new HashMap<>();
        firstLine.put(Position.of(ChessFile.A.value() + chessRank.value()), new Piece(PieceType.WHITE_ROOK));
        firstLine.put(Position.of(ChessFile.B.value() + chessRank.value()), new Piece(PieceType.WHITE_KNIGHT));
        firstLine.put(Position.of(ChessFile.C.value() + chessRank.value()), new Piece(PieceType.WHITE_BISHOP));
        firstLine.put(Position.of(ChessFile.D.value() + chessRank.value()), new Piece(PieceType.WHITE_QUEEN));
        firstLine.put(Position.of(ChessFile.E.value() + chessRank.value()), new Piece(PieceType.WHITE_KING));
        firstLine.put(Position.of(ChessFile.F.value() + chessRank.value()), new Piece(PieceType.WHITE_BISHOP));
        firstLine.put(Position.of(ChessFile.G.value() + chessRank.value()), new Piece(PieceType.WHITE_KNIGHT));
        firstLine.put(Position.of(ChessFile.H.value() + chessRank.value()), new Piece(PieceType.WHITE_ROOK));

        return firstLine;
    }

    private Map<Position, Piece> createBlackSecondLine(ChessRank chessRank) {
        Map<Position, Piece> secondLine = new HashMap<>();
        secondLine.put(Position.of(ChessFile.A.value() + chessRank.value()), new Piece(PieceType.BLACK_PAWN));
        secondLine.put(Position.of(ChessFile.B.value() + chessRank.value()), new Piece(PieceType.BLACK_PAWN));
        secondLine.put(Position.of(ChessFile.C.value() + chessRank.value()), new Piece(PieceType.BLACK_PAWN));
        secondLine.put(Position.of(ChessFile.D.value() + chessRank.value()), new Piece(PieceType.BLACK_PAWN));
        secondLine.put(Position.of(ChessFile.E.value() + chessRank.value()), new Piece(PieceType.BLACK_PAWN));
        secondLine.put(Position.of(ChessFile.F.value() + chessRank.value()), new Piece(PieceType.BLACK_PAWN));
        secondLine.put(Position.of(ChessFile.G.value() + chessRank.value()), new Piece(PieceType.BLACK_PAWN));
        secondLine.put(Position.of(ChessFile.H.value() + chessRank.value()), new Piece(PieceType.BLACK_PAWN));

        return secondLine;
    }

    private Map<Position, Piece> createWhiteSecondLine(final ChessRank chessRank) {
        Map<Position, Piece> secondLine = new HashMap<>();
        secondLine.put(Position.of(ChessFile.A.value() + chessRank.value()), new Piece(PieceType.WHITE_PAWN));
        secondLine.put(Position.of(ChessFile.B.value() + chessRank.value()), new Piece(PieceType.WHITE_PAWN));
        secondLine.put(Position.of(ChessFile.C.value() + chessRank.value()), new Piece(PieceType.WHITE_PAWN));
        secondLine.put(Position.of(ChessFile.D.value() + chessRank.value()), new Piece(PieceType.WHITE_PAWN));
        secondLine.put(Position.of(ChessFile.E.value() + chessRank.value()), new Piece(PieceType.WHITE_PAWN));
        secondLine.put(Position.of(ChessFile.F.value() + chessRank.value()), new Piece(PieceType.WHITE_PAWN));
        secondLine.put(Position.of(ChessFile.G.value() + chessRank.value()), new Piece(PieceType.WHITE_PAWN));
        secondLine.put(Position.of(ChessFile.H.value() + chessRank.value()), new Piece(PieceType.WHITE_PAWN));

        return secondLine;
    }
}
