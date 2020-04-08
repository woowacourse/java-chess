package chess.game;

import chess.location.Col;
import chess.location.Location;
import chess.piece.type.Pawn;
import chess.piece.type.Piece;
import chess.score.Score;
import chess.team.Team;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class ChessSet {
    private static final String NO_ELEMENT_IN_CHESS_SET_MESSAGE = "ChessSet에 아이템이 존재하지 않습니다.";
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
        for (Piece piece: chessSet.values()) {
            if(Objects.nonNull(piece)) {
                return piece.getTeam();
            }
        }

        throw new NoSuchElementException(NO_ELEMENT_IN_CHESS_SET_MESSAGE);
    }
}
