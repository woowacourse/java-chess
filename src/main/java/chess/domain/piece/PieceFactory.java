package chess.domain.piece;

import static chess.domain.BoardSpecification.BOARD_SIZE;
import static chess.domain.BoardSpecification.END_LINE;
import static chess.domain.BoardSpecification.START_LINE;
import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;

import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PieceFactory {

    public static List<Piece> initialPieces() {
        List<Piece> pieces = new ArrayList<>();

        pieces.addAll(piecesByColor(WHITE));
        pieces.addAll(pawnsByColor(WHITE));

        pieces.addAll(piecesByColor(BLACK));
        pieces.addAll(pawnsByColor(BLACK));

        return pieces;
    }

    private static List<Piece> piecesByColor(TeamColor teamColor) {
        int row = START_LINE;
        if (teamColor == BLACK) {
            row = END_LINE;
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

    private static List<Piece> pawnsByColor(TeamColor teamColor) {
        List<Piece> pieces = new ArrayList<>();

        int row = START_LINE + 1;
        if (teamColor == BLACK) {
            row = END_LINE - 1;
        }

        for (int column = 0; column < BOARD_SIZE; column++) {
            pieces.add(new Pawn(teamColor, Position.of(column, row)));
        }
        return pieces;
    }
}
