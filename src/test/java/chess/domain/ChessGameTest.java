package chess.domain;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.WhiteTeam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

public class ChessGameTest {
    @Test
    @DisplayName("체스 게임이 정상 생성되는지 확인한다")
    void init() {
        assertThatCode(() -> new ChessGame(new BlackTeam(), new WhiteTeam()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("정상적으로 움직일 수 있는 좌표가 주어지면, 이동한다.")
    void move_chess_piece_when_valid_destination_is_given() {
        final ChessGame chessGame = new ChessGame(new BlackTeam(), new WhiteTeam());
        chessGame.move(Position.of("e2"), Position.of("e4"));

        final Map<Position, Piece> chessBoard = chessGame.generateChessBoard();
        assertThat(chessBoard.get(Position.of("e4"))).isInstanceOf(Pawn.class);
    }
}
