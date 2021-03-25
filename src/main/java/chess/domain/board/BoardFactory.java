package chess.domain.board;

import chess.domain.Team;
import chess.domain.pieces.*;
import chess.domain.position.Col;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BoardFactory {
    private final Map<Team, Pieces> board = new HashMap<>();

    public BoardFactory() {
        initialize();
    }

    public Board board() {
        return new Board(board);
    }

    private void initialize() {
        List<Piece> black = new ArrayList<>();
        List<Piece> white = new ArrayList<>();

        pawnInitialize(black, white);
        rookInitialize(black, white);
        knightInitialize(black, white);
        bishopInitialize(black, white);
        queenInitialize(black, white);
        kingInitialize(black, white);

        makeBoard(black, white);
    }

    private void pawnInitialize(final List<Piece> black, final List<Piece> white) {
        List<Integer> cols = Col.pawnInitCols();
        cols.forEach((col) -> {
            black.add(Pawn.of(Team.BLACK, col));
            white.add(Pawn.of(Team.WHITE, col));
        });
    }

    private void rookInitialize(final List<Piece> black, final List<Piece> white) {
        List<Integer> cols = Col.rookInitCols();
        cols.forEach((col) -> {
            black.add(Rook.of(Team.BLACK, col));
            white.add(Rook.of(Team.WHITE, col));
        });
    }

    private void knightInitialize(final List<Piece> black, final List<Piece> white) {
        List<Integer> cols = Col.knightInitCols();
        cols.forEach((col) -> {
            black.add(Knight.of(Team.BLACK, col));
            white.add(Knight.of(Team.WHITE, col));
        });
    }

    private void bishopInitialize(final List<Piece> black, final List<Piece> white) {
        List<Integer> cols = Col.bishopInitCols();
        cols.forEach((col) -> {
            black.add(Bishop.of(Team.BLACK, col));
            white.add(Bishop.of(Team.WHITE, col));
        });
    }

    private void queenInitialize(final List<Piece> black, final List<Piece> white) {
        List<Integer> cols = Col.queenInitCols();
        cols.forEach((col) -> {
            black.add(Queen.of(Team.BLACK, col));
            white.add(Queen.of(Team.WHITE, col));
        });
    }

    private void kingInitialize(final List<Piece> black, final List<Piece> white) {
        List<Integer> cols = Col.kingInitCols();
        cols.forEach((col) -> {
            black.add(King.of(Team.BLACK, col));
            white.add(King.of(Team.WHITE, col));
        });
    }

    private void makeBoard(final List<Piece> black, final List<Piece> white) {
        board.put(Team.BLACK, new Pieces(black));
        board.put(Team.WHITE, new Pieces(white));
    }
}

