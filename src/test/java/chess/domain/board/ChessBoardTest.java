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

public class ChessBoardTest {
    private final static int CORRECTION_VALUE = 1;

    @Test
    @DisplayName("ChessBoard 생성")
    void create() {
        assertThat(new ChessBoard(BoardFactory.createBoard())).isInstanceOf(ChessBoard.class);
    }

    @Test
    @DisplayName("getBoard 테스트")
    void getBoard() {
        ChessBoard chessBoard = new ChessBoard(BoardFactory.createBoard());

        assertThat(chessBoard.getBoard()).isInstanceOf(List.class);
    }

    @Test
    @DisplayName("move 테스트")
    void move() {
        ChessBoard chessBoard = new ChessBoard(BoardFactory.createBoard());
        Position startPosition = Position.of(2, 1);
        Position targetPosition = Position.of(4, 1);
        MovingInfo movingInfo = new MovingInfo(startPosition, targetPosition);

        chessBoard.move(movingInfo);
        ChessPiece startChessPiece = chessBoard.getBoard().get(2 - CORRECTION_VALUE).get(1 - CORRECTION_VALUE);
        ChessPiece targetChessPiece = chessBoard.getBoard().get(4 - CORRECTION_VALUE).get(1 - CORRECTION_VALUE);
        assertThat(startChessPiece).isInstanceOf(Blank.class);
        assertThat(targetChessPiece).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("isGameEnd 테스트 - 게임이 끝났을 경우")
    void isGameEnd() {
        ChessBoard chessBoard = new ChessBoard(BoardFactory.createBoard());

        chessBoard.move(new MovingInfo(Position.of(2, 6), Position.of(3, 6)));
        chessBoard.move(new MovingInfo(Position.of(7, 5), Position.of(6, 5)));
        chessBoard.move(new MovingInfo(Position.of(1, 5), Position.of(2, 6)));
        chessBoard.move(new MovingInfo(Position.of(8, 4), Position.of(4, 8)));
        chessBoard.move(new MovingInfo(Position.of(2, 6), Position.of(3, 7)));
        chessBoard.move(new MovingInfo(Position.of(4, 8), Position.of(3, 7)));
        assertThat(chessBoard.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("isGameEnd 테스트 - 게임이 끝나지 않았을 경우")
    void isGameEnd_IfNotEnd_ReturnFalse() {
        ChessBoard chessBoard = new ChessBoard(BoardFactory.createBoard());

        chessBoard.move(new MovingInfo(Position.of(2, 6), Position.of(3, 6)));
        chessBoard.move(new MovingInfo(Position.of(7, 5), Position.of(6, 5)));
        chessBoard.move(new MovingInfo(Position.of(1, 5), Position.of(2, 6)));
        chessBoard.move(new MovingInfo(Position.of(8, 4), Position.of(4, 8)));
        chessBoard.move(new MovingInfo(Position.of(2, 6), Position.of(3, 7)));
        assertThat(chessBoard.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("getTotalScore 테스트")
    void getTotalScore() {
        ChessBoard chessBoard = new ChessBoard(BoardFactory.createBoard());

        chessBoard.move(new MovingInfo(Position.of(2, 4), Position.of(4, 4)));
        chessBoard.move(new MovingInfo(Position.of(7, 5), Position.of(5, 5)));
        chessBoard.move(new MovingInfo(Position.of(4, 4), Position.of(5, 5)));
        assertThat(chessBoard.getTotalScore()).isEqualTo(37);
    }

    @Test
    @DisplayName("getNowPlayingTeam 테스트")
    void getNowPlayingTeam() {
        ChessBoard chessBoard = new ChessBoard(BoardFactory.createBoard());

        chessBoard.move(new MovingInfo(Position.of(2, 4), Position.of(4, 4)));
        assertThat(chessBoard.getNowPlayingTeam()).isEqualTo(BLACK);
    }
}
