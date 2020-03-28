package chess.domain.board;

import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Pawn;
import chess.domain.move.MovingInfo;
import chess.domain.move.Position;
import chess.factory.BoardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.game.Team.BLACK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BoardTest {
    private final static int CORRECTION_VALUE = 1;

    @Test
    @DisplayName("ChessBoard 생성")
    void create() {
        assertThat(new Board(BoardFactory.createBoard())).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("getBoard 테스트")
    void getBoard() {
        Board board = new Board(BoardFactory.createBoard());

        assertThat(board.getBoard()).isInstanceOf(List.class);
    }

    @Test
    @DisplayName("move 테스트")
    void move() {
        Board board = new Board(BoardFactory.createBoard());
        Position startPosition = Position.of(2, 1);
        Position targetPosition = Position.of(4, 1);
        MovingInfo movingInfo = new MovingInfo(startPosition, targetPosition);

        board.move(movingInfo);
        ChessPiece startChessPiece = board.getBoard().get(2 - CORRECTION_VALUE).get(1 - CORRECTION_VALUE);
        ChessPiece targetChessPiece = board.getBoard().get(4 - CORRECTION_VALUE).get(1 - CORRECTION_VALUE);
        assertThat(startChessPiece).isInstanceOf(Blank.class);
        assertThat(targetChessPiece).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("isGameEnd 테스트 - 게임이 끝났을 경우")
    void isGameEnd() {
        Board board = new Board(BoardFactory.createBoard());

        board.move(new MovingInfo(Position.of(2, 6), Position.of(3, 6)));
        board.move(new MovingInfo(Position.of(7, 5), Position.of(6, 5)));
        board.move(new MovingInfo(Position.of(1, 5), Position.of(2, 6)));
        board.move(new MovingInfo(Position.of(8, 4), Position.of(4, 8)));
        board.move(new MovingInfo(Position.of(2, 6), Position.of(3, 7)));
        board.move(new MovingInfo(Position.of(4, 8), Position.of(3, 7)));
        assertThat(board.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("isGameEnd 테스트 - 게임이 끝나지 않았을 경우")
    void isGameEnd_IfNotEnd_ReturnFalse() {
        Board board = new Board(BoardFactory.createBoard());

        board.move(new MovingInfo(Position.of(2, 6), Position.of(3, 6)));
        board.move(new MovingInfo(Position.of(7, 5), Position.of(6, 5)));
        board.move(new MovingInfo(Position.of(1, 5), Position.of(2, 6)));
        board.move(new MovingInfo(Position.of(8, 4), Position.of(4, 8)));
        board.move(new MovingInfo(Position.of(2, 6), Position.of(3, 7)));
        assertThat(board.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("getTotalScore 테스트")
    void getTotalScore() {
        Board board = new Board(BoardFactory.createBoard());

        board.move(new MovingInfo(Position.of(2, 4), Position.of(4, 4)));
        board.move(new MovingInfo(Position.of(7, 5), Position.of(5, 5)));
        board.move(new MovingInfo(Position.of(4, 4), Position.of(5, 5)));
        assertThat(board.getTotalScore()).isEqualTo(37);
    }

    @Test
    @DisplayName("getNowPlayingTeam 테스트")
    void getNowPlayingTeam() {
        Board board = new Board(BoardFactory.createBoard());

        board.move(new MovingInfo(Position.of(2, 4), Position.of(4, 4)));
        assertThat(board.getNowPlayingTeam()).isEqualTo(BLACK);
    }
}
