package chess.domain.chessgame;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.ChessPieceType;
import java.util.List;

public class ChessGame {

    private final ChessBoard chessBoard;

    public ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public List<Square> settingMoveSquare(List<String> input) {
        return createMoveSquare(extractMoveSquare(input));
    }

    private List<String> extractMoveSquare(List<String> input) {
        return input.subList(1, input.size());
    }

    private List<Square> createMoveSquare(List<String> moveSquare) {
        Square moveSource = new Square(moveSquare.get(0));
        Square target = new Square(moveSquare.get(1));
        return List.of(moveSource, target);
    }

    public void executeTurn(Square moveSource, Square target) {
        ChessPiece chessPieceOnSquare = chessBoard.findChessPieceOnSquare(moveSource);
        validateEmptyChessPiece(chessPieceOnSquare);
        chessPieceOnSquare.move(chessBoard, moveSource, target);
    }

    private void validateEmptyChessPiece(ChessPiece chessPieceOnSquare) {
        if (chessPieceOnSquare.getChessPieceType() == ChessPieceType.NONE) {
            throw new IllegalArgumentException("[ERROR] 이동할 체스말이 없습니다.");
        }
    }
}
