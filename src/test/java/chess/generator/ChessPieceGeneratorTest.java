package chess.generator;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.move.Coordinate;
import chess.domain.move.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessPieceGeneratorTest {
    @Test
    @DisplayName("generateChessPiece 테스트")
    void generateChessPiece() {
        String pieceName = "p";
        String teamName = "Black Team";
        Position position = Position.of(Coordinate.of(1), Coordinate.of(1));

        assertThat(ChessPieceGenerator.generateChessPiece(pieceName, teamName, position)).isInstanceOf(ChessPiece.class);
    }
}
