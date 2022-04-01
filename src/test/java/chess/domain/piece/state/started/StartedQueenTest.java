package chess.domain.piece.state.started;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.game.state.position.File;
import chess.domain.game.state.position.Position;
import chess.domain.game.state.position.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.property.Color;
import chess.domain.piece.state.Dead;
import chess.domain.piece.state.PieceState;

class StartedQueenTest {

    private Map<Position, Piece> board;
    private Position source;
    private Piece sourcePiece;

    @Test
    @DisplayName("Started상태에서 die시 dead로 변해야한다. ")
    void die() {
        PieceState pieceState = new StartedQueen();
        assertThat(pieceState.die()).isInstanceOf(Dead.class);
    }

    @Test
    @DisplayName("Started상태에서 updateState시 자신의 상태로 돌아와야 한다.")
    void updateState() {
        PieceState pieceState = new StartedQueen();
        assertThat(pieceState.updateState()).isInstanceOf(StartedQueen.class);
    }

    @Test
    @DisplayName("현재 상태에서 가능한 Positions 을 가져와야 한다.")
    void findMovablePositions() {
        initBoard();
        List<Position> positions = sourcePiece.findMovablePositions(source, board);
        assertThat(positions)
            .hasSize(21)
            .contains(
                Position.of(File.a, Rank.Four),
                Position.of(File.b, Rank.Four),
                Position.of(File.c, Rank.Four),
                Position.of(File.e, Rank.Four),
                Position.of(File.f, Rank.Four),
                Position.of(File.g, Rank.Four),
                Position.of(File.h, Rank.Four),
                Position.of(File.a, Rank.One),
                Position.of(File.b, Rank.Two),
                Position.of(File.c, Rank.Three),
                Position.of(File.e, Rank.Five),
                Position.of(File.f, Rank.Six),
                Position.of(File.g, Rank.Seven),
                Position.of(File.h, Rank.Eight),
                Position.of(File.g, Rank.One),
                Position.of(File.f, Rank.Two),
                Position.of(File.e, Rank.Three),
                Position.of(File.c, Rank.Five),
                Position.of(File.b, Rank.Six),
                Position.of(File.a, Rank.Seven),
                Position.of(File.d, Rank.Three)
            );
    }

    void initBoard() {
        board = new HashMap<>();
        source = Position.of(File.d, Rank.Four);
        sourcePiece = new Queen(Color.White);

        board.put(source, sourcePiece);
        board.put(Position.of(File.d, Rank.Three), new Queen(Color.Black));
        board.put(Position.of(File.d, Rank.Five), new Queen(Color.White));
    }
}