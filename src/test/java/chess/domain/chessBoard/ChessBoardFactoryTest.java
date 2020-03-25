package chess.domain.chessBoard;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.PieceColor;
import chess.domain.chessPiece.pieceType.*;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardFactoryTest {

    @ParameterizedTest
    @MethodSource("provideInitializedChessBoard")
    void create_ReturnInitialChessBoard(Map<Position, ChessPiece> expected) {
        assertThat(ChessBoardFactory.create()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideInitializedChessBoard() {
        Map<Position, ChessPiece> initialBoard = new HashMap<>();
        addOtherPiecesBy(initialBoard, PieceColor.WHITE, 1);
        addPawnPiecesBy(initialBoard, PieceColor.WHITE, 2);

        addPawnPiecesBy(initialBoard, PieceColor.BLACK, 7);
        addOtherPiecesBy(initialBoard, PieceColor.BLACK, 8);

        return Stream.of(Arguments.of(initialBoard));
    }

    private static void addPawnPiecesBy(Map<Position, ChessPiece> initialBoard, PieceColor pieceColor, int rank) {
        for (ChessFile chessFile : ChessFile.values()) {
            initialBoard.put(Position.of(chessFile, ChessRank.from(rank)), ChessPiece.of(pieceColor, Pawn.getInstance()));
        }
    }

    private static void addOtherPiecesBy(Map<Position, ChessPiece> initialBoard, PieceColor pieceColor, int rank) {
        initialBoard.put(Position.of(ChessFile.from('a'), ChessRank.from(rank)), ChessPiece.of(pieceColor, Rook.getInstance()));
        initialBoard.put(Position.of(ChessFile.from('b'), ChessRank.from(rank)), ChessPiece.of(pieceColor, Knight.getInstance()));
        initialBoard.put(Position.of(ChessFile.from('c'), ChessRank.from(rank)), ChessPiece.of(pieceColor, Bishop.getInstance()));
        initialBoard.put(Position.of(ChessFile.from('d'), ChessRank.from(rank)), ChessPiece.of(pieceColor, Queen.getInstance()));
        initialBoard.put(Position.of(ChessFile.from('e'), ChessRank.from(rank)), ChessPiece.of(pieceColor, King.getInstance()));
        initialBoard.put(Position.of(ChessFile.from('f'), ChessRank.from(rank)), ChessPiece.of(pieceColor, Bishop.getInstance()));
        initialBoard.put(Position.of(ChessFile.from('g'), ChessRank.from(rank)), ChessPiece.of(pieceColor, Knight.getInstance()));
        initialBoard.put(Position.of(ChessFile.from('h'), ChessRank.from(rank)), ChessPiece.of(pieceColor, Rook.getInstance()));
    }
}
