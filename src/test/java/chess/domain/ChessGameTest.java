package chess.domain;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThatCode;

public class ChessGameTest {
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(Team.blackTeam(), Team.whiteTeam());
    }

    @Test
    @DisplayName("체스 게임이 정상 생성되는지 확인한다")
    void init() {
        assertThatCode(() -> new ChessGame(Team.blackTeam(), Team.whiteTeam()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("정상적으로 움직일 수 있는 좌표가 주어지면, 이동한다.")
    void move_chess_piece_when_valid_destination_is_given() {
        chessGame.move(Position.of("e2"), Position.of("e4"));

        final Map<Position, Piece> chessBoard = chessGame.generateChessBoard();
        assertThat(chessBoard.get(Position.of("e4"))).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("킹이 잡히면, isEnd가 true로 변한다.")
    void game_end_with_checkmate_test() {
        chessGame.move(Position.of("e2"), Position.of("e4"));
        chessGame.move(Position.of("f7"), Position.of("f5"));
        chessGame.move(Position.of("e4"), Position.of("f5"));
        chessGame.move(Position.of("g7"), Position.of("g5"));
        assertThat(chessGame.isPlaying()).isTrue();

        chessGame.move(Position.of("d1"), Position.of("h5"));
        chessGame.move(Position.of("h7"), Position.of("h6"));
        chessGame.move(Position.of("h5"), Position.of("e8"));
        assertThat(chessGame.isPlaying()).isFalse();
    }

    @Test
    @DisplayName("캐슬링이 정상적으로 이루어질 수 있다면, 캐슬링을 시행한다.")
    void castling_move_test() {
        chessGame.move(Position.of("e2"), Position.of("e4"));
        chessGame.move(Position.of("h7"), Position.of("h6"));
        chessGame.move(Position.of("f1"), Position.of("c4"));
        chessGame.move(Position.of("h6"), Position.of("h5"));
        chessGame.move(Position.of("g1"), Position.of("f3"));
        chessGame.move(Position.of("h5"), Position.of("h4"));

        assertThatCode(() -> chessGame.move(Position.of("e1"), Position.of("g1")))
                .doesNotThrowAnyException();

        final Map<Position, Piece> piecePosition = chessGame.generateChessBoard();
        assertThat(piecePosition.get(Position.of("g1")).isKing()).isTrue();
        assertThat(piecePosition.get(Position.of("f1")).isRook()).isTrue();
    }

    @Test
    @DisplayName("끝의 행까지 이동한 폰의 경우, 퀸으로 승격시킨다.")
    void when_pawn_arrive_at_last_rank_promote() {
        chessGame.move(Position.of("c2"), Position.of("c4"));
        chessGame.move(Position.of("b7"), Position.of("b5"));
        chessGame.move(Position.of("c4"), Position.of("b5"));
        chessGame.move(Position.of("b8"), Position.of("c6"));
        chessGame.move(Position.of("b5"), Position.of("b6"));
        chessGame.move(Position.of("c8"), Position.of("a6"));
        chessGame.move(Position.of("b6"), Position.of("b7"));

        Map<Position, Piece> piecePosition = chessGame.generateChessBoard();
        assertThat(piecePosition.get(Position.of("b7"))).isInstanceOf(Pawn.class);

        chessGame.move(Position.of("d7"), Position.of("d5"));
        chessGame.move(Position.of("b7"), Position.of("b8"));
        piecePosition = chessGame.generateChessBoard();
        assertThat(piecePosition.get(Position.of("b8"))).isInstanceOf(Queen.class);
    }
}
