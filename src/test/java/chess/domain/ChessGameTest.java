package chess.domain;

import chess.domain.generator.BlackGenerator;
import chess.domain.generator.CustomGenerator;
import chess.domain.generator.NoKingCustomGenerator;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.player.Player;
import chess.domain.player.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    private Player currentPlayer;
    private Player opponentPlayer;
    private ChessGame chessGame;

    @Test
    @DisplayName("현재 위치에 현재 플레이어의 체스말이 없을 때, 예외를 발생시킨다.")
    void emptyCurrentPosition() {
        initializeChessGame();

        final Position currentPosition = Position.of(7, 'a');
        final Position destinationPosition = Position.of(6, 'a');

        assertThatThrownBy(() -> chessGame.move(currentPlayer, opponentPlayer, currentPosition, destinationPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("선택한 출발 위치에 현재 턴에 해당하는 팀의 체스말이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("이동할 위치에 현재 플레이어의 체스말이 있을 때, 예외를 발생시킨다.")
    void destinationHasCurrentPlayerPiece() {
        initializeChessGame();

        final Position currentPosition = Position.of(1, 'a');
        final Position destinationPosition = Position.of(1, 'b');

        assertThatThrownBy(() -> chessGame.move(currentPlayer, opponentPlayer, currentPosition, destinationPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("선택한 도착 위치에 이미 체스말이 존재합니다.");
    }

    @Test
    @DisplayName("이동할 위치 중간에 체스말이 존재해서 이동할 수 없는 경우, 예외를 발생시킨다.")
    void hasPieceBetweenPosition() {
        initializeChessGame();

        final Position currentPosition = Position.of(1, 'a');
        final Position destinationPosition = Position.of(8, 'a');

        assertThatThrownBy(() -> chessGame.move(currentPlayer, opponentPlayer, currentPosition, destinationPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 체스말이 존재합니다.");
    }

    @Test
    @DisplayName("이동할 위치에 상대 플레이어의 체스말이 있을 때, Capture 한다.")
    void destinationHasOpponentPlayerPiece() {
        initializeChessGame();

        final Position currentPosition = Position.of(1, 'a');
        final Position destinationPosition = Position.of(7, 'a');

        final List<Piece> actual = chessGame.move(currentPlayer, opponentPlayer, currentPosition, destinationPosition);

        assertThat(actual).contains(new Rook(destinationPosition));
    }

    @Test
    @DisplayName("이동할 위치에 체스말이 없는 경우, move 한다.")
    void emptyDestinationPosition() {
        initializeChessGame();

        final Position currentPosition = Position.of(1, 'a');
        final Position destinationPosition = Position.of(5, 'a');

        final List<Piece> actual = chessGame.move(currentPlayer, opponentPlayer, currentPosition, destinationPosition);

        assertThat(actual).contains(new Rook(destinationPosition));
    }

    @Test
    @DisplayName("내 점수가 더 낮지만, 상대 플레이어가 King이 없으면 승리한다.")
    void isWin() {
        initializeNoKingPlayerChessGame();

        assertThat(chessGame.isWin(currentPlayer, opponentPlayer)).isTrue();
    }

    @Test
    @DisplayName("상대 플레이어가 King이 없을 때 승리한다.")
    void isWinByCaptureKing() {
        initializeNoKingPlayerChessGame();

        assertThat(chessGame.hasNoKing(opponentPlayer)).isTrue();
    }

    @Test
    @DisplayName("내가 점수가 더 높은 경우 승리한다.")
    void isWinByHigherScore() {
        initializeChessGame();

        assertThat(chessGame.isHigherScore(opponentPlayer, currentPlayer)).isTrue();
    }

    private void initializeChessGame() {
        currentPlayer = new Player(new CustomGenerator(), Team.WHITE);
        opponentPlayer = new Player(new BlackGenerator(), Team.BLACK);
        chessGame = new ChessGame(currentPlayer, opponentPlayer);
    }

    private void initializeNoKingPlayerChessGame() {
        currentPlayer = new Player(new CustomGenerator(), Team.WHITE);
        opponentPlayer = new Player(new NoKingCustomGenerator(), Team.BLACK);
        chessGame = new ChessGame(currentPlayer, opponentPlayer);
    }
}
