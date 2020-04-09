package chess.manager;

import chess.board.ChessBoard;
import chess.board.ChessBoardAdapter;
import chess.board.Tile;
import chess.coordinate.Coordinate;
import chess.coordinate.File;
import chess.coordinate.Rank;
import chess.handler.exception.AlreadyEndGameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.piece.Pieces.BLACK_KING;
import static chess.piece.Pieces.WHITE_KING;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessManagerTest {

    @DisplayName("왕이 잡힌 상태에서 움직임을 시도하면 Exception 발생")
    @Test
    void move() {
        //given
        ChessBoard chessBoard = ChessBoard.empty();
        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.ONE), BLACK_KING.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.B, Rank.ONE), WHITE_KING.getPiece()));

        ChessManager chessManager = new ChessManager(new ChessBoardAdapter(chessBoard));
        chessManager.move("b1", "a1");

        //then
        assertThatThrownBy(() -> chessManager.move("a1", "b1"))
                .isInstanceOf(AlreadyEndGameException.class)
                .hasMessage("%s의 승리로 끝난 게임입니다.", chessManager.getCurrentTeam());
    }
}