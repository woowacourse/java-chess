package chess.domain.chessboard;

import chess.domain.chesspiece.ChessPiece;
import chess.dto.ChessBoardDto;
import chess.dto.ChessPieceDto;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ChessBoard {

    private static final BoardGenerator BOARD_GENERATOR = BoardGenerator.getInstance();

    private final Map<Square, Optional<ChessPiece>> board;

    private ChessBoard(Map<Square, Optional<ChessPiece>> board) {
        this.board = board;
    }

    public ChessBoard() {
        this(BOARD_GENERATOR.generate());
    }

    public ChessBoardDto createDto() {
        Map<Square, Optional<ChessPieceDto>> chessBoardInfo = new LinkedHashMap<>();

        for (Square square : board.keySet()) {
            Optional<ChessPiece> chessPiece = board.get(square);
            Optional<ChessPieceDto> chessPieceDto = createChessPieceDto(chessPiece);
            chessBoardInfo.put(square, chessPieceDto);
        }

        return new ChessBoardDto(chessBoardInfo);
    }

    private Optional<ChessPieceDto> createChessPieceDto(Optional<ChessPiece> chessPiece) {
        if (chessPiece.isEmpty()) {
            return Optional.empty();
        }

        ChessPiece existChessPiece = chessPiece.get();
        return Optional.of(existChessPiece.createDto());
    }
}
