package chess.view;

import chess.domain.chessBoard.Space;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.List;

public class OutputView {

    private final int CHESS_BOARD_WIDTH = 8;
    public static final String EMPTY_PIECE_SIGN = ".";

    public void printStartGameMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
    }

    public void printCommandGuideMessage() {
        System.out.println(
                "> 게임 시작 : start" + System.lineSeparator()
                        + "> 게임 종료 : end" + System.lineSeparator()
                        + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(List<Space> spaces) {
        for (int rankOrder = 0; rankOrder < CHESS_BOARD_WIDTH; rankOrder++) {
            printOneRankBoard(spaces, rankOrder);
        }
        System.out.println();
    }

    private void printOneRankBoard(List<Space> spaces, int rankOrder) {
        for (int spaceNumber = CHESS_BOARD_WIDTH * rankOrder;
             spaceNumber < CHESS_BOARD_WIDTH * rankOrder + CHESS_BOARD_WIDTH; spaceNumber++) {
            Space space = spaces.get(spaceNumber);
            Piece piece = space.getPiece();
            System.out.print(findSign(piece));
        }
        System.out.println();
    }

    private String findSign(Piece piece) {
        PieceType pieceType = piece.getPieceType();
        String sign = EMPTY_PIECE_SIGN;
        if (pieceType == PieceType.PAWN) {
            sign = "p";
        }
        if (pieceType == PieceType.ROOK) {
            sign = "r";
        }
        if (pieceType == PieceType.BISHOP) {
            sign = "b";
        }
        if (pieceType == PieceType.KNIGHT) {
            sign = "n";
        }
        if (pieceType == PieceType.QUEEN) {
            sign = "q";
        }
        if (pieceType == PieceType.KING) {
            sign = "k";
        }
        if (piece.isSameColor(Color.BLACK)) {
            sign = sign.toUpperCase();
        }
        return sign;
    }

    public void printWrongTurn() {
        System.out.println("상대방의 말은 움직일 수 없습니다.");
    }
}
