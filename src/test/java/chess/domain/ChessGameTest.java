package chess.domain;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.WhiteTeam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThatCode;

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

    @Test
    @DisplayName("킹이 잡히면, isEnd가 true로 변한다.")
    void game_end_with_checkmate_test() {
        final ChessGame chessGame = new ChessGame(new BlackTeam(), new WhiteTeam());
        chessGame.move(Position.of("e2"), Position.of("e4"));
        chessGame.move(Position.of("f7"), Position.of("f5"));
        chessGame.move(Position.of("e4"), Position.of("f5"));
        chessGame.move(Position.of("g7"), Position.of("g5"));

        assertThat(chessGame.isEnd()).isFalse();

        chessGame.move(Position.of("d1"), Position.of("h5"));
        chessGame.move(Position.of("h7"), Position.of("h6"));
        chessGame.move(Position.of("h5"), Position.of("e8"));

        assertThat(chessGame.isEnd()).isTrue();
    }
}
