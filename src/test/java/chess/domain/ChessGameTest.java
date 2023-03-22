package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ChessGameTest {

    @DisplayName("King 이 잡히면 게임이 종료된다.")
    @Test
    void 킹_잡히면_게임_종료() {
        Map<Position, Piece> boardForTest = Map.of(
            Position.from("A2"), InitialPiece.WHITE_QUEEN.getPiece(),
            Position.from("E3"), InitialPiece.WHITE_KING.getPiece(),
            Position.from("C4"), InitialPiece.BLACK_KING.getPiece(),
            Position.from("B2"), InitialPiece.BLACK_PAWN.getPiece());

        ChessBoard chessBoard = new ChessBoard(new HashMap<>(boardForTest));
        ChessGame chessGame = new ChessGame(chessBoard);

        chessGame.move(Position.from("A2"), Position.from("C4"));

        assertThat(chessGame.isPlaying()).isFalse();
    }

    @DisplayName("King 이 잡혔을 때 우승팀을 반환한다.")
    @Test
    void 우승팀_안내() {
        Map<Position, Piece> boardForTest = Map.of(
            Position.from("A2"), InitialPiece.WHITE_QUEEN.getPiece(),
            Position.from("E3"), InitialPiece.WHITE_KING.getPiece(),
            Position.from("C4"), InitialPiece.BLACK_KING.getPiece(),
            Position.from("B2"), InitialPiece.BLACK_PAWN.getPiece());

        ChessBoard chessBoard = new ChessBoard(new HashMap<>(boardForTest));
        ChessGame chessGame = new ChessGame(chessBoard);

        chessGame.move(Position.from("A2"), Position.from("C4"));

        assertThat(chessGame.findWinningTeam()).isEqualTo(TeamColor.BLACK);
    }

}