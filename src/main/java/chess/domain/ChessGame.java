package chess.domain;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.ChessPiece;

public class ChessGame {

    private final ChessBoard chessBoard;

    public ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void executeTurn(Square moveSource, Square target) {
        ChessPiece chessPieceOnSquare = chessBoard.findChessPieceOnSquare(moveSource).orElse(null);
        validateEmptyChessPiece(chessPieceOnSquare);

        chessPieceOnSquare.move(chessBoard, moveSource, target);
    }

    private void validateEmptyChessPiece(ChessPiece chessPieceOnSquare) {
        if (chessPieceOnSquare == null) {
            throw new IllegalArgumentException("[ERROR] 이동할 체스말이 없습니다.");
        }
    }
}
