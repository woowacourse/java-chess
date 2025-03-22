package view.support;

import static chess.Color.*;
import static chess.piece.PieceType.*;

import chess.Color;
import chess.Turn;
import chess.board.Column;
import chess.board.Position;
import chess.board.Row;
import chess.piece.Piece;
import chess.piece.PieceType;
import java.util.HashMap;
import java.util.Map;

public class OutputSupporter {

    private static final Map<Position, Piece> EMPTY_PIECES = new HashMap<>();
    private static final Map<PieceType, String> PIECE_TYPE_FORMATTER = new HashMap<>();
    private static final Map<Color, String> COLOR_FORMATTER = new HashMap<>();

    static {
        PIECE_TYPE_FORMATTER.put(KING, "K");
        PIECE_TYPE_FORMATTER.put(ROOK, "R");
        PIECE_TYPE_FORMATTER.put(BISHOP, "B");
        PIECE_TYPE_FORMATTER.put(QUEEN, "Q");
        PIECE_TYPE_FORMATTER.put(PAWN, "P");
        PIECE_TYPE_FORMATTER.put(KNIGHT, "N");
    }

    static {
        COLOR_FORMATTER.put(BLACK, "B");
        COLOR_FORMATTER.put(WHITE, "W");
    }

    public Map<Position, Piece> fillBoard(Map<Position, Piece> boardPieces) {
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                EMPTY_PIECES.put(new Position(row, column), null);
            }
        }
        EMPTY_PIECES.putAll(boardPieces);
        return EMPTY_PIECES;
    }

    public String formatPiece(Piece piece) {
        PieceType pieceType = piece.getPieceType();
        Color color = piece.getColor();
        return COLOR_FORMATTER.get(color) + PIECE_TYPE_FORMATTER.get(pieceType);
    }

    public String formatTurn(Turn currentTurn) {
        if (currentTurn == Turn.BLACK_TURN) {
            return "흑색";
        }
        return "백색";
    }
}
