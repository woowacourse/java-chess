package chess.domain.chessboard;

import chess.domain.chesspiece.Camp;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.ChessPieceProperty;
import chess.domain.chesspiece.ChessPieceType;
import chess.domain.chesspiece.movestrategy.EmptyMoveStrategy;
import chess.dto.ChessBoardDto;
import chess.dto.ChessPieceDto;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {

    private static final BoardGenerator BOARD_GENERATOR = BoardGenerator.getInstance();

    private final Map<Square, ChessPiece> board;

    private ChessBoard(Map<Square, ChessPiece> board) {
        this.board = board;
    }

    public ChessBoard() {
        this(BOARD_GENERATOR.generate());
    }

    public ChessPiece findChessPieceOnSquare(Square findSquare) {
        return board.get(findSquare);
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
        ChessPiece moveSourceChessPiece = board.get(moveSource);
        ChessPiece emptyChessPiece = new ChessPiece(Camp.NONE,
                new ChessPieceProperty(ChessPieceType.NONE, new EmptyMoveStrategy()));
        board.computeIfPresent(moveSource, (k, v) -> emptyChessPiece);
        board.computeIfPresent(target, (k, v) -> moveSourceChessPiece);
    }

    public ChessBoardDto createDto() {
        Map<Square, ChessPieceDto> chessBoardInfo = new LinkedHashMap<>();

        for (Square square : board.keySet()) {
            ChessPiece chessPiece = board.get(square);
            chessBoardInfo.put(square, chessPiece.createDto());
        }

        return new ChessBoardDto(chessBoardInfo);
    }
}
