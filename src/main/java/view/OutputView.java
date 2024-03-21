package view;

import domain.chessboard.Square;
import domain.game.PieceMover;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceType;
import domain.piece.piecerole.Bishop;
import domain.piece.piecerole.King;
import domain.piece.piecerole.Knight;
import domain.piece.piecerole.Pawn;
import domain.piece.piecerole.Queen;
import domain.piece.piecerole.Rook;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.HashMap;
import java.util.Map;

public class OutputView {
    private static final String START_OR_END_MESSAGE = "체스 게임을 시작합니다.%n게임 시작은 start, 종료는 end 명령을 입력하세요.%n";

    private static final Map<PieceType, String> pieceSymbol = new HashMap<>();

    static {
        pieceSymbol.put(new PieceType(new Rook(), Color.BLACK), "R");
        pieceSymbol.put(new PieceType(new Knight(), Color.BLACK), "N");
        pieceSymbol.put(new PieceType(new Bishop(), Color.BLACK), "B");
        pieceSymbol.put(new PieceType(new Queen(), Color.BLACK), "Q");
        pieceSymbol.put(new PieceType(new King(), Color.BLACK), "K");
        pieceSymbol.put(new PieceType(new Pawn(Color.BLACK), Color.BLACK), "P");

        pieceSymbol.put(new PieceType(new Rook(), Color.WHITE), "r");
        pieceSymbol.put(new PieceType(new Knight(), Color.WHITE), "n");
        pieceSymbol.put(new PieceType(new Bishop(), Color.WHITE), "b");
        pieceSymbol.put(new PieceType(new Queen(), Color.WHITE), "q");
        pieceSymbol.put(new PieceType(new King(), Color.WHITE), "k");
        pieceSymbol.put(new PieceType(new Pawn(Color.WHITE), Color.WHITE), "p");
    }

    public void printCommandMessage() {
        System.out.printf(START_OR_END_MESSAGE);
    }

    public void printSquareStatus(final PieceMover mover) {
        for (int rank = 8; rank >= 1; rank--) {
            printSquareByFile(mover, rank);
            System.out.println();
        }
    }

    private void printSquareByFile(PieceMover mover, int row) {
        for (int file = 0; file < 8; file++) {
            Square square = new Square(new Position(new File((char) ('a' + file)), new Rank(row)));
            System.out.print(generateSymbol(mover, square));
        }
    }

    public String generateSymbol(PieceMover mover, Square square) {
        if (mover.hasPiece(square)) {
            Piece piece = mover.findPieceBySquare(square);
            return pieceSymbol.get(piece.getPieceType());
        }
        return ".";
    }
}
