package chess.gameboard;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import chess.Color;
import chess.Fixtures;
import chess.Position;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.request.StartPosAndEndPos;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameBoardTest {
    @DisplayName("체스판 초기화 테스트")
    @Test
    void chessBoardInitTest() {
        // given

        // when
        GameBoard gameBoard = new GameBoard();

        // then
        Map<Position, Piece> rawBoard = gameBoard.getBoard();

        // 백팀 주요 기물
        assertEquals(new Rook(Color.WHITE), rawBoard.get(Position.from("A1")));
        assertEquals(new Knight(Color.WHITE), rawBoard.get(Position.from("B1")));
        assertEquals(new Bishop(Color.WHITE), rawBoard.get(Position.from("C1")));
        assertEquals(new Queen(Color.WHITE), rawBoard.get(Position.from("D1")));
        assertEquals(new King(Color.WHITE), rawBoard.get(Position.from("E1")));
        assertEquals(new Bishop(Color.WHITE), rawBoard.get(Position.from("F1")));
        assertEquals(new Knight(Color.WHITE), rawBoard.get(Position.from("G1")));
        assertEquals(new Rook(Color.WHITE), rawBoard.get(Position.from("H1")));

        // 백팀 폰
        assertEquals(new Pawn(Color.WHITE), rawBoard.get(Position.from("A2")));
        assertEquals(new Pawn(Color.WHITE), rawBoard.get(Position.from("B2")));
        assertEquals(new Pawn(Color.WHITE), rawBoard.get(Position.from("C2")));
        assertEquals(new Pawn(Color.WHITE), rawBoard.get(Position.from("D2")));
        assertEquals(new Pawn(Color.WHITE), rawBoard.get(Position.from("E2")));
        assertEquals(new Pawn(Color.WHITE), rawBoard.get(Position.from("F2")));
        assertEquals(new Pawn(Color.WHITE), rawBoard.get(Position.from("G2")));
        assertEquals(new Pawn(Color.WHITE), rawBoard.get(Position.from("H2")));

        // 흑팀 주요 기물
        assertEquals(new Rook(Color.BLACK), rawBoard.get(Position.from("A8")));
        assertEquals(new Knight(Color.BLACK), rawBoard.get(Position.from("B8")));
        assertEquals(new Bishop(Color.BLACK), rawBoard.get(Position.from("C8")));
        assertEquals(new Queen(Color.BLACK), rawBoard.get(Position.from("D8")));
        assertEquals(new King(Color.BLACK), rawBoard.get(Position.from("E8")));
        assertEquals(new Bishop(Color.BLACK), rawBoard.get(Position.from("F8")));
        assertEquals(new Knight(Color.BLACK), rawBoard.get(Position.from("G8")));
        assertEquals(new Rook(Color.BLACK), rawBoard.get(Position.from("H8")));

        // 흑팀 폰
        assertEquals(new Pawn(Color.BLACK), rawBoard.get(Position.from("A7")));
        assertEquals(new Pawn(Color.BLACK), rawBoard.get(Position.from("B7")));
        assertEquals(new Pawn(Color.BLACK), rawBoard.get(Position.from("C7")));
        assertEquals(new Pawn(Color.BLACK), rawBoard.get(Position.from("D7")));
        assertEquals(new Pawn(Color.BLACK), rawBoard.get(Position.from("E7")));
        assertEquals(new Pawn(Color.BLACK), rawBoard.get(Position.from("F7")));
        assertEquals(new Pawn(Color.BLACK), rawBoard.get(Position.from("G7")));
        assertEquals(new Pawn(Color.BLACK), rawBoard.get(Position.from("H7")));

        for (char file = 'A'; file <= 'H'; file++) {
            for (int rank = 3; rank <= 6; rank++) {
                Position pos = Position.from("" + file + rank);
                assertNull(rawBoard.get(pos), "다음 칸이 비어잇어야 함:" + pos);
            }
        }
    }

    @DisplayName("게임 보드의 폰은 move할 수 있다")
    @Test
    void pieceMoveTest() {
        // given
        GameBoard gameBoard = new GameBoard();
        StartPosAndEndPos moveRequest = new StartPosAndEndPos(
                Fixtures.A2, Fixtures.A3
        );

        // when
        gameBoard.move(moveRequest);

        // then
        Map<Position, Piece> rawBoard = gameBoard.getBoard();
        Piece piece = rawBoard.get(Fixtures.A3);
        Piece expectedPiece = new Pawn(Color.WHITE);

        Assertions.assertThat(piece).isEqualTo(expectedPiece);
        assertNull(rawBoard.get(Fixtures.A2));
    }
}
