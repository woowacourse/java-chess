package chess.domain.board;

import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.King;
import chess.domain.chesspiece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static chess.domain.game.Team.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RowTest {
    @Test
    @DisplayName("of 테스트")
    void create() {
        List<ChessPiece> chessPieces = new ArrayList<>();

        chessPieces.add(new Blank(BLANK));
        assertThat(Row.of(chessPieces)).isInstanceOf(Row.class);
    }

    @Test
    @DisplayName("get 테스트")
    void get() {
        List<ChessPiece> chessPieces = new ArrayList<>();
        chessPieces.add(new Blank(BLANK));
        Row row = Row.of(chessPieces);

        assertThat(row.get(0)).isInstanceOf(ChessPiece.class);
    }

    @Test
    @DisplayName("modifyRow 테스트")
    void modifyRow() {
        List<ChessPiece> chessPieces = new ArrayList<>();
        chessPieces.add(new Queen(BLACK));
        Row row = Row.of(chessPieces);

        row.modifyRow(0, new King(WHITE));
        assertThat(row.get(0)).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("getChessPieces 테스트")
    void getChessPieces() {
        List<ChessPiece> chessPieces = new ArrayList<>();
        chessPieces.add(new Queen(BLACK));
        Row row = Row.of(chessPieces);

        assertThat(row.getChessPieces()).isInstanceOf(List.class);
    }
}
