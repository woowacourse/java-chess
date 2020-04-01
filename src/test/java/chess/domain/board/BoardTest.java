package chess.domain.board;

import chess.domain.board.initializer.EnumRepositoryBoardInitializer;
import chess.domain.game.Turn;
import chess.domain.piece.PieceState;
import chess.domain.piece.implementation.piece.Knight;
import chess.domain.player.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("board는 boardInitializer 타입의 객체를 받아서 생성")
    void initialize() {
        Board board = Board.of(new EnumRepositoryBoardInitializer());
    }

    @Test
    void move() {
        Board board = Board.of(new EnumRepositoryBoardInitializer());
        board.move(Position.of("B8"), Position.of("A6"), Turn.from(Team.BLACK));
        Map<Position, PieceState> piecePosition = board.getBoard();

        assertThat(piecePosition.get(Position.of("A6")))
                .isInstanceOf(Knight.class);
        assertThat(piecePosition.get(Position.of("B8")))
                .isNull();
    }
}