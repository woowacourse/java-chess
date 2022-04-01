package chess.domain.state;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public class BlackTurn extends Playing {
    public BlackTurn(Map<Position, Piece> board) {
        super(board);
    }

    @Override
    Playing turn() {
        return new WhiteTurn(getBoard());
    }

    @Override
    void validateTeam(Piece piece) {
        if (!Team.BLACK.matchTeam(piece.getTeam())) {
            throw new IllegalArgumentException("상대편 말을 옮길 수 없습니다.");
        }
    }

    @Override
    GameState finished() {
        return new Finished(Team.BLACK, getBoard());
    }

    @Override
    public Team getTeam() {
        return Team.BLACK;
    }
}
