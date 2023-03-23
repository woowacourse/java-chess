package chess.view.dto;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.game.ChessGame;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessBoardDto {

    private static final Map<Class<? extends Piece>, String> pieceName = Map.of(
            Pawn.class, "p",
            Rook.class, "r",
            Knight.class, "n",
            Bishop.class, "b",
            Queen.class, "q",
            King.class, "k"
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
            return pieceName.get(piece.getClass()).toUpperCase();
        }
        return pieceName.get(piece.getClass());
    }

    public List<String> getLines() {
        return lines;
    }
}
