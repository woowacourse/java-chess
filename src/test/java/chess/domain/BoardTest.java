package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.board.position.Position;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    @Test
    @DisplayName("체스보드 생성 테스트")
    void createTest() {
        assertThat(new Board(InitBoardGenerator.initLines())).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("좌표에 해당하는 체스말 찾아오기")
    void findPieceByPositionTest() {
        Board board = new Board(InitBoardGenerator.initLines());
        assertThat(board.pieceOfPosition(Position.of("a2"))).isEqualTo(Pawn.createWhite());
        assertThat(board.pieceOfPosition(Position.of("d8"))).isEqualTo(Queen.createBlack());
        assertThat(board.pieceOfPosition(Position.of("c5"))).isEqualTo(Empty.create());
        assertThat(board.pieceOfPosition(Position.of("c1"))).isNotEqualTo(Empty.create());
    }
}
