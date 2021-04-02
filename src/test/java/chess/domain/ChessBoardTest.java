package chess.domain;

import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ChessBoardTest {

    private ChessBoard chessBoard;
    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        this.chessBoard = new ChessBoard();
        this.board = new HashMap<>(chessBoard.getChessBoard());
    }

    @Test
    @DisplayName("체스판 생성 테스트")
    void createChessBoard() {
        assertThatCode(ChessBoard::new).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("말 이동 실패 테스트")
    void failOutOfBoundary() {
        assertThatThrownBy(() -> chessBoard.move("b2", "b10"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("동일한 위치 금지 테스트")
    void failSamePosition() {
        assertThatThrownBy(() -> chessBoard.move("b2", "b2"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("위치 이동 테스트")
    void change_position_test() {
        chessBoard.move("b1", "c3");
        final Map<Position, Piece> chessBoard = this.chessBoard.getChessBoard();
        assertThat(chessBoard.get(Position.valueOf("c3"))).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("킹이 잡히는 경우 게임 종료")
    void game_end_die_king() {
        Piece kingPiece = new King(TeamColor.BLACK, Position.valueOf("b4"));
        Piece piece = new Queen(TeamColor.WHITE, Position.valueOf("b5"));
        board.put(Position.valueOf("b4"), kingPiece);
        board.put(Position.valueOf("b5"), piece);

        final ChessBoard chessBoard = new ChessBoard(board);
        chessBoard.move("b5", "b4");

        assertFalse(chessBoard.isPlaying());
    }

}
