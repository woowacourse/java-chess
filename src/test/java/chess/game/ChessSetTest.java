package chess.game;

import chess.board.ChessBoard;
import chess.board.ChessBoardCreater;
import chess.location.Col;
import chess.location.Location;
import chess.location.Row;
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
}