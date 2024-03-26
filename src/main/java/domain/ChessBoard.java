package domain;

import static domain.InitialPieces.INITIAL_PIECES;
import static domain.PieceMoveResult.CATCH;
import static domain.PieceMoveResult.FAILURE;
import static domain.Team.WHITE;

import domain.piece.Piece;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

class ChessBoard {
    private final List<Piece> piecesOnBoard;
    private Team currentTeam = WHITE;

    ChessBoard() {
        this(INITIAL_PIECES);
    }

    ChessBoard(List<Piece> pieces) {
        piecesOnBoard = new ArrayList<>(pieces);
    }

    boolean move(Position from, Position to) {
        if (isEmptyPosition(from) || isOtherTeamTurn(from)) {
            return false;
        }
        Piece piece = findPiece(from);
        PieceMoveResult moveResult = piece.move(to, new PiecesOnChessBoard(piecesOnBoard));
        removePieceIfCaught(to, moveResult);
        changeCurrentTeamIfNotFail(moveResult);
        return moveResult.isMoved();
    }

    private boolean isEmptyPosition(Position from) {
        Optional<Piece> optionalPiece = piecesOnBoard.stream()
                .filter(piece1 -> piece1.isOn(from))
                .findFirst();
        return optionalPiece.isEmpty();
    }

    private boolean isOtherTeamTurn(Position from) {
        Piece piece = findPiece(from);
        Team otherTeam = currentTeam.otherTeam();
        Team pieceTeam = piece.getTeam();
        return pieceTeam.equals(otherTeam);
    }

    private Piece findPiece(Position from) {
        return piecesOnBoard.stream()
                .filter(piece -> piece.isOn(from))
                .findFirst().orElseThrow();
    }

    private void removePieceIfCaught(Position to, PieceMoveResult moveResult) {
        if (moveResult.equals(CATCH)) {
            removeDeadPiece(to);
        }
    }

    private void removeDeadPiece(Position to) {
        Piece needToRemovePiece = piecesOnBoard.stream()
                .filter(piece -> piece.isOn(to))
                .filter(piece -> {
                    Team pieceTeam = piece.getTeam();
                    Team otherTeam = currentTeam.otherTeam();
                    return pieceTeam.equals(otherTeam);
                })
                .findFirst().orElseThrow();
        piecesOnBoard.remove(needToRemovePiece);
    }

    private void changeCurrentTeamIfNotFail(PieceMoveResult moveResult) {
        if (!moveResult.equals(FAILURE)) {
            currentTeam = currentTeam.otherTeam();
        }
    }

    List<Piece> getPiecesOnBoard() {
        return Collections.unmodifiableList(piecesOnBoard);
    }
}
