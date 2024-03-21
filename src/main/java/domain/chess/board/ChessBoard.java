package domain.chess.board;

import static domain.chess.board.InitialPieces.INITIAL_PIECES;
import static domain.chess.piece.PieceMoveResult.CATCH;
import static domain.chess.piece.PieceMoveResult.FAILURE;
import static domain.chess.piece.Team.WHITE;

import domain.Position;
import domain.chess.piece.Piece;
import domain.chess.piece.PieceMoveResult;
import domain.chess.piece.Team;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ChessBoard {
    private final List<Piece> piecesOnBoard;
    private Team currentTeam = WHITE;

    public ChessBoard() {
        this(INITIAL_PIECES);
    }

    public ChessBoard(List<Piece> pieces) {
        piecesOnBoard = new ArrayList<>(pieces);
    }

    public ChessBoard(Piece... pieces) {
        this(List.of(pieces));
    }

    boolean move(Position from, Position to) {
        if (isEmptyPosition(from) || isOtherTeamTurn(from)) {
            return false;
        }
        Piece piece = findPiece(from);
        PieceMoveResult moveResult = piece.move(to, this);
        removePieceIfCaught(to, moveResult);
        changeCurrentTeamIfNotFail(moveResult);
        return moveResult.toBoolean();
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

    public Optional<Team> whichTeam(Position position) {
        Optional<Piece> pieceOnPosition = piecesOnBoard.stream().filter(piece -> piece.isOn(position))
                .findFirst();
        return pieceOnPosition.map(Piece::getTeam);
    }

    public List<Piece> getPiecesOnBoard() {
        return Collections.unmodifiableList(piecesOnBoard);
    }
}
