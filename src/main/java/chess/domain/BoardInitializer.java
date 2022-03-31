package chess.domain;

import static chess.domain.position.Row.EIGHT;
import static chess.domain.position.Row.ONE;
import static chess.domain.position.Row.SEVEN;
import static chess.domain.position.Row.TWO;
import static chess.domain.position.Row.find;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
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
        pieces.put(Column.A, new Rook(team, Position.from("a" + row)));
        pieces.put(Column.B, new Knight(team, Position.from("b" + row)));
        pieces.put(Column.C, new Bishop(team, Position.from("c" + row)));
        pieces.put(Column.D, new Queen(team, Position.from("d" + row)));
        pieces.put(Column.E, new King(team, Position.from("e" + row)));
        pieces.put(Column.F, new Bishop(team, Position.from("f" + row)));
        pieces.put(Column.G, new Knight(team, Position.from("g" + row)));
        pieces.put(Column.H, new Rook(team, Position.from("h" + row)));
        return new Rank(pieces);
    }

    private static Rank createBlank(int row) {
        return new Rank(Blank.of(row, Team.NONE));
    }

    private static Rank createPawn(Team team, int row) {
        return new Rank(Pawn.of(row, team));
    }
}
