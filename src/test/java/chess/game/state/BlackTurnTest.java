package chess.game.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.board.Board;
import chess.board.BoardInitializer;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Piece;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackTurnTest {

    @Test
    @DisplayName("흑의 턴이 움직이면 백 차례 상태로 전이한다.")
    void whiteStateAfterMovingBlack() {
        // given
        Board board = BoardInitializer.createBoard();
        BlackTurn blackTurn = new BlackTurn();
        Position source = Position.of(File.A, Rank.SEVEN);
        Position destination = Position.of(File.A, Rank.FIVE);
        // when
        GameState actual = blackTurn.proceedTurn(board, source, destination);
        // then
        assertThat(actual).isInstanceOf(WhiteTurn.class);
    }

    @Test
    @DisplayName("상대 킹을 잡으면 게임 종료 상태로 전이된다.")
    void terminateOnKingCaptureTest() {
        // given
        Map<Position, Piece> pieces = new HashMap<>();
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.B, Rank.TWO);
        pieces.put(source, new King(Color.BLACK));
        pieces.put(destination, new King(Color.WHITE));
        Board board = new Board(pieces);

        BlackTurn blackTurn = new BlackTurn();
        // when
        GameState actual = blackTurn.proceedTurn(board, source, destination);
        // then
        assertThat(actual).isInstanceOf(TerminatedState.class);
    }
}
