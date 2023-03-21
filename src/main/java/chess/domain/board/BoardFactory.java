package chess.domain.board;

import chess.domain.board.position.Column;
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

        for (int column = Column.START.value(); column <= Column.END.value(); column++) {
            final Position whitePosition = new Position(column, WhitePiecePositionConstant.PAWN_ROW.value());
            final Position blackPosition = new Position(column, BlackPiecePositionConstant.PAWN_ROW.value());
            pieceMap.put(whitePosition, new Pawn(Color.WHITE));
            pieceMap.put(blackPosition, new Pawn(Color.BLACK));
        }

        return pieceMap;
    }

    private Map<Position, Piece> createKing() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whitePosition = new Position(WhitePiecePositionConstant.KING_COLUMN.value(),
                                                    WhitePiecePositionConstant.KING_ROW.value());
        pieceMap.put(whitePosition, new King(Color.WHITE));

        final Position blackPosition = new Position(BlackPiecePositionConstant.KING_COLUMN.value(),
                                                    BlackPiecePositionConstant.KING_ROW.value());

        pieceMap.put(blackPosition, new King(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createQueen() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whitePosition = new Position(WhitePiecePositionConstant.QUEEN_COLUMN.value(),
                                                    WhitePiecePositionConstant.QUEEN_ROW.value());
        final Position blackPosition = new Position(BlackPiecePositionConstant.QUEEN_COLUMN.value(),
                                                    BlackPiecePositionConstant.QUEEN_ROW.value());

        pieceMap.put(whitePosition, new Queen(Color.WHITE));
        pieceMap.put(blackPosition, new Queen(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createBishop() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whiteLeftPosition = new Position(WhitePiecePositionConstant.LEFT_BISHOP_COLUMN.value(),
                                                        WhitePiecePositionConstant.LEFT_BISHOP_ROW.value());

        final Position whiteRightPosition = new Position(WhitePiecePositionConstant.RIGHT_BISHOP_COLUMN.value(),
                                                         WhitePiecePositionConstant.RIGHT_BISHOP_ROW.value());

        final Position blackLeftPosition = new Position(BlackPiecePositionConstant.LEFT_BISHOP_COLUMN.value(),
                                                        BlackPiecePositionConstant.LEFT_BISHOP_ROW.value());

        final Position blackRightPosition = new Position(BlackPiecePositionConstant.RIGHT_BISHOP_COLUMN.value(),
                                                         BlackPiecePositionConstant.RIGHT_BISHOP_ROW.value());


        pieceMap.put(whiteLeftPosition, new Bishop(Color.WHITE));
        pieceMap.put(whiteRightPosition, new Bishop(Color.WHITE));
        pieceMap.put(blackLeftPosition, new Bishop(Color.BLACK));
        pieceMap.put(blackRightPosition, new Bishop(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createRook() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whiteLeftPosition = new Position(WhitePiecePositionConstant.LEFT_ROOK_COLUMN.value(),
                                                        WhitePiecePositionConstant.LEFT_ROOK_ROW.value());
        final Position whiteRightPosition = new Position(WhitePiecePositionConstant.RIGHT_ROOK_COLUMN.value(),
                                                         WhitePiecePositionConstant.RIGHT_ROOK_ROW.value());
        final Position blackLeftPosition = new Position(BlackPiecePositionConstant.LEFT_ROOK_COLUMN.value(),
                                                        BlackPiecePositionConstant.LEFT_ROOK_ROW.value());
        final Position blackRightPosition = new Position(BlackPiecePositionConstant.RIGHT_ROOK_COLUMN.value(),
                                                         BlackPiecePositionConstant.RIGHT_ROOK_ROW.value());

        pieceMap.put(whiteLeftPosition, new Rook(Color.WHITE));
        pieceMap.put(whiteRightPosition, new Rook(Color.WHITE));
        pieceMap.put(blackLeftPosition, new Rook(Color.BLACK));
        pieceMap.put(blackRightPosition, new Rook(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createKnight() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whiteLeftPosition = new Position(WhitePiecePositionConstant.LEFT_KNIGHT_COLUMN.value(),
                                                        WhitePiecePositionConstant.LEFT_KNIGHT_ROW.value());

        final Position whiteRightPosition = new Position(WhitePiecePositionConstant.RIGHT_KNIGHT_COLUMN.value(),
                                                         WhitePiecePositionConstant.RIGHT_KNIGHT_ROW.value());

        final Position blackLeftPosition = new Position(BlackPiecePositionConstant.LEFT_KNIGHT_COLUMN.value(),
                                                        BlackPiecePositionConstant.LEFT_KNIGHT_ROW.value());

        final Position blackRightPosition = new Position(BlackPiecePositionConstant.RIGHT_KNIGHT_COLUMN.value(),
                                                         BlackPiecePositionConstant.RIGHT_KNIGHT_ROW.value());

        pieceMap.put(whiteLeftPosition, new Knight(Color.WHITE));
        pieceMap.put(whiteRightPosition, new Knight(Color.WHITE));
        pieceMap.put(blackLeftPosition, new Knight(Color.BLACK));
        pieceMap.put(blackRightPosition, new Knight(Color.BLACK));

        return pieceMap;
    }
}
