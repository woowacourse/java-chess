package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.NormalPiecesGenerator;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PiecesGenerator;
import chess.domain.piece.Rook;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성한다.")
    void construct() {
        Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                Map.entry(new Position("a1"), new Pawn(Color.WHITE)),
                Map.entry(new Position("a2"), new Pawn(Color.BLACK))
        ));
        ChessBoard chessBoard = new ChessBoard(pieces);
        assertThat(chessBoard.getPieces()).containsAllEntriesOf(pieces);
    }

    @Test
    @DisplayName("체스판을 생성할 때 빈 칸은 EmptyPiece를 삽입한다.")
    void constructEmptyPieces() {
        Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                Map.entry(new Position("a1"), new Pawn(Color.WHITE)),
                Map.entry(new Position("a2"), new Pawn(Color.BLACK))
        ));
        ChessBoard chessBoard = new ChessBoard(pieces);
        assertThat(chessBoard.getPieces()).contains(Map.entry(new Position("a3"), EmptyPiece.getInstance()));
    }

    @Test
    @DisplayName("위치가 들어왔을 때 해당 위치의 말이 어떤 말인지 확인한다.")
    void selectPiece() {
        PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
        ChessBoard chessBoard = new ChessBoard(piecesGenerator.generate());
        Piece piece = chessBoard.selectPiece(new Position("a1"));
        assertThat(piece).isInstanceOf(Rook.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a2, true", "a3, false", "h7, true", "h8, false"})
    @DisplayName("해당 위치에 움직이지 않은 폰이 있는지 확인한다.")
    void isFirstMovePawn(String position, boolean expected) {
        PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
        ChessBoard chessBoard = new ChessBoard(piecesGenerator.generate());
        assertThat(chessBoard.isFirstMovePawn(new Position(position))).isEqualTo(expected);
    }
}
