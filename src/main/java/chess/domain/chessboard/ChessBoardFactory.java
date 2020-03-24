package chess.domain.chessboard;

import chess.domain.File;
import chess.domain.PiecePosition;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.chessPiece.Piece;
import chess.domain.chessPiece.factory.PieceFactory;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.chessPiece.team.WhiteTeam;

import java.util.LinkedList;
import java.util.List;

public class ChessBoardFactory {
    static List<PiecePosition> create() {
        List<PiecePosition> chessBoard = new LinkedList<>();

        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                TeamStrategy team = Team(rank);
                if (rank.getNumber() >= 3 && rank.getNumber() <= 6) {
                    chessBoard.add(new PiecePosition(null, new Position(file, rank)));
                    continue;
                }
                if (rank.getNumber() == 2 || rank.getNumber() == 7) {
                    chessBoard.add(new PiecePosition(PieceFactory.of("pawn", team), new Position(file, rank)));
                    continue;
                }
                if (file.getNumber() == 1 || file.getNumber() == 8) {
                    chessBoard.add(new PiecePosition(PieceFactory.of("rook", team), new Position(file, rank)));
                }
                if (file.getNumber() == 2 || file.getNumber() == 7) {
                    chessBoard.add(new PiecePosition(PieceFactory.of("knight", team), new Position(file, rank)));
                }
                if (file.getNumber() == 3 || file.getNumber() == 6) {
                    chessBoard.add(new PiecePosition(PieceFactory.of("bishop", team), new Position(file, rank)));
                }
                if (file.getNumber() == 4) {
                    chessBoard.add(new PiecePosition(PieceFactory.of("queen", team), new Position(file, rank)));
                }
                if (file.getNumber() == 5) {
                    chessBoard.add(new PiecePosition(PieceFactory.of("king", team), new Position(file, rank)));
                }
            }
        }
        return chessBoard;
    }

    private static TeamStrategy Team(Rank rank) {
        if (rank.getNumber() > 6) {
            return new WhiteTeam();
        }
        return new BlackTeam();
    }
}
