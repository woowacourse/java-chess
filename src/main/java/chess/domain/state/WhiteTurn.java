package chess.domain.state;

import chess.domain.*;
import chess.domain.piece.Piece;

import java.util.EnumMap;
import java.util.Map;

public class WhiteTurn extends Playing{

    public WhiteTurn(Map<Position, Piece> board) {
        super(board);
    }

    @Override
    Playing turn() {
        return new BlackTurn(getBoard());
    }

    @Override
    void validateTeam(Piece piece) {
        if (!Team.WHITE.matchTeam(piece.getTeam())) {
            throw new IllegalArgumentException("상대편 말을 옮길 수 없습니다.");
        }
    }

    @Override
    GameState finished() {
        return new Finished(Team.WHITE, getBoard());
    }

    @Override
    public Team getTeam() {
        return Team.WHITE;
    }
}
