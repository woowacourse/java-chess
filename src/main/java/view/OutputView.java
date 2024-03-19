package view;

import domain.chessboard.ChessBoard;
import domain.chessboard.Rank;
import domain.chessboard.Square;
import domain.piece.Color;
import domain.piece.PieceRole;
import domain.piece.PieceType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String START_OR_END_MESSAGE = "체스 게임을 시작합니다.%n게임 시작은 start, 종료는 end 명령을 입력하세요.%n";

    private static final Map<PieceType, String> pieceSymbol = new HashMap<>();

    static {
        pieceSymbol.put(new PieceType(PieceRole.ROOK, Color.BLACK), "R");
        pieceSymbol.put(new PieceType(PieceRole.KNIGHT, Color.BLACK), "N");
        pieceSymbol.put(new PieceType(PieceRole.BISHOP, Color.BLACK), "B");
        pieceSymbol.put(new PieceType(PieceRole.QUEEN, Color.BLACK), "Q");
        pieceSymbol.put(new PieceType(PieceRole.KING, Color.BLACK), "K");
        pieceSymbol.put(new PieceType(PieceRole.PAWN, Color.BLACK), "P");

        pieceSymbol.put(new PieceType(PieceRole.ROOK, Color.WHITE), "r");
        pieceSymbol.put(new PieceType(PieceRole.KNIGHT, Color.WHITE), "n");
        pieceSymbol.put(new PieceType(PieceRole.BISHOP, Color.WHITE), "b");
        pieceSymbol.put(new PieceType(PieceRole.QUEEN, Color.WHITE), "q");
        pieceSymbol.put(new PieceType(PieceRole.KING, Color.WHITE), "k");
        pieceSymbol.put(new PieceType(PieceRole.PAWN, Color.WHITE), "p");

        pieceSymbol.put(new PieceType(PieceRole.NONE, Color.NONE), ".");
    }

    public void printCommandMessage() {
        System.out.printf(START_OR_END_MESSAGE);
    }

    public void printChessBoard(final ChessBoard chessBoard) {
        List<Rank> ranks = chessBoard.getRanks();
        for (Rank rank : ranks) {
            printRankMessage(rank);
        }
        System.out.println();
    }

    private void printRankMessage(final Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Square square : rank.getSquares()) {
            String symbol = pieceSymbol.get(square.getPiece());
            stringBuilder.append(symbol);
        }
        System.out.println(stringBuilder);
    }
}
