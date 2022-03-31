package chess.domain;

import static chess.domain.Row.EIGHT;
import static chess.domain.Row.ONE;
import static chess.domain.Row.SEVEN;
import static chess.domain.Row.TWO;
import static chess.domain.Row.find;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.EnumMap;
import java.util.Map;

public class BoardInitializer {

    public static Map<Row, Rank> initPieces() {
        Map<Row, Rank> board = new EnumMap<>(Row.class);
        board.put(EIGHT, createPiecesExceptPawn(Team.BLACK, 8));
        board.put(SEVEN, createPawn(Team.BLACK, 7));
        for (int i = 3; i <= 6; i++) {
            board.put(find(i), createBlank(i));
        }
        board.put(TWO, createPawn(Team.WHITE, 2));
        board.put(ONE, createPiecesExceptPawn(Team.WHITE, 1));
        return board;
    }

    private static Rank createPiecesExceptPawn(Team team, int row) {
        EnumMap<Column, Piece> pieces = new EnumMap<>(Column.class);
        pieces.put(Column.A, new Rook(team, new Position(Column.A, Row.find(row))));
        pieces.put(Column.B, new Knight(team, new Position(Column.B, Row.find(row))));
        pieces.put(Column.C, new Bishop(team, new Position(Column.C, Row.find(row))));
        pieces.put(Column.D, new Queen(team, new Position(Column.D, Row.find(row))));
        pieces.put(Column.E, new King(team, new Position(Column.E, Row.find(row))));
        pieces.put(Column.F, new Bishop(team, new Position(Column.F, Row.find(row))));
        pieces.put(Column.G, new Knight(team, new Position(Column.G, Row.find(row))));
        pieces.put(Column.H, new Rook(team, new Position(Column.H, Row.find(row))));
        return new Rank(pieces);
    }

    private static Rank createBlank(int row) {
        return new Rank(Blank.from(row, Team.NONE));
    }

    private static Rank createPawn(Team team, int row) {
        return new Rank(Pawn.from(row, team));
    }
}
