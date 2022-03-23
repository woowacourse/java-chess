package chess.view;

import static java.lang.System.*;

import java.util.Map;

import chess.File;
import chess.Piece;
import chess.PieceColor;
import chess.PieceType;
import chess.Position;
import chess.Rank;

public class OutputView {

    public static void printInitMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printChessGameBoard(Map<Position, Piece> piecesByPositions) {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Position searchPosition = new Position(rank, file);
                Piece piece = piecesByPositions.getOrDefault(searchPosition, new Piece(PieceType.EMPTY, PieceColor.EMPTY));
                out.print(piece.getPieceType().getName());
            }
            out.println();
        }
    }
}
