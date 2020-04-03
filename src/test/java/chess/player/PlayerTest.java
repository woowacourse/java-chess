package chess.player;

import chess.board.ChessBoard;
import chess.board.ChessBoardCreater;
import chess.game.ChessSet;
import chess.location.Location;
import chess.piece.type.Piece;
import chess.score.Score;
import chess.team.Team;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void isNotSame() {
        // given
        // when
        // then
    }

    @Test
    void deletePieceIfExistIn() {
        // given
        // when
        // then
    }

    @Test
    void hasNotKing() {
        // given
        // when
        // then
    }

    @Test
    void calculateScoreExceptPawnReduce() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        Map<Location, Piece> locationPieceMap = chessBoard.giveMyPiece(Team.WHITE);
        Player player = new Player(new ChessSet(locationPieceMap), Team.WHITE);

        assertThat(player.calculateScoreExceptPawnReduce()).isEqualTo(new Score(38));
    }
}