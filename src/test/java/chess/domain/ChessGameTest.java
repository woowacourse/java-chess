package chess.domain;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.command.MoveCommand;
import chess.domain.game.ChessGame;
import chess.domain.game.GameResult;
import chess.domain.piece.ChessmenInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import chess.dto.GameResultDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    ChessmenInitializer chessmenInitializer;
    ChessGame chessGame;


    @BeforeEach
    void setup_chessGame() {
        chessmenInitializer = new ChessmenInitializer();
        chessGame = ChessGame.of(chessmenInitializer.init());
    }

    @DisplayName("체스말이 이동할 수 있는 위치면 이동에 성공한다.")
    @Test
    void move_success() {
        MoveCommand command = new MoveCommand("move a2 a4");
        Position a4 = Position.of("a4");

        chessGame.moveChessmen(command);

        Position actual = chessGame.getChessmen()
            .extractPiece(Position.of("a4"))
            .getPosition();

        assertThat(actual).isEqualTo(a4);
    }

    @DisplayName("체스말이 이동할 수 없는 위치면 예외가 발생한다.")
    @Test
    void move_failOnInvalidMove() {
        MoveCommand command = new MoveCommand("move a2 a5");

        assertThatCode(() -> chessGame.moveChessmen(command))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("체스말의 이동경로에 다른 말이 있으면 예외가 발생한다.")
    @Test
    void move_failOnObstacleInPath() {
        MoveCommand command = new MoveCommand("move a1 a3");

        assertThatCode(() -> chessGame.moveChessmen(command))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("가는 길목에 다른 말이 있어 이동할 수 없습니다.");
    }

    @DisplayName("체스말의 이동하려는 위치에 아군말이 있으면 예외가 발생한다.")
    @Test
    void move_failOnFriendlyInTargetPosition() {
        MoveCommand command = new MoveCommand("move a1 a2");

        assertThatCode(() -> chessGame.moveChessmen(command))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동하려는 위치에 아군 말이 있습니다.");
    }

    @DisplayName("체스말의 이동하려는 위치면서 공격가능한 위치면 적군말을 공격한다.")
    @Test
    void kill_enemy() {
        MoveCommand command1 = new MoveCommand("move a2 a4");
        MoveCommand command2 = new MoveCommand("move b7 b5");
        MoveCommand command3 = new MoveCommand("move a4 b5");

        chessGame.moveChessmen(command1);
        chessGame.moveChessmen(command2);
        chessGame.moveChessmen(command3);

        Piece actual = chessGame.getChessmen().extractPiece(Position.of("b5"));
        Pawn expected = new Pawn(WHITE, Position.of("b5"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("게임 시작시 처음엔 백색말만 움직일 수 있다.")
    @Test
    void checkTurn_white() {
        MoveCommand command1 = new MoveCommand("move a2 a4");
        chessGame.moveChessmen(command1);

        Color actual = chessGame.getChessmen().extractPiece(Position.of("a4")).getColor();

        assertThat(actual).isEqualTo(WHITE);
    }

    @DisplayName("게임 시작시 처음에 흑색말을 움직이려는 경우 예외가 발생한다.")
    @Test
    void checkTurn_white_Exception() {
        MoveCommand command = new MoveCommand("move a7 a6");

        assertThatCode(() -> chessGame.moveChessmen(command))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("턴은 백색 말부터 시작해 한번씩 움직일 수 있습니다.");
    }

    @DisplayName("같은 색의 말을 연달아 두번 움직이려는 경우 예외가 발생한다.")
    @Test
    void checkTurn_black_Exception() {
        MoveCommand command1 = new MoveCommand("move a2 a4");
        MoveCommand command2 = new MoveCommand("move a4 a5");

        chessGame.moveChessmen(command1);

        assertThatCode(() -> chessGame.moveChessmen(command2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("턴은 백색 말부터 시작해 한번씩 움직일 수 있습니다.");
    }

    @DisplayName("흑, 백 King 모두 죽지 않으면 게임은 끝나지 않는다.")
    @Test
    void isEnd_false() {
        boolean actual = chessGame.isEnd();

        assertThat(actual).isFalse();
    }

    @DisplayName("King이 한 개라도 죽으면 게임은 끝난다.")
    @Test
    void isEnd_true() {
        chessGame = ChessGame.of(new Pieces(List.of(new King(WHITE, Position.of("e1")))));

        boolean actual = chessGame.isEnd();

        assertThat(actual).isTrue();
    }

    @DisplayName("최초의 게임 점수는 각각 38.0점이다.")
    @Test
    void calculateGameResult() {
        double actual = GameResult.calculate(chessGame.getChessmen())
            .getBlackScore();
        double expected = new GameResultDto(BLACK.getName(), 38.0, 38.0)
            .getBlackScore();

        assertThat(actual).isEqualTo(expected);
    }

}
