package chess.domain.chessgame;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Lettering;
import chess.domain.chessboard.Numbering;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.ChessPiece;
import java.util.ArrayList;
import java.util.List;

public class ChessGame {

    private static final Integer FORMATTING_TO_UPPERCASE_LETTER_ASCII_NUMBER = 64;
    private static final Integer FORMATTING_TO_LOWERCASE_LETTER_ASCII_NUMBER = 96;
    private static final Integer FORMATTING_TO_NUMBER_ASCII_NUMBER = 48;

    private final ChessBoard chessBoard;

    public ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public List<Square> settingMoveSquare(List<String> input) {
        return createMoveSquare(extractMoveSquare(input));
    }

    private List<String> extractMoveSquare(List<String> input) {
        List<String> moveSquare = new ArrayList<>();
        moveSquare.add(input.get(1));
        moveSquare.add(input.get(2));
        return moveSquare;
    }

    private List<Square> createMoveSquare(List<String> moveSquare) {
        Square moveSource = createSquare(moveSquare.get(0));
        Square target = createSquare(moveSquare.get(1));
        return List.of(moveSource, target);
    }

    private Square createSquare(String inputSquare) {
        char letter = inputSquare.charAt(0);
        char number = inputSquare.charAt(1);
        Numbering numbering = Numbering.findNumbering(number - FORMATTING_TO_NUMBER_ASCII_NUMBER);
        return createSquareByCamp(letter, numbering);
    }

    private Square createSquareByCamp(char letter, Numbering numbering) {
        if (Character.isUpperCase(letter)) {
            return new Square(
                    Lettering.findLettering(letter - FORMATTING_TO_UPPERCASE_LETTER_ASCII_NUMBER),
                    numbering);
        }
        return new Square(
                Lettering.findLettering(letter - FORMATTING_TO_LOWERCASE_LETTER_ASCII_NUMBER),
                numbering);
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
