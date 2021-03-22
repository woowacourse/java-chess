package chess.domain.piece;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;

import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PieceFactory {

    public static List<Piece> initialPieces(int boardSize, int startLine, int endLine) {
        List<Piece> pieces = new ArrayList<>();

        pieces.addAll(piecesByColor(WHITE, startLine, endLine));
        pieces.addAll(pawnsByColor(WHITE, boardSize, startLine, endLine));

        pieces.addAll(piecesByColor(BLACK, startLine, endLine));
        pieces.addAll(pawnsByColor(BLACK, boardSize, startLine, endLine));

        return pieces;
    }

    private static List<Piece> piecesByColor(TeamColor teamColor, int startLine,
        int endLine) {
        int row = startLine;
        if (teamColor.isBlack()) {
            row = endLine;
        }
        return Arrays.asList(
            new Rook(teamColor, Position.of(0, row)),
            new Knight(teamColor, Position.of(1, row)),
            new Bishop(teamColor, Position.of(2, row)),
            new Queen(teamColor, Position.of(3, row)),
            new King(teamColor, Position.of(4, row)),
            new Bishop(teamColor, Position.of(5, row)),
            new Knight(teamColor, Position.of(6, row)),
            new Rook(teamColor, Position.of(7, row))
        );
    }

    private static List<Piece> pawnsByColor(TeamColor teamColor, int boardSize, int startLine,
        int endLine) {
        List<Piece> pieces = new ArrayList<>();

        int row = startLine + 1;
        if (teamColor.isBlack()) {
            row = endLine - 1;
        }

        for (int column = 0; column < boardSize; column++) {
            pieces.add(new Pawn(teamColor, Position.of(column, row)));
        }
        return pieces;
    }
}
