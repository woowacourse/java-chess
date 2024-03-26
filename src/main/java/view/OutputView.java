package view;

import domain.game.ChessBoard;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.piecerole.Bishop;
import domain.piece.piecerole.BlackPawn;
import domain.piece.piecerole.King;
import domain.piece.piecerole.Knight;
import domain.piece.piecerole.Queen;
import domain.piece.piecerole.Rook;
import domain.piece.piecerole.WhitePawn;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.HashMap;
import java.util.Map;

public class OutputView {
    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.%n"
            + "> 게임 시작 : start%n"
            + "> 게임 종료 : end%n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n";
    private static final Map<Piece, String> pieceSymbol = new HashMap<>();

    static {
        pieceSymbol.put(new Piece(Rook.from(), Color.BLACK), "R");
        pieceSymbol.put(new Piece(Knight.from(), Color.BLACK), "N");
        pieceSymbol.put(new Piece(Bishop.from(), Color.BLACK), "B");
        pieceSymbol.put(new Piece(Queen.from(), Color.BLACK), "Q");
        pieceSymbol.put(new Piece(King.from(), Color.BLACK), "K");
        pieceSymbol.put(new Piece(BlackPawn.from(), Color.BLACK), "P");

        pieceSymbol.put(new Piece(Rook.from(), Color.WHITE), "r");
        pieceSymbol.put(new Piece(Knight.from(), Color.WHITE), "n");
        pieceSymbol.put(new Piece(Bishop.from(), Color.WHITE), "b");
        pieceSymbol.put(new Piece(Queen.from(), Color.WHITE), "q");
        pieceSymbol.put(new Piece(King.from(), Color.WHITE), "k");
        pieceSymbol.put(new Piece(WhitePawn.from(), Color.WHITE), "p");
    }

    public void printStartMessage() {
        System.out.printf(GAME_START_MESSAGE);
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public void printChessBoard(final ChessBoard mover) {
        for (int rank = 8; rank >= 1; rank--) {
            printSquareByFile(mover, rank);
            System.out.println();
        }
        System.out.println();
    }

    private void printSquareByFile(ChessBoard mover, int row) {
        for (int file = 0; file < 8; file++) {
            Position position = new Position(
                    new Position(new File((char) ('a' + file)), new Rank(row)));
            System.out.print(generateSymbol(mover, position));
        }
    }

    public String generateSymbol(ChessBoard mover, Position position) {
        if (mover.hasPiece(position)) {
            Piece piece = mover.findPieceByPosition(position);
            return pieceSymbol.get(piece);
        }
        return ".";
    }
}
