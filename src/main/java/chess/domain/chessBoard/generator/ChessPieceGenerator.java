package chess.domain.chessBoard.generator;

import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import java.util.ArrayList;
import java.util.List;

public class ChessPieceGenerator implements PieceGenerator {

    public static final int CHESS_BOARD_LENGTH = 8;

    @Override
    public List<Piece> makeSpecialPieces(Color color) {
        return List.of(
                new Rook(color),
                new Knight(color),
                new Bishop(color),
                new Queen(color),
                new King(color),
                new Bishop(color),
                new Knight(color),
                new Rook(color)
        );
    }

    @Override
    public List<Piece> makePawnPieces(Color color) {
        if (color == Color.WHITE) {
            return makeWhitePawnPieces();
        }
        if (color == Color.BLACK) {
            return makeBlackPawnPieces();
        }
        throw new IllegalArgumentException(String.format("%s 색깔의 폰을 생성할 수 없습니다", color));
    }

    @Override
    public List<Piece> makeEmptyPieces() {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < CHESS_BOARD_LENGTH; i++) {
            pieces.add(new EmptyPiece());
        }
        return pieces;
    }

    private List<Piece> makeWhitePawnPieces() {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < CHESS_BOARD_LENGTH; i++) {
            pieces.add(new WhitePawn());
        }
        return pieces;
    }

    private List<Piece> makeBlackPawnPieces() {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < CHESS_BOARD_LENGTH; i++) {
            pieces.add(new BlackPawn());
        }
        return pieces;
    }
}
