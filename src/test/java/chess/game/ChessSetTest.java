package chess.game;

import chess.board.ChessBoard;
import chess.board.ChessBoardCreater;
import chess.score.Score;
import chess.team.Team;
import jdk.jfr.events.SocketReadEvent;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChessSetTest {

    @Test
    void calculateScoreExceptPawnReduce() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        ChessSet chessSet = new ChessSet(chessBoard.giveMyPiece(Team.WHITE));
        assertThat(chessSet.calculateScoreExceptPawnReduce()).isEqualTo(new Score(38));
    }

    @Test
    void remove() {
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
}