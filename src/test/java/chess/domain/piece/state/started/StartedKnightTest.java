package chess.domain.piece.state.started;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.game.state.ChessBoard;
import chess.domain.game.state.position.Direction;
import chess.domain.game.state.position.File;
import chess.domain.game.state.position.Position;
import chess.domain.game.state.position.Rank;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;
import chess.domain.piece.state.Dead;
import chess.domain.piece.state.PieceState;

class StartedKnightTest {

    private ChessBoard board;
    private Position source;
    private Piece sourcePiece;

    @Test
    @DisplayName("Started상태에서 die시 dead로 변해야한다. ")
    void die() {
        PieceState pieceState = new StartedKnight();
        assertThat(pieceState.die()).isInstanceOf(Dead.class);
    }

    @Test
    @DisplayName("Started상태에서 updateState시 자신의 상태로 돌아와야 한다.")
    void updateState() {
        PieceState pieceState = new StartedKnight();
        assertThat(pieceState.updateState()).isInstanceOf(StartedKnight.class);
    }

    @Test
    @DisplayName("현재 상태에서 가능한 Positions 을 가져와야 한다.")
    void findMovablePositions() {
        initBoard();
        List<Position> positions = sourcePiece.findMovablePositions(source, board);;
        assertThat(positions)
            .hasSize(8)
            .contains(
                Position.of(File.b, Rank.Three),
                Position.of(File.b, Rank.Five),
                Position.of(File.c, Rank.Two),
                Position.of(File.c, Rank.Six),
                Position.of(File.e, Rank.Two),
                Position.of(File.e, Rank.Six),
                Position.of(File.f, Rank.Three),
                Position.of(File.f, Rank.Five)
            );
    }

    void initBoard() {
        board = new ChessBoard();
        source = Position.of(File.d, Rank.Four);
        sourcePiece = new Knight(Color.White);

        board.putPiece(source, sourcePiece);
        board.putPiece(Position.of(File.b, Rank.Three), new Knight(Color.Black));

        for (Direction direction : Direction.all()) {
            board.putPiece(source.findNext(direction), new Knight(Color.Black));
        }
    }
}