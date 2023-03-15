package chess.domain.chessboard;

import chess.domain.chessboard.state.Team;
import chess.domain.chessboard.state.piece.Bishop;
import chess.domain.chessboard.state.piece.King;
import chess.domain.chessboard.state.piece.Knight;
import chess.domain.chessboard.state.piece.Pawn;
import chess.domain.chessboard.state.piece.Queen;
import chess.domain.chessboard.state.piece.Rook;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ChessBoard {

    private static final int FIXED_RANK_SIZE = 8;
    public static final int BLANK_RANK_SIZE = 4;

    private final List<Rank> ranks = new ArrayList<>(FIXED_RANK_SIZE);

    public ChessBoard() {
        initRanks();
    }

    private void initRanks() {
        ranks.add(createSideRankByTeam(Team.WHITE));
        ranks.add(createPawnRankByTeam(Team.WHITE));

        for (int rankIndex = 0; rankIndex < BLANK_RANK_SIZE; rankIndex++) {
            ranks.add(new Rank());
        }

        ranks.add(createPawnRankByTeam(Team.BLACK));
        ranks.add(createSideRankByTeam(Team.BLACK));
    }

    private static Rank createPawnRankByTeam(final Team team) {
        return new Rank(new Square(new Pawn(team)));
    }

    private Rank createSideRankByTeam(final Team team) {
        return new Rank(List.of(
                new Square(new Rook(team)),
                new Square(new Knight(team)),
                new Square(new Bishop(team)),
                new Square(new Queen(team)),
                new Square(new King(team)),
                new Square(new Bishop(team)),
                new Square(new Knight(team)),
                new Square(new Rook(team))));
    }

    public List<Rank> getRanks() {
        return Collections.unmodifiableList(ranks);
    }
}
