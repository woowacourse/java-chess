package chess.domain.chessboard;

import chess.domain.chesspiece.ChessPiece;
import chess.dto.ChessBoardDto;
import chess.dto.ChessPieceDto;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class ChessBoard {

    private static final BoardGenerator BOARD_GENERATOR = BoardGenerator.getInstance();

    private final Map<Square, Optional<ChessPiece>> board;

    private ChessBoard(Map<Square, Optional<ChessPiece>> board) {
        this.board = board;
    }

    public ChessBoard() {
        this(BOARD_GENERATOR.generate());
    }

    public Optional<ChessPiece> findChessPieceOnSquare(Square findSquare) {
        return board.keySet().stream()
                .filter(square -> square == findSquare)
                .map(board::get)
                .findFirst().orElse(null);
    }

    public Square findForwardSquare(Square square) {
        Numbering numbering = Numbering.findNextNumbering(square.getNumbering());
        return new Square(square.getLettering(), numbering);
    }

    public Square findBackwardSquare(Square square) {
        Numbering numbering = Numbering.findPreviousNumbering(square.getNumbering());
        return new Square(square.getLettering(), numbering);
    }

    public Square findLeftSquare(Square square) {
        Lettering lettering = Lettering.findPreviousLettering(square.getLettering());
        return new Square(lettering, square.getNumbering());
    }

    public Square findRightSquare(Square square) {
        Lettering lettering = Lettering.findNextLettering(square.getLettering());
        return new Square(lettering, square.getNumbering());
    }

    public Square findLeftForwardDiagonalSquare(Square square) {
        Lettering lettering = Lettering.findPreviousLettering(square.getLettering());
        Numbering numbering = Numbering.findNextNumbering(square.getNumbering());
        return new Square(lettering, numbering);
    }

    public Square findRightForwardDiagonalSquare(Square square) {
        Lettering lettering = Lettering.findNextLettering(square.getLettering());
        Numbering numbering = Numbering.findNextNumbering(square.getNumbering());
        return new Square(lettering, numbering);
    }

    public Square findLeftBackwardDiagonalSquare(Square square) {
        Lettering lettering = Lettering.findPreviousLettering(square.getLettering());
        Numbering numbering = Numbering.findPreviousNumbering(square.getNumbering());
        return new Square(lettering, numbering);
    }

    public Square findRightBackwardDiagonalSquare(Square square) {
        Lettering lettering = Lettering.findNextLettering(square.getLettering());
        Numbering numbering = Numbering.findPreviousNumbering(square.getNumbering());
        return new Square(lettering, numbering);
    }

    public void movePiece(Square moveSource, Square target) {
        ChessPiece chessPiece = board.get(moveSource).orElseThrow();
        board.put(moveSource, Optional.empty());
        board.put(target, Optional.of(chessPiece));
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
