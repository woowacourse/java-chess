package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardFactoryTest {

    @Test
    @DisplayName("초기화된 체스판을 생성한다.")
    void createInitChessBoard() {
        Board initChessBoard = BoardFactory.createInitChessBoard();

        assertThat(initChessBoard.getBoard()).hasSize(64);
        assertThat(initChessBoard.getBoard())
                .containsEntry(Position.of('a', 1), new Rook(Team.WHITE))
                .containsEntry(Position.of('b', 1), new Knight(Team.WHITE))
                .containsEntry(Position.of('c', 1), new Bishop(Team.WHITE))
                .containsEntry(Position.of('d', 1), new Queen(Team.WHITE))
                .containsEntry(Position.of('e', 1), new King(Team.WHITE))
                .containsEntry(Position.of('f', 1), new Bishop(Team.WHITE))
                .containsEntry(Position.of('g', 1), new Knight(Team.WHITE))
                .containsEntry(Position.of('h', 1), new Rook(Team.WHITE))

                .containsEntry(Position.of('a', 2), new Pawn(Team.WHITE))
                .containsEntry(Position.of('b', 2), new Pawn(Team.WHITE))
                .containsEntry(Position.of('c', 2), new Pawn(Team.WHITE))
                .containsEntry(Position.of('d', 2), new Pawn(Team.WHITE))
                .containsEntry(Position.of('e', 2), new Pawn(Team.WHITE))
                .containsEntry(Position.of('f', 2), new Pawn(Team.WHITE))
                .containsEntry(Position.of('g', 2), new Pawn(Team.WHITE))
                .containsEntry(Position.of('h', 2), new Pawn(Team.WHITE))

                .containsEntry(Position.of('a', 3), new Blank())
                .containsEntry(Position.of('b', 3), new Blank())
                .containsEntry(Position.of('c', 3), new Blank())
                .containsEntry(Position.of('d', 3), new Blank())
                .containsEntry(Position.of('e', 3), new Blank())
                .containsEntry(Position.of('f', 3), new Blank())
                .containsEntry(Position.of('g', 3), new Blank())
                .containsEntry(Position.of('h', 3), new Blank())

                .containsEntry(Position.of('a', 4), new Blank())
                .containsEntry(Position.of('b', 4), new Blank())
                .containsEntry(Position.of('c', 4), new Blank())
                .containsEntry(Position.of('d', 4), new Blank())
                .containsEntry(Position.of('e', 4), new Blank())
                .containsEntry(Position.of('f', 4), new Blank())
                .containsEntry(Position.of('g', 4), new Blank())
                .containsEntry(Position.of('h', 4), new Blank())

                .containsEntry(Position.of('a', 5), new Blank())
                .containsEntry(Position.of('b', 5), new Blank())
                .containsEntry(Position.of('c', 5), new Blank())
                .containsEntry(Position.of('d', 5), new Blank())
                .containsEntry(Position.of('e', 5), new Blank())
                .containsEntry(Position.of('f', 5), new Blank())
                .containsEntry(Position.of('g', 5), new Blank())
                .containsEntry(Position.of('h', 5), new Blank())

                .containsEntry(Position.of('a', 6), new Blank())
                .containsEntry(Position.of('b', 6), new Blank())
                .containsEntry(Position.of('c', 6), new Blank())
                .containsEntry(Position.of('d', 6), new Blank())
                .containsEntry(Position.of('e', 6), new Blank())
                .containsEntry(Position.of('f', 6), new Blank())
                .containsEntry(Position.of('g', 6), new Blank())
                .containsEntry(Position.of('h', 6), new Blank())

                .containsEntry(Position.of('a', 7), new Pawn(Team.BLACK))
                .containsEntry(Position.of('b', 7), new Pawn(Team.BLACK))
                .containsEntry(Position.of('c', 7), new Pawn(Team.BLACK))
                .containsEntry(Position.of('d', 7), new Pawn(Team.BLACK))
                .containsEntry(Position.of('e', 7), new Pawn(Team.BLACK))
                .containsEntry(Position.of('f', 7), new Pawn(Team.BLACK))
                .containsEntry(Position.of('g', 7), new Pawn(Team.BLACK))
                .containsEntry(Position.of('h', 7), new Pawn(Team.BLACK))

                .containsEntry(Position.of('a', 8), new Rook(Team.BLACK))
                .containsEntry(Position.of('b', 8), new Knight(Team.BLACK))
                .containsEntry(Position.of('c', 8), new Bishop(Team.BLACK))
                .containsEntry(Position.of('d', 8), new Queen(Team.BLACK))
                .containsEntry(Position.of('e', 8), new King(Team.BLACK))
                .containsEntry(Position.of('f', 8), new Bishop(Team.BLACK))
                .containsEntry(Position.of('g', 8), new Knight(Team.BLACK))
                .containsEntry(Position.of('h', 8), new Rook(Team.BLACK));
    }
}