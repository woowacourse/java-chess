package domain.board;

import domain.piece.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void setup() {
        chessGame = new ChessGame();
    }

    @Test
    @DisplayName("기물의 이동규칙 밖인 경우, 이동할 수 없다")
    void moveImpossible() {
        Coordinate start = new Coordinate(1, 0);
        Coordinate end = new Coordinate(0, 0);

        assertThatThrownBy(() -> chessGame.move(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("목적지에 기물이 이미 존재하는 경우, 이동할 수 없다")
    void moveImpossibleWhenExistsAtEnd() {
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(1, 0);

        assertThatThrownBy(() -> chessGame.move(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("경로상에 기물이 존재하는 경우, reap이 불가능한 기물은 이동할 수 없다")
    void moveImpossibleWhenBlockedAndCantReap() {
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(2, 0);

        assertThatThrownBy(() -> chessGame.move(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("경로상에 기물이 존재하는 경우, reap이 가능한 기물은 이동할 수 있다")
    void moveImpossibleWhenBlockedAndCanReap() {
        Coordinate start = new Coordinate(0, 1);
        Coordinate end = new Coordinate(2, 0);

        assertThatCode(() -> chessGame.move(start, end))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("일반적인 경우, 기물은 이동할 수 있다")
    void movePossible() {
        Coordinate start = new Coordinate(1, 0);
        Coordinate end = new Coordinate(3, 0);

        assertThatCode(() -> chessGame.move(start, end))
                .doesNotThrowAnyException();
    }


    @Test
    @DisplayName("블랙 팀은 처음에 기물을 움직일 수 없다")
    void moveImpossibleWhenTurnIsNotForBlack() {
        Coordinate start = new Coordinate(6, 0);
        Coordinate end = new Coordinate(5, 0);

        assertThatThrownBy(() -> chessGame.move(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("화이트 팀은 처음에 기물을 움직일 수 있다")
    void movePossibleWhenTurnIsForWhite() {
        Coordinate start = new Coordinate(1, 0);
        Coordinate end = new Coordinate(2, 0);

        assertThatCode(() -> chessGame.move(start, end))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("두번째 턴에는 블랙 팀이 기물을 움직일 수 있다")
    void movePossibleWhenTurnTwoForBlack() {
        Coordinate start = new Coordinate(6, 0);
        Coordinate end = new Coordinate(5, 0);

        chessGame.move(
                new Coordinate(1, 0),
                new Coordinate(2, 0)
        );

        assertThatCode(() -> chessGame.move(start, end))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("두번째 턴에는 화이트 팀이 기물을 움직일 수 없다")
    void moveImPossibleWhenTurnTwoForWhite() {
        Coordinate start = new Coordinate(1, 0);
        Coordinate end = new Coordinate(3, 0);

        chessGame.move(
                new Coordinate(1, 0),
                new Coordinate(2, 0)
        );

        assertThatCode(() -> chessGame.move(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("세번째 턴에는 화이트 팀이 기물을 움직일 수 있다")
    void movePossibleWhenTurnThreeForWhite() {
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(2, 1);

        chessGame.move(
                new Coordinate(1, 0),
                new Coordinate(2, 0)
        );
        chessGame.move(
                new Coordinate(6, 1),
                new Coordinate(5, 1)
        );

        assertThatCode(() -> chessGame.move(start, end))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("같은 팀이 아니라면 기물을 잡을 수 있다")
    void catchPossibleWhenNotSameCamp() {
        chessGame.move( // move Pawn
                new Coordinate(1, 1),
                new Coordinate(2, 1)
        );

        chessGame.move( // for change turn
                new Coordinate(6, 0),
                new Coordinate(5, 0)
        );

        chessGame.move( // move Bishop
                new Coordinate(0, 2),
                new Coordinate(2, 0)
        );

        chessGame.move( // for change turn
                new Coordinate(6, 1),
                new Coordinate(5, 1)
        );

        assertThatCode(() -> chessGame.move(
                new Coordinate(2, 0),
                new Coordinate(6, 4)
        )).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("킹을 잡지 않으면 게임이 끝나지 않는다")
    void notCatchKingNotGameOver() {
        chessGame.move( // move Pawn
                new Coordinate(1, 1),
                new Coordinate(2, 1)
        );

        assertThat(chessGame.isGameNotOver()).isTrue();
    }

    @Test
    @DisplayName("킹을 잡으면 게임이 끝난다")
    void catchKingGameOver() {
        chessGame.move( // move Pawn
                new Coordinate(1, 0),
                new Coordinate(3, 0)
        );

        chessGame.move( // for change turn(move any Piece)
                new Coordinate(6, 0),
                new Coordinate(5, 0)
        );

        chessGame.move( // move Rook
                new Coordinate(0, 0),
                new Coordinate(2, 0)
        );

        chessGame.move( // for change turn(move any Piece)
                new Coordinate(6, 1),
                new Coordinate(5, 1)
        );

        chessGame.move( // move Rook
                new Coordinate(2, 0),
                new Coordinate(2, 4)
        );

        chessGame.move( // for change turn(move any Piece)
                new Coordinate(6, 2),
                new Coordinate(5, 2)
        );

        chessGame.move( // move Rook
                new Coordinate(2, 4),
                new Coordinate(6, 4)
        );

        chessGame.move( // for change turn(move any Piece)
                new Coordinate(6, 3),
                new Coordinate(5, 3)
        );

        chessGame.move( // Finally Rook catches King
                new Coordinate(6, 4),
                new Coordinate(7, 4)
        );

        assertThat(chessGame.isGameNotOver()).isFalse();
    }
}
