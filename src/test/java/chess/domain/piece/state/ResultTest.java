package chess.domain.piece.state;

import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @Test
    void concludeWithInitializedPieces() {
        String initialScore = "38.0";
        PiecesState piecesState = PiecesState.initialize();
        Result result = Result.conclude(piecesState);
        assertThat(result.getWinner()).isEqualTo(Team.NOT_ASSIGNED.toString());
        assertThat(result.getWhiteScore()).isEqualTo(initialScore);
        assertThat(result.getBlackScore()).isEqualTo(initialScore);
    }

    @Test
    void concludeWithWhiteWinPieces() {
        String whiteScore = "38.0";
        String blackScore = "37.0";
        PiecesState piecesState = PiecesState.initialize();
        killBlackKingOnPurpose(piecesState);
        Result result = Result.conclude(piecesState);
        assertThat(result.getWinner()).isEqualTo(Team.WHITE.toString());
        assertThat(result.getWhiteScore()).isEqualTo(whiteScore);
        assertThat(result.getBlackScore()).isEqualTo(blackScore);
    }

    private void killBlackKingOnPurpose(PiecesState piecesState) {
        piecesState.movePiece(Position.of(2,1), Position.of(3,3));
        piecesState.movePiece(Position.of(3,3), Position.of(2,5));
        piecesState.movePiece(Position.of(2,5), Position.of(3,7));
        piecesState.movePiece(Position.of(3,7), Position.of(5,8));
    }
}