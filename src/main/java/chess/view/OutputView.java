package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.Rank;
import chess.dto.ChessBoardDto;
import java.util.Map;

public class OutputView {
    public void printChessBoard(ChessBoardDto chessBoardDto) {
        Map<Position, Piece> chessBoard = chessBoardDto.chessBoard();

        for (Rank rank : Rank.values()) {
            printEachRank(chessBoard, rank);
            System.out.println();
        }
        System.out.println();
    }

    private void printEachRank(Map<Position, Piece> chessBoard, Rank rank) {
        for (File file : File.values()) {
            printEachPiece(chessBoard, rank, file);
        }
    }

    private void printEachPiece(Map<Position, Piece> chessBoard, Rank rank, File file) {
        Position position = Positions.of(rank, file);
        if (chessBoard.containsKey(position)) {
            Piece piece = chessBoard.get(position);
            System.out.print(PieceSymbol.convertToSymbol(piece));
            return;
        }
        System.out.print(PieceSymbol.printEmptySymbol());
    }
}
