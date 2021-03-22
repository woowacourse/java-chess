package chess.domain.game;

import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.dto.response.BoardResponseDTO;
import chess.controller.dto.response.ScoresResponseDTO;
import chess.domain.board.Board;
import chess.domain.board.setting.BoardSetting;
import chess.domain.piece.Piece;
import chess.domain.piece.type.PieceWithColorType;
import chess.domain.player.Players;
import chess.domain.player.type.TeamColor;
import chess.domain.position.MoveRoute;
import chess.domain.position.Position;
import chess.domain.position.cache.PositionsCache;
import java.util.List;

public class ChessGame {
    private final Players players;
    private final Board board;

    public ChessGame(BoardSetting boardSetting) {
        players = new Players();
        board = new Board();
        setPieces(boardSetting);
    }

    private void setPieces(BoardSetting boardSetting) {
        List<PieceWithColorType> piecesSetting = boardSetting.getPiecesSetting();
        for (int index = 0; index < piecesSetting.size(); index++) {
            Position position = PositionsCache.get(index);
            PieceWithColorType pieceWithColorType = piecesSetting.get(index);
            setPiece(pieceWithColorType, position);
        }
    }

    private void setPiece(PieceWithColorType pieceWithColorType, Position position) {
        Piece piece = null;
        if (pieceWithColorType != null) {
            piece = Piece.of(pieceWithColorType);
            players.add(piece, position);
        }
        board.setPiece(position, piece);
    }

    public void move(MoveRequestDTO moveRequestDTO) {
        MoveRoute moveRoute = new MoveRoute(moveRequestDTO);
        TeamColor teamColor = TeamColor.of(moveRequestDTO.getTeamColor());
        board.validateRoute(moveRoute, teamColor);
        updatePiecesOfPlayers(moveRoute);
        board.move(moveRoute);
    }

    private void updatePiecesOfPlayers(MoveRoute moveRoute) {
        Piece movingPiece = board.findPiece(moveRoute.startPosition());
        players.remove(movingPiece, moveRoute.startPosition());
        players.add(movingPiece, moveRoute.destination());
        if (board.isAnyPieceExistsInCell(moveRoute.destination())) {
            Piece deadPiece = board.findPiece(moveRoute.destination());
            players.remove(deadPiece, moveRoute.destination());
        }
    }

    public boolean isKingDead() {
        return board.isKingDead();
    }

    public BoardResponseDTO boardStatus() {
        return board.status();
    }

    public ScoresResponseDTO getScores() {
        return new ScoresResponseDTO(players.blackPlayerScore(), players.whitePlayerScore());
    }
}
