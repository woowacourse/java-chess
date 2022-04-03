package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.generator.NormalPiecesGenerator;
import chess.domain.piece.generator.PiecesGenerator;
import chess.domain.position.Column;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성한다.")
    void construct() {
        Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("a2"), new Pawn(Color.BLACK))
        ));
        ChessBoard chessBoard = new ChessBoard(() -> pieces);
        assertThat(chessBoard.getPieces()).containsAllEntriesOf(pieces);
    }

    @Test
    @DisplayName("체스판을 생성할 때 빈 칸은 EmptyPiece를 삽입한다.")
    void constructEmptyPieces() {
        Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("a2"), new Pawn(Color.BLACK))
        ));
        ChessBoard chessBoard = new ChessBoard(() -> pieces);
        assertThat(chessBoard.getPieces()).contains(Map.entry(Position.of("a3"), EmptyPiece.getInstance()));
    }

    @Test
    @DisplayName("위치가 들어왔을 때 해당 위치의 말이 어떤 말인지 확인한다.")
    void selectPiece() {
        PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
        ChessBoard chessBoard = new ChessBoard(piecesGenerator);
        Piece piece = chessBoard.selectPiece(Position.of("a1"));
        assertThat(piece).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("한 컬럼의 흑팀 말들을 반환한다.")
    void getPiecesOnColumnByColor() {
        ChessBoard chessBoard = new ChessBoard(new NormalPiecesGenerator());
        Piece rook = chessBoard.selectPiece(Position.of("a8"));
        Piece pawn = chessBoard.selectPiece(Position.of("a7"));
        List<Piece> pieces = chessBoard.getPiecesOnColumn(Column.A, Color.BLACK);
        assertThat(pieces).containsExactly(pawn, rook);
    }

    @Test
    @DisplayName("여러 컬럼의 흑팀 말들을 반환한다.")
    void getPiecesOnColumnsByColor() {

        Map<Position, Piece> testPieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new Queen(Color.WHITE)),
                Map.entry(Position.of("b3"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("c4"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("a4"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("a7"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("c5"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("b8"), new Pawn(Color.BLACK))

        ));
        ChessBoard chessBoard = new ChessBoard(() -> testPieces);

        Piece a = chessBoard.selectPiece(Position.of("a4"));
        Piece a2 = chessBoard.selectPiece(Position.of("a7"));
        Piece b = chessBoard.selectPiece(Position.of("b8"));
        Piece c = chessBoard.selectPiece(Position.of("c5"));

        List<List<Piece>> pieces = chessBoard.getPiecesOnColumns(Color.BLACK);
        assertThat(pieces).contains(
                List.of(a, a2),
                List.of(b),
                List.of(c)
        );
    }

    @Test
    @DisplayName("킹이 1개일 때, 게임은 끝난다.")
    void isEndTrue() {
        Map<Position, Piece> testPieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new King(Color.WHITE)),
                Map.entry(Position.of("b3"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("c4"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("a4"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("a7"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("c5"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("b8"), new Pawn(Color.BLACK))
        ));
        ChessBoard chessBoard = new ChessBoard(() -> testPieces);

        assertThat(chessBoard.isEnd()).isTrue();
    }

    @Test
    @DisplayName("킹이 2개일 때, 게임은 끝나지 않는다.")
    void isEndFalse() {
        Map<Position, Piece> testPieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new King(Color.WHITE)),
                Map.entry(Position.of("b3"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("c4"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("a4"), new King(Color.BLACK)),
                Map.entry(Position.of("a7"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("c5"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("b8"), new Pawn(Color.BLACK))
        ));
        ChessBoard chessBoard = new ChessBoard(() -> testPieces);

        assertThat(chessBoard.isEnd()).isFalse();
    }

    @Test
    @DisplayName("해당 Position의 컬러를 확인한다.")
    void getPositionColor() {
        ChessBoard chessBoard = new ChessBoard(
                () -> new HashMap<>(Map.ofEntries(
                        Map.entry(Position.of("a1"), new King(Color.WHITE)))));
        assertThat(chessBoard.getPositionColor(Position.of("a1")))
                .isEqualTo(Color.WHITE);
    }
}
