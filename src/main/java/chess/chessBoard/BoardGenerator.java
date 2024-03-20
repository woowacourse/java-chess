package chess.chessboard;

import chess.chesspiece.ChessPiece;
import chess.chesspiece.ChessPieceGenerator;
import chess.chesspiece.ChessPieceStartingPosition;
import chess.chesspiece.ChessPieceType;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class BoardGenerator {

    private static final BoardGenerator INSTANCE = new BoardGenerator();
    private static final Map<Square, Optional<ChessPiece>> CHESS_BOARD_CACHE = createBoard();

    private BoardGenerator() {
    }

    public static BoardGenerator getInstance() {
        return INSTANCE;
    }

    public Map<Square, Optional<ChessPiece>> generate() {
        return new LinkedHashMap<>(CHESS_BOARD_CACHE);
    }

    private static Map<Square, Optional<ChessPiece>> createBoard() {
        List<Square> squares = createSquares();
        Map<Square, Optional<ChessPiece>> chessBoard = new LinkedHashMap<>();
        putChessPieceOnBoard(squares, chessBoard);

        return chessBoard;
    }

    private static List<Square> createSquares() {
        return Arrays.stream(Lettering.values())
                .flatMap(lettering -> Arrays.stream(Numbering.values())
                        .map(numbering -> new Square(lettering, numbering)))
                .toList();
    }

    private static void putChessPieceOnBoard(List<Square> squares, Map<Square, Optional<ChessPiece>> chessBoard) {
        for (Square square : squares) {
            Optional<ChessPiece> chessPiece = createChessPieceForSquare(square);
            chessBoard.put(square, chessPiece);
        }
    }

    private static Optional<ChessPiece> createChessPieceForSquare(Square square) {
        ChessPieceGenerator chessPieceGenerator = ChessPieceGenerator.getInstance();

        ChessPieceType chessPieceType = determineChessPieceType(square);
        if (chessPieceType != null) {
            ChessPiece createdChessPiece = chessPieceGenerator.generate(chessPieceType, square.getNumbering());
            return Optional.of(createdChessPiece);
        }

        return Optional.empty();
    }

    private static ChessPieceType determineChessPieceType(Square square) {
        ChessPieceStartingPosition chessPieceStartingPosition = ChessPieceStartingPosition.getInstance();
        return chessPieceStartingPosition.determineChessPieceType(square).orElse(null);
    }
}
