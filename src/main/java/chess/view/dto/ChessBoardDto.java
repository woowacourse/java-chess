package chess.view.dto;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessBoardDto {

    private static final Map<PieceType, String> pieceName = Map.of(
            PieceType.BLACK_PAWN, "P",
            PieceType.WHITE_PAWN, "p",
            PieceType.ROOK, "r",
            PieceType.KNIGHT, "n",
            PieceType.BISHOP, "b",
            PieceType.KING, "k",
            PieceType.QUEEN, "q"
    );
    private static final String EMPTY = ".";

    private final List<String> lines;

    private ChessBoardDto(final List<String> lines) {
        this.lines = lines;
    }

    public static ChessBoardDto from(final ChessGame chessGame) {
        final List<String> lines = new ArrayList<>();
        for (final Rank rank : Rank.values()) {
            lines.add(generateLine(chessGame.getBoard(), rank));
        }
        Collections.reverse(lines);
        return new ChessBoardDto(lines);
    }

    private static String generateLine(final Board board, final Rank rank) {
        final StringBuilder line = new StringBuilder();
        for (final File file : File.values()) {
            line.append(generateSquareView(board, new Square(file, rank)));
        }
        return line.toString();
    }

    private static String generateSquareView(final Board board, final Square square) {
        final Optional<Piece> pieceOf = board.findPieceOf(square);
        if (pieceOf.isPresent()) {
            return toUpperCaseIfBlack(pieceOf.get());
        }
        return EMPTY;
    }

    private static String toUpperCaseIfBlack(final Piece piece) {
        if (piece.isBlack()) {
            return pieceName.get(piece.getPieceType()).toUpperCase();
        }
        return pieceName.get(piece.getPieceType());
    }

    public List<String> getLines() {
        return lines;
    }
}
