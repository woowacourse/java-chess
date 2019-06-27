package chess.domain.domain.piece;

import chess.domain.Aliance;
import chess.domain.Board;
import chess.domain.PieceValue;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PieceTest {
    Board board;

    @BeforeEach
    void setUp() {
         board = new Board(Aliance.BLACK);
    }

    @Test
    void 현재_턴과_말이_팀이_다른_경우() {
        Piece piece = new Rook(Aliance.BLACK, PieceValue.ROOK);
        assertTrue(piece.isDifferentTeam(Aliance.WHITE));
    }

    @Test
    void 현재_턴과_말이_팀이_같은_경우() {
        Piece piece = new Rook(Aliance.BLACK, PieceValue.ROOK);
        assertFalse(piece.isDifferentTeam(Aliance.BLACK));
    }

    @Test
    void 킹움직임_테스트() {
        board.putPiece("a1",Aliance.BLACK.getTeamId() ,PieceValue.KING.getKindId());
        board.movePiece("a1","a2");
        assertThat(board.getPieces().containsKey(Position.valueOf("a2"))).isEqualTo(true);
    }

    @Test
    void 퀸움직임_테스트() {
        board.putPiece("a1",Aliance.BLACK.getTeamId() ,PieceValue.QUEEN.getKindId());
        board.movePiece("a1","h8");
        assertThat(board.getPieces().containsKey(Position.valueOf("h8"))).isEqualTo(true);
    }

    @Test
    void 비숍움직임_테스트() {
        board.putPiece("a1",Aliance.BLACK.getTeamId() ,PieceValue.BISHOP.getKindId());
        board.movePiece("a1","c3");
        assertThat(board.getPieces().containsKey(Position.valueOf("c3"))).isEqualTo(true);
    }

    @Test
    void 나이트움직임_테스트() {
        board.putPiece("a1",Aliance.BLACK.getTeamId() ,PieceValue.KNIGHT.getKindId());
        board.movePiece("a1","b3");
        assertThat(board.getPieces().containsKey(Position.valueOf("b3"))).isEqualTo(true);
    }

    @Test
    void 룩움직임_테스트() {
        board.putPiece("a1",Aliance.BLACK.getTeamId() ,PieceValue.ROOK.getKindId());
        board.movePiece("a1","h1");
        assertThat(board.getPieces().containsKey(Position.valueOf("h1"))).isEqualTo(true);
    }

    @Test
    void 폰_두칸이동_움직임_테스트() {
        board.putPiece("a7",Aliance.BLACK.getTeamId() ,PieceValue.PAWN.getKindId());
        board.movePiece("a7","a5");
        assertThat(board.getPieces().containsKey(Position.valueOf("a5"))).isEqualTo(true);
    }

    @Test
    void 폰_한칸이동_움직임_테스트() {
        board.putPiece("a7",Aliance.BLACK.getTeamId() ,PieceValue.PAWN.getKindId());
        board.movePiece("a7","a6");
        assertThat(board.getPieces().containsKey(Position.valueOf("a6"))).isEqualTo(true);
    }
}
