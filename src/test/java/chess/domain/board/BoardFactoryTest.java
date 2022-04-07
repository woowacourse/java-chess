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
                .containsEntry(Position.valueOf('a', 1), new Rook(Team.WHITE))
                .containsEntry(Position.valueOf('b', 1), new Knight(Team.WHITE))
                .containsEntry(Position.valueOf('c', 1), new Bishop(Team.WHITE))
                .containsEntry(Position.valueOf('d', 1), new Queen(Team.WHITE))
                .containsEntry(Position.valueOf('e', 1), new King(Team.WHITE))
                .containsEntry(Position.valueOf('f', 1), new Bishop(Team.WHITE))
                .containsEntry(Position.valueOf('g', 1), new Knight(Team.WHITE))
                .containsEntry(Position.valueOf('h', 1), new Rook(Team.WHITE))

                .containsEntry(Position.valueOf('a', 2), new Pawn(Team.WHITE))
                .containsEntry(Position.valueOf('b', 2), new Pawn(Team.WHITE))
                .containsEntry(Position.valueOf('c', 2), new Pawn(Team.WHITE))
                .containsEntry(Position.valueOf('d', 2), new Pawn(Team.WHITE))
                .containsEntry(Position.valueOf('e', 2), new Pawn(Team.WHITE))
                .containsEntry(Position.valueOf('f', 2), new Pawn(Team.WHITE))
                .containsEntry(Position.valueOf('g', 2), new Pawn(Team.WHITE))
                .containsEntry(Position.valueOf('h', 2), new Pawn(Team.WHITE))

                .containsEntry(Position.valueOf('a', 3), new Blank())
                .containsEntry(Position.valueOf('b', 3), new Blank())
                .containsEntry(Position.valueOf('c', 3), new Blank())
                .containsEntry(Position.valueOf('d', 3), new Blank())
                .containsEntry(Position.valueOf('e', 3), new Blank())
                .containsEntry(Position.valueOf('f', 3), new Blank())
                .containsEntry(Position.valueOf('g', 3), new Blank())
                .containsEntry(Position.valueOf('h', 3), new Blank())

                .containsEntry(Position.valueOf('a', 4), new Blank())
                .containsEntry(Position.valueOf('b', 4), new Blank())
                .containsEntry(Position.valueOf('c', 4), new Blank())
                .containsEntry(Position.valueOf('d', 4), new Blank())
                .containsEntry(Position.valueOf('e', 4), new Blank())
                .containsEntry(Position.valueOf('f', 4), new Blank())
                .containsEntry(Position.valueOf('g', 4), new Blank())
                .containsEntry(Position.valueOf('h', 4), new Blank())

                .containsEntry(Position.valueOf('a', 5), new Blank())
                .containsEntry(Position.valueOf('b', 5), new Blank())
                .containsEntry(Position.valueOf('c', 5), new Blank())
                .containsEntry(Position.valueOf('d', 5), new Blank())
                .containsEntry(Position.valueOf('e', 5), new Blank())
                .containsEntry(Position.valueOf('f', 5), new Blank())
                .containsEntry(Position.valueOf('g', 5), new Blank())
                .containsEntry(Position.valueOf('h', 5), new Blank())

                .containsEntry(Position.valueOf('a', 6), new Blank())
                .containsEntry(Position.valueOf('b', 6), new Blank())
                .containsEntry(Position.valueOf('c', 6), new Blank())
                .containsEntry(Position.valueOf('d', 6), new Blank())
                .containsEntry(Position.valueOf('e', 6), new Blank())
                .containsEntry(Position.valueOf('f', 6), new Blank())
                .containsEntry(Position.valueOf('g', 6), new Blank())
                .containsEntry(Position.valueOf('h', 6), new Blank())

                .containsEntry(Position.valueOf('a', 7), new Pawn(Team.BLACK))
                .containsEntry(Position.valueOf('b', 7), new Pawn(Team.BLACK))
                .containsEntry(Position.valueOf('c', 7), new Pawn(Team.BLACK))
                .containsEntry(Position.valueOf('d', 7), new Pawn(Team.BLACK))
                .containsEntry(Position.valueOf('e', 7), new Pawn(Team.BLACK))
                .containsEntry(Position.valueOf('f', 7), new Pawn(Team.BLACK))
                .containsEntry(Position.valueOf('g', 7), new Pawn(Team.BLACK))
                .containsEntry(Position.valueOf('h', 7), new Pawn(Team.BLACK))

                .containsEntry(Position.valueOf('a', 8), new Rook(Team.BLACK))
                .containsEntry(Position.valueOf('b', 8), new Knight(Team.BLACK))
                .containsEntry(Position.valueOf('c', 8), new Bishop(Team.BLACK))
                .containsEntry(Position.valueOf('d', 8), new Queen(Team.BLACK))
                .containsEntry(Position.valueOf('e', 8), new King(Team.BLACK))
                .containsEntry(Position.valueOf('f', 8), new Bishop(Team.BLACK))
                .containsEntry(Position.valueOf('g', 8), new Knight(Team.BLACK))
                .containsEntry(Position.valueOf('h', 8), new Rook(Team.BLACK));
    }
}