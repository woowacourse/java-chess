package chess.view;

import chess.controller.ChessBoardDto;
import chess.controller.PieceDto;

import java.util.List;
import java.util.Objects;

public class OutputView {
    public void printChessBoard(ChessBoardDto chessBoardDto) {
        final List<List<PieceDto>> pieceDtos = chessBoardDto.getPieceDtos();
        for (List<PieceDto> rank : pieceDtos) {
            printRank(rank);
        }
    }

    private void printRank(final List<PieceDto> rank) {
        for (PieceDto piece : rank) {
            final String type = piece.getType();
            final String team = piece.getTeam();
            printPiece(type, team);
        }
    }

    private void printPiece(final String type, final String team) {
        if (Objects.equals(type, "EMPTY_PIECE")) {
            System.out.print(".");
        }
        if (Objects.equals(type, "PAWN")) {
            printLetter("p", team);
        }
        if (Objects.equals(type, "ROOK")) {
            printLetter("r", team);
        }
        if (Objects.equals(type, "KNIGHT")) {
            printLetter("n", team);
        }
        if (Objects.equals(type, "BISHOP")) {
            printLetter("b", team);
        }
        if (Objects.equals(type, "QUEEN")) {
            printLetter("q", team);
        }
        if (Objects.equals(type, "KING")) {
            printLetter("k", team);
        }
    }

    private void printLetter(final String letter, final String team) {
        if (Objects.equals(team, "BLACK")) {
            System.out.print(letter.toUpperCase());
            return;
        }
        System.out.print(letter.toLowerCase());
    }
}
