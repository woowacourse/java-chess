package chess.domain.dto;

import chess.domain.db.ChessBoard;
import chess.domain.piece.*;
import chess.domain.pieceInfo.PieceInfo;
import chess.domain.pieceInfo.Position;
import chess.domain.pieceInfo.Team;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardToBoardDto {
    private final List<Piece> pieces;

    private ChessBoardToBoardDto(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public ChessBoardToBoardDto of(List<ChessBoard> board) {
        List<Piece> boards = makePieces(board);
        return new ChessBoardToBoardDto(boards);
    }

    private List<Piece> makePieces(List<ChessBoard> boards) {
        List<Piece> newBoard = new ArrayList<>();
        boards.forEach(board -> newBoard.add(piece(board)));
        return newBoard;
    }

    private Position position(ChessBoard board) {
        return Position.of(board.position());
    }

    private Piece piece(ChessBoard board) {
        List<Piece> allPieces = pieces(position(board), Team.valueOf(board.team()));
        return allPieces.stream()
                .filter(piece -> piece.getType().name().equals(board.type()))
                .findFirst()
                .orElse(new EmptyPiece(new PieceInfo(position(board), Team.valueOf(board.team()))));
    }

    private List<Piece> pieces(Position position, Team team) {
        PieceInfo pieceInfo = new PieceInfo(position, team);
        return List.of(
                new King(pieceInfo),
                new Queen(pieceInfo),
                new Bishop(pieceInfo),
                new Knight(pieceInfo),
                new Rook(pieceInfo),
                new Pawn(pieceInfo)
        );
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
