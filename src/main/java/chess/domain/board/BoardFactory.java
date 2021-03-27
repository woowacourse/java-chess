package chess.domain.board;

import chess.domain.Team;
import chess.domain.pieces.*;

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
        black.addAll(Pawn.getInitPawns(Team.BLACK));
        white.addAll(Pawn.getInitPawns(Team.WHITE));
    }

    private void rookInitSetting(final List<Piece> black, final List<Piece> white) {
        black.addAll(Rook.getInitRooks(Team.BLACK));
        white.addAll(Rook.getInitRooks(Team.WHITE));
    }

    private void knightInitSetting(final List<Piece> black, final List<Piece> white) {
        black.addAll(Knight.getInitKnights(Team.BLACK));
        white.addAll(Knight.getInitKnights(Team.WHITE));
    }

    private void bishopInitSetting(final List<Piece> black, final List<Piece> white) {
        black.addAll(Bishop.getInitBishop(Team.BLACK));
        white.addAll(Bishop.getInitBishop(Team.WHITE));
    }

    private void queenInitSetting(final List<Piece> black, final List<Piece> white) {
        black.addAll(Queen.getInitQueen(Team.BLACK));
        white.addAll(Queen.getInitQueen(Team.WHITE));
    }

    private void kingInitSetting(final List<Piece> black, final List<Piece> white) {
        black.addAll(King.getInitKing(Team.BLACK));
        white.addAll(King.getInitKing(Team.WHITE));
    }

    private void makeBoard(final List<Piece> black, final List<Piece> white) {
        board.put(Team.BLACK, new Pieces(black));
        board.put(Team.WHITE, new Pieces(white));
    }
}

