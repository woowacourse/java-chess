package chess.domain.piece.state.started;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.game.state.position.Direction;
import chess.domain.game.state.position.File;
import chess.domain.game.state.position.Position;
import chess.domain.game.state.position.Rank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;
import chess.domain.piece.state.Dead;
import chess.domain.piece.state.PieceState;

class MovedPawnTest {

    private Map<Position, Piece> board;
    private Position source;
    private Piece sourcePiece;

    @Test
    @DisplayName("Started상태에서 die시 dead로 변해야한다. ")
    void die() {
        PieceState pieceState = new MovedPawn(Direction.Up);
        assertThat(pieceState.die()).isInstanceOf(Dead.class);
    }

    @Test
    @DisplayName("Moved상태에서 updateState시 자신의 상태로 돌아와야 한다.")
    void updateState() {
        PieceState pieceState = new MovedPawn(Direction.Up);
        assertThat(pieceState.updateState()).isInstanceOf(MovedPawn.class);
    }

    @Test
    @DisplayName("현재 상태에서 가능한 Positions 을 가져와야 한다.")
    void findMovablePositions() {
        initBoard();
        sourcePiece.updateState();
        List<Position> positions = sourcePiece.findMovablePositions(source, board);
        assertThat(positions)
            .hasSize(3)
            .contains(
                Position.of(File.d, Rank.Five),
                Position.of(File.c, Rank.Five),
                Position.of(File.e, Rank.Five)
            );
    }

    @Test
    @DisplayName("현재 상태에서 가능한 Positions 을 가져와야 한다.")
    void findMovablePositions1() {
        initBoard();
        board.put(Position.of(File.d, Rank.Five), new Pawn(Color.Black));
        sourcePiece.updateState();
        List<Position> positions = sourcePiece.findMovablePositions(source, board);
        assertThat(positions)
            .hasSize(2)
            .contains(
                Position.of(File.c, Rank.Five),
                Position.of(File.e, Rank.Five)
            );
    }

    void initBoard() {
        board = new HashMap<>();
        source = Position.of(File.d, Rank.Four);
        sourcePiece = new Pawn(Color.White);

        board.put(source, sourcePiece);
        board.put(Position.of(File.c, Rank.Five), new Pawn(Color.Black));
        board.put(Position.of(File.e, Rank.Five), new Pawn(Color.Black));
    }
}