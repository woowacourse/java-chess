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
            final Position whitePosition = new Position(file, WhitePiecePosition.PAWN_RANK.value());
            final Position blackPosition = new Position(file, BlackPiecePosition.PAWN_RANK.value());
            pieceMap.put(whitePosition, new Pawn(Color.WHITE));
            pieceMap.put(blackPosition, new Pawn(Color.BLACK));
        }

        return pieceMap;
    }

    private Map<Position, Piece> createKing() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whitePosition = new Position(WhitePiecePosition.KING_FILE.value(), WhitePiecePosition.KING_RANK.value());
        pieceMap.put(whitePosition, new King(Color.WHITE));

        final Position blackPosition = new Position(5, 8);
        pieceMap.put(blackPosition, new King(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createQueen() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whitePosition = new Position(WhitePiecePosition.QUEEN_FILE.value(), WhitePiecePosition.QUEEN_RANK.value());
        final Position blackPosition = new Position(BlackPiecePosition.QUEEN_FILE.value(), BlackPiecePosition.QUEEN_RANK.value());

        pieceMap.put(whitePosition, new Queen(Color.WHITE));
        pieceMap.put(blackPosition, new Queen(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createBishop() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whiteLeftPosition = new Position(WhitePiecePosition.LEFT_BISHOP_FILE.value(),
                                                        WhitePiecePosition.LEFT_BISHOP_RANK.value());

        final Position whiteRightPosition = new Position(WhitePiecePosition.RIGHT_BISHOP_FILE.value(),
                                                         WhitePiecePosition.RIGHT_BISHOP_RANK.value());

        final Position blackLeftPosition = new Position(BlackPiecePosition.LEFT_BISHOP_FILE.value(),
                                                        BlackPiecePosition.LEFT_BISHOP_RANK.value());

        final Position blackRightPosition = new Position(BlackPiecePosition.RIGHT_BISHOP_FILE.value(),
                                                         BlackPiecePosition.RIGHT_BISHOP_RANK.value());


        pieceMap.put(whiteLeftPosition, new Bishop(Color.WHITE));
        pieceMap.put(whiteRightPosition, new Bishop(Color.WHITE));
        pieceMap.put(blackLeftPosition, new Bishop(Color.BLACK));
        pieceMap.put(blackRightPosition, new Bishop(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createRook() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whiteLeftPosition = new Position(WhitePiecePosition.LEFT_ROOK_FILE.value(), WhitePiecePosition.LEFT_ROOK_RANK.value());
        final Position whiteRightPosition = new Position(WhitePiecePosition.RIGHT_ROOK_FILE.value(),
                                                         WhitePiecePosition.RIGHT_ROOK_RANK.value());
        final Position blackLeftPosition = new Position(BlackPiecePosition.LEFT_ROOK_FILE.value(), BlackPiecePosition.LEFT_ROOK_RANK.value());
        final Position blackRightPosition = new Position(BlackPiecePosition.RIGHT_ROOK_FILE.value(), BlackPiecePosition.RIGHT_ROOK_RANK.value());

        pieceMap.put(whiteLeftPosition, new Rook(Color.WHITE));
        pieceMap.put(whiteRightPosition, new Rook(Color.WHITE));
        pieceMap.put(blackLeftPosition, new Rook(Color.BLACK));
        pieceMap.put(blackRightPosition, new Rook(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createKnight() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whiteLeftPosition = new Position(WhitePiecePosition.LEFT_KNIGHT_FILE.value(),
                                                        WhitePiecePosition.LEFT_KNIGHT_RANK.value());

        final Position whiteRightPosition = new Position(WhitePiecePosition.RIGHT_KNIGHT_FILE.value(),
                                                         WhitePiecePosition.RIGHT_KNIGHT_RANK.value());

        final Position blackLeftPosition = new Position(BlackPiecePosition.LEFT_KNIGHT_FILE.value(),
                                                        BlackPiecePosition.LEFT_KNIGHT_RANK.value());

        final Position blackRightPosition = new Position(BlackPiecePosition.RIGHT_KNIGHT_FILE.value(),
                                                         BlackPiecePosition.RIGHT_KNIGHT_RANK.value());

        pieceMap.put(whiteLeftPosition, new Knight(Color.WHITE));
        pieceMap.put(whiteRightPosition, new Knight(Color.WHITE));
        pieceMap.put(blackLeftPosition, new Knight(Color.BLACK));
        pieceMap.put(blackRightPosition, new Knight(Color.BLACK));

        return pieceMap;
    }
}
