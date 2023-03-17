package chess.domain.board;

import chess.domain.board.position.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    private static final int START_VALUE_OF_FILE = 1;
    private static final int END_VALUE_OF_FILE = 8;

    public Map<Position, Piece> createInitialBoard() {
        Map<Position, Piece> chessBoard = new HashMap<>();

        chessBoard.putAll(createPawn());
        chessBoard.putAll(createKing());
        chessBoard.putAll(createQueen());
        chessBoard.putAll(createBishop());
        chessBoard.putAll(createRook());
        chessBoard.putAll(createKnight());

        return chessBoard;
    }

    private Map<Position, Piece> createPawn() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        for (int file = START_VALUE_OF_FILE; file <= END_VALUE_OF_FILE; file++) {
            final Position whitePosition = new Position(file, White.PAWN_RANK.value());
            final Position blackPosition = new Position(file, Black.PAWN_RANK.value());
            pieceMap.put(whitePosition, new Pawn(Color.WHITE));
            pieceMap.put(blackPosition, new Pawn(Color.BLACK));
        }

        return pieceMap;
    }

    private Map<Position, Piece> createKing() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whitePosition = new Position(White.KING_FILE.value(), White.KING_RANK.value());
        pieceMap.put(whitePosition, new King(Color.WHITE));

        final Position blackPosition = new Position(5, 8);
        pieceMap.put(blackPosition, new King(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createKnight() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whiteLeftPosition = new Position(White.LEFT_KNIGHT_FILE.value(),
                                                        White.LEFT_KNIGHT_RANK.value());

        final Position whiteRightPosition = new Position(White.RIGHT_KNIGHT_FILE.value(),
                                                         White.RIGHT_KNIGHT_RANK.value());

        final Position blackLeftPosition = new Position(Black.LEFT_KNIGHT_FILE.value(),
                                                        Black.LEFT_KNIGHT_RANK.value());

        final Position blackRightPosition = new Position(Black.RIGHT_KNIGHT_FILE.value(),
                                                         Black.RIGHT_KNIGHT_RANK.value());

        pieceMap.put(whiteLeftPosition, new Knight(Color.WHITE));
        pieceMap.put(whiteRightPosition, new Knight(Color.WHITE));
        pieceMap.put(blackLeftPosition, new Knight(Color.BLACK));
        pieceMap.put(blackRightPosition, new Knight(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createBishop() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whiteLeftPosition = new Position(White.LEFT_BISHOP_FILE.value(),
                                                        White.LEFT_BISHOP_RANK.value());

        final Position whiteRightPosition = new Position(White.RIGHT_BISHOP_FILE.value(),
                                                         White.RIGHT_BISHOP_RANK.value());

        final Position blackLeftPosition = new Position(Black.LEFT_BISHOP_FILE.value(),
                                                        Black.LEFT_BISHOP_RANK.value());

        final Position blackRightPosition = new Position(Black.RIGHT_BISHOP_FILE.value(),
                                                         Black.RIGHT_BISHOP_RANK.value());


        pieceMap.put(whiteLeftPosition, new Bishop(Color.WHITE));
        pieceMap.put(whiteRightPosition, new Bishop(Color.WHITE));
        pieceMap.put(blackLeftPosition, new Bishop(Color.BLACK));
        pieceMap.put(blackRightPosition, new Bishop(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createQueen() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whitePosition = new Position(White.QUEEN_FILE.value(), White.QUEEN_RANK.value());
        final Position blackPosition = new Position(Black.QUEEN_FILE.value(), Black.QUEEN_RANK.value());

        pieceMap.put(whitePosition, new Queen(Color.WHITE));
        pieceMap.put(blackPosition, new Queen(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createRook() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whiteLeftPosition = new Position(White.LEFT_ROOK_FILE.value(), White.LEFT_ROOK_RANK.value());
        final Position whiteRightPosition = new Position(White.RIGHT_ROOK_FILE.value(),White.RIGHT_ROOK_RANK.value());
        final Position blackLeftPosition = new Position(Black.LEFT_ROOK_FILE.value(), Black.LEFT_ROOK_RANK.value());
        final Position blackRightPosition = new Position(Black.RIGHT_ROOK_FILE.value(), Black.RIGHT_ROOK_RANK.value());

        pieceMap.put(whiteLeftPosition, new Rook(Color.WHITE));
        pieceMap.put(whiteRightPosition, new Rook(Color.WHITE));
        pieceMap.put(blackLeftPosition, new Rook(Color.BLACK));
        pieceMap.put(blackRightPosition, new Rook(Color.BLACK));

        return pieceMap;
    }
}
