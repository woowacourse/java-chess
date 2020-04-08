package chess.game;

import chess.location.Col;
import chess.location.Location;
import chess.piece.type.Pawn;
import chess.piece.type.Piece;
import chess.score.Score;
import chess.team.Team;

import java.util.Map;
import java.util.Objects;

public class ChessSet {
    private final Map<Location, Piece> chessSet;

    public ChessSet(Map<Location, Piece> chessSet) {
        this.chessSet = chessSet;
    }

    public Score calculateScoreExceptPawnReduce() {
        return chessSet.values().stream()
                .map(Piece::getScore)
                .reduce(Score::plus)
                .get();
    }

    public void remove(Location location) {
        chessSet.remove(location);
    }

    public boolean hasNotKing() {
        return chessSet.values().stream()
                .noneMatch(Piece::isKing);
    }

    public void movePiece(Location location, Location after) {
        chessSet.put(after, chessSet.remove(location));
    }

    public int calculateExistPawnSizeInSame(Col col) {
        int count = 0;
        for (Map.Entry<Location, Piece> element : chessSet.entrySet()) {
            if (element.getKey().isSame(col)
                    && element.getValue().isSameTeam(getTeam())
                    && element.getValue() instanceof Pawn
            ) {
                count++;
            }
        }
        return count;
    }

    public Team getTeam() {
        Piece piece = chessSet.values().stream()
                .filter(Objects::nonNull)
                .findAny()
                .get();

        return piece.getTeam();
    }
}
