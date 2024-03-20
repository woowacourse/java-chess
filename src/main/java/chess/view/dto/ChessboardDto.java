package chess.view.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Position;
import chess.domain.attribute.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class ChessboardDto {

    private static final String EMPTY = ".";

    private final List<List<String>> chessboard;

    public ChessboardDto(final Map<Position, Piece> chessboard) {
        this.chessboard = new ArrayList<>();
        for (final Rank rank : Rank.values()) {
            List<String> squares = new ArrayList<>();
            this.chessboard.add(squares);
            for (final File file : File.values()) {
                String value = EMPTY;
                Position position = Position.of(file, rank);
                if (chessboard.containsKey(position)) {
                    Piece piece = chessboard.get(position);
                    value = parsePieceType(piece);
                }
                squares.add(value);
            }
        }
    }

    private String parsePieceType(final Piece piece) {
        final PieceType pieceType = piece.getPieceType();
        if (pieceType == PieceType.KING) {
            return parsePieceTypeByColor("k", piece.getColor());
        }
        if (pieceType == PieceType.QUEEN) {
            return parsePieceTypeByColor("q", piece.getColor());
        }
        if (pieceType == PieceType.BISHOP) {
            return parsePieceTypeByColor("b", piece.getColor());
        }
        if (pieceType == PieceType.KNIGHT) {
            return parsePieceTypeByColor("n", piece.getColor());
        }
        if (pieceType == PieceType.ROOK) {
            return parsePieceTypeByColor("r", piece.getColor());
        }
        return parsePieceTypeByColor("p", piece.getColor());
    }

    private String parsePieceTypeByColor(final String pieceName, final Color color) {
        if (color == Color.WHITE) {
            return pieceName.toLowerCase();
        }
        return pieceName.toUpperCase();
    }

    public List<List<String>> getChessboard() {
        return chessboard;
    }
}
