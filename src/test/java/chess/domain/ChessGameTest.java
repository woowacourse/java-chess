package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.generator.BlackGenerator;
import chess.domain.generator.CustomGenerator;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    private Player currentPlayer;
    private Player opponentPlayer;
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        currentPlayer = new Player(new CustomGenerator(), Team.WHITE);
        opponentPlayer = new Player(new BlackGenerator(), Team.BLACK);
        chessGame = new ChessGame(currentPlayer, opponentPlayer);
    }

    @Test
    @DisplayName("현재 위치에 현재 플레이어의 체스말이 없을 때, 예외를 발생시킨다.")
    void emptyCurrentPosition() {
        final Position currentPosition = new Position(7, 'a');
        final Position destinationPosition = new Position(6, 'a');

        assertThatThrownBy(() -> chessGame.move(currentPlayer, opponentPlayer, currentPosition, destinationPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("선택한 출발 위치에 체스말이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("이동할 위치에 현재 플레이어의 체스말이 있을 때, 예외를 발생시킨다.")
    void destinationHasCurrentPlayerPiece() {
        final Position currentPosition = new Position(1, 'a');
        final Position destinationPosition = new Position(1, 'b');

        assertThatThrownBy(() -> chessGame.move(currentPlayer, opponentPlayer, currentPosition, destinationPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("선택한 도착 위치에 이미 체스말이 존재합니다.");
    }

    @Test
    @DisplayName("이동할 위치 중간에 체스말이 존재해서 이동할 수 없는 경우, 예외를 발생시킨다.")
    void hasPieceBetweenPosition() {
        final Position currentPosition = new Position(1, 'a');
        final Position destinationPosition = new Position(8, 'a');

        assertThatThrownBy(() -> chessGame.move(currentPlayer, opponentPlayer, currentPosition, destinationPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 체스말이 존재합니다.");
    }

    @Test
    @DisplayName("이동할 위치에 상대 플레이어의 체스말이 있을 때, Capture 한다.")
    void destinationHasOpponentPlayerPiece() {
        final Position currentPosition = new Position(1, 'a');
        final Position destinationPosition = new Position(7, 'a');

        final List<Piece> actual = chessGame.move(currentPlayer, opponentPlayer, currentPosition, destinationPosition);

        assertThat(actual).contains(new Rook(destinationPosition));
    }

    @Test
    @DisplayName("이동할 위치에 체스말이 없는 경우, move 한다.")
    void emptyDestinationPosition() {
        final Position currentPosition = new Position(1, 'a');
        final Position destinationPosition = new Position(5, 'a');

        final List<Piece> actual = chessGame.move(currentPlayer, opponentPlayer, currentPosition, destinationPosition);

        assertThat(actual).contains(new Rook(destinationPosition));
    }
}
