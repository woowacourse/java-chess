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
        initSetting();
    }

    public Board getBoard() {
        return new Board(board);
    }

    private void initSetting() {
        List<Piece> black = new ArrayList<>();
        List<Piece> white = new ArrayList<>();

        pawnInitSetting(black, white);
        rookInitSetting(black, white);
        knightInitSetting(black, white);
        bishopInitSetting(black, white);
        queenInitSetting(black, white);
        kingInitSetting(black, white);

        makeBoard(black, white);
    }

    private void pawnInitSetting(final List<Piece> black, final List<Piece> white) {
        List<Integer> cols = Col.getPawnInitCols();
        cols.forEach((col) -> {
            black.add(Pawn.of(Team.BLACK, col));
            white.add(Pawn.of(Team.WHITE, col));
        });
    }

    private void rookInitSetting(final List<Piece> black, final List<Piece> white) {
        List<Integer> cols = Col.getRookInitCols();

        cols.forEach((col) -> {
            black.add(Rook.of(Team.BLACK, col));
            white.add(Rook.of(Team.WHITE, col));
        });
    }

    private void knightInitSetting(final List<Piece> black, final List<Piece> white) {
        List<Integer> cols = Col.getKnightInitCols();

        cols.forEach((col) -> {
            black.add(Knight.of(Team.BLACK, col));
            white.add(Knight.of(Team.WHITE, col));
        });
    }

    private void bishopInitSetting(final List<Piece> black, final List<Piece> white) {
        List<Integer> cols = Col.getBishopInitCols();

        cols.forEach((col) -> {
            black.add(Bishop.of(Team.BLACK, col));
            white.add(Bishop.of(Team.WHITE, col));
        });
    }

    private void queenInitSetting(final List<Piece> black, final List<Piece> white) {
        List<Integer> cols = Col.getQueenInitCols();

        cols.forEach((col) -> {
            black.add(Queen.of(Team.BLACK, col));
            white.add(Queen.of(Team.WHITE, col));
        });
    }

    private void kingInitSetting(final List<Piece> black, final List<Piece> white) {
        List<Integer> cols = Col.getKingInitCols();

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

