package chess.domain.piece.piece;

import chess.domain.board.ChessBoard;
import chess.domain.move.Move;
import chess.domain.move.MoveFactory;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.BlackTeam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {
    @Test
    @DisplayName("이동 성공 테스트")
    void movable() {
        Position source = Position.of("d2");
        Position target = Position.of("f3");
        ChessBoard chessBoard = ChessBoard.initPieces();
        Move move = MoveFactory.findMovePattern(source, target);
        Piece knight = new Knight(source, new BlackTeam());

        knight.validateMovePattern(move, null, chessBoard.getPieces());
    }

    @Test
    @DisplayName("이동 실패 테스트")
    void isNotMovable() {
        Position source = Position.of("d2");
        Position target = Position.of("e3");
        ChessBoard chessBoard = ChessBoard.initPieces();
        Move move = MoveFactory.findMovePattern(source, target);
        Piece knight = new Knight(source, new BlackTeam());

        assertThatThrownBy(() -> knight.validateMovePattern(move, null, chessBoard.getPieces()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말이 갈 수 없는 칸입니다");
    }
}
