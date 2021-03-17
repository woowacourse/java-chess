package chess.domain;

import chess.domain.piece.*;
import javafx.geometry.Pos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ChessBoardTest {

    @Test
    @DisplayName("체스게임을 시작하면 8*8 체스판을 생성한다.")
    void when_start_chess_game_create_chess_board() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.getChessBoard()).hasSize(32);
    }

    @Test
    @DisplayName("체스판을 생성하면, 체스 말이 초기 위치로 정상 배치된다.")
    void init_chess_piece_in_chess_board() {
        final ChessBoard chessBoard = new ChessBoard();
        final Map<Position, Piece> chessBoardMap = chessBoard.getChessBoard();

        assertThat(chessBoardMap.get(new Position(0, 0))).isInstanceOf(Rook.class);
        assertThat(chessBoardMap.get(new Position(0, 1))).isInstanceOf(Knight.class);
        assertThat(chessBoardMap.get(new Position(0, 2))).isInstanceOf(Bishop.class);
        assertThat(chessBoardMap.get(new Position(0, 3))).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("정상적으로 움직일 수 있는 좌표가 주어지면, 이동한다.")
    void move_chess_piece_when_valid_destination_is_given() {
        final ChessBoard chessBoard = new ChessBoard();
        chessBoard.move(Position.of("e7"), Position.of("e5"));

        final Map<Position, Piece> piecePosition = chessBoard.getChessBoard();
        assertThat(piecePosition.get(Position.of("e5"))).isInstanceOf(Pawn.class);
    }
}
