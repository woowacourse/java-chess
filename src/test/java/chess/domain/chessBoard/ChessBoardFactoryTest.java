package chess.domain.chessBoard;

import chess.domain.chessPiece.*;
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
            initialBoard.put(Position.of(chessFile, ChessRank.from(rank)),
                    ChessPiece.of(pieceColor.convertName(Pawn.NAME)));
        }
    }

    private static void addOtherPiecesBy(Map<Position, ChessPiece> initialBoard, PieceColor pieceColor, int rank) {
        initialBoard.put(Position.of("a" + rank), ChessPiece.of(pieceColor.convertName(Rook.NAME)));
        initialBoard.put(Position.of("b" + rank), ChessPiece.of(pieceColor.convertName(Knight.NAME)));
        initialBoard.put(Position.of("c" + rank), ChessPiece.of(pieceColor.convertName(Bishop.NAME)));
        initialBoard.put(Position.of("d" + rank), ChessPiece.of(pieceColor.convertName(Queen.NAME)));
        initialBoard.put(Position.of("e" + rank), ChessPiece.of(pieceColor.convertName(King.NAME)));
        initialBoard.put(Position.of("f" + rank), ChessPiece.of(pieceColor.convertName(Bishop.NAME)));
        initialBoard.put(Position.of("g" + rank), ChessPiece.of(pieceColor.convertName(Knight.NAME)));
        initialBoard.put(Position.of("h" + rank), ChessPiece.of(pieceColor.convertName(Rook.NAME)));
    }
}
