package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.move.Coordinate;
import domain.piece.nonsliding.King;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
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
    void catchPossibleWhenNotSameColor() {
        Map<Coordinate, Piece> mockedSquareLocations = new HashMap<>();
        mockedSquareLocations.put(
                new Coordinate(0, 0),
                new WhitePawn(Color.WHITE)
        );
        mockedSquareLocations.put(
                new Coordinate(1, 1),
                new BlackPawn(Color.BLACK)
        );

        ChessGame mockedChessGame = new ChessGame(
                new Board(mockedSquareLocations)
        );

        assertThatCode(() -> mockedChessGame.move(
                new Coordinate(0, 0),
                new Coordinate(1, 1)
        )).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("킹을 잡지 않으면 게임이 끝나지 않는다")
    void notCatchKingNotGameOver() {
        chessGame.move(
                new Coordinate(1, 1),
                new Coordinate(2, 1)
        );

        assertThat(chessGame.isGameNotOver()).isTrue();
    }

    @Test
    @DisplayName("킹을 잡으면 게임이 끝난다")
    void catchKingGameOver() {
        Map<Coordinate, Piece> mockedSquareLocations = new HashMap<>();
        mockedSquareLocations.put(
                new Coordinate(0, 0),
                new King(Color.WHITE)
        );
        mockedSquareLocations.put(
                new Coordinate(1, 1),
                new King(Color.BLACK)
        );

        ChessGame mockedChessGame = new ChessGame(
                new Board(mockedSquareLocations)
        );
        mockedChessGame.move(
                new Coordinate(0, 0),
                new Coordinate(1, 1)
        );

        assertThat(mockedChessGame.isGameNotOver()).isFalse();
    }

    @Test
    @DisplayName("각 진영의 최하 점수를 계산할 수 있다")
    void collectPointWhenZero() {
        Map<Coordinate, Piece> mockedSquareLocations = new HashMap<>();
        mockedSquareLocations.put(
                new Coordinate(0, 0),
                new King(Color.WHITE)
        );
        mockedSquareLocations.put(
                new Coordinate(1, 1),
                new King(Color.BLACK)
        );

        ChessGame mockedChessGame = new ChessGame(
                new Board(mockedSquareLocations)
        );

        assertThat(mockedChessGame.collectPoint())
                .containsEntry(Color.WHITE, 0.0)
                .containsEntry(Color.BLACK, 0.0);
    }

    @Test
    @DisplayName("공동 우승자를 반환할 수 있다")
    void getWinningColorTwo() {
        Map<Coordinate, Piece> mockedSquareLocations = new HashMap<>();
        mockedSquareLocations.put(
                new Coordinate(0, 0),
                new King(Color.WHITE)
        );
        mockedSquareLocations.put(
                new Coordinate(1, 1),
                new King(Color.BLACK)
        );

        ChessGame mockedChessGame = new ChessGame(
                new Board(mockedSquareLocations)
        );

        assertThat(mockedChessGame.getWinningColor())
                .contains(Color.WHITE, Color.BLACK);
    }

    @Test
    @DisplayName("단독 우승자를 반환할 수 있다")
    void getWinningColorOne() {
        Map<Coordinate, Piece> mockedSquareLocations = new HashMap<>();
        mockedSquareLocations.put(
                new Coordinate(0, 0),
                new WhitePawn(Color.WHITE)
        );
        mockedSquareLocations.put(
                new Coordinate(1, 1),
                new King(Color.BLACK)
        );

        ChessGame mockedChessGame = new ChessGame(
                new Board(mockedSquareLocations)
        );

        assertThat(mockedChessGame.getWinningColor())
                .contains(Color.WHITE);
    }
}
