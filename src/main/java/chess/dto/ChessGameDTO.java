package chess.dto;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.Team;
import chess.domain.team.WhiteTeam;
import chess.view.PieceConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameDTO {
    private final TeamDTO blackTeamDTO;
    private final TeamDTO whiteTeamDTO;
    private final boolean isRunning;

    private ChessGameDTO(final TeamDTO blackTeam, final TeamDTO whiteTeam, final boolean isRunning) {
        this.blackTeamDTO = blackTeam;
        this.whiteTeamDTO = whiteTeam;
        this.isRunning = isRunning;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public ChessGame toChessGame() {
        BlackTeam blackTeam = createBlackTeam();
        WhiteTeam whiteTeam = createWhiteTeam();

        Team currentTurn = whiteTeam;
        if (blackTeam.isCurrentTurn()) {
            currentTurn = blackTeam;
        }

        blackTeam.setEnemy(whiteTeam);
        whiteTeam.setEnemy(blackTeam);

        return new ChessGame(blackTeam, whiteTeam, currentTurn, !isRunning());
    }

    private BlackTeam createBlackTeam() {
        PiecesDTO blackPiecesDTO = blackTeamDTO.getPiecesDto();
        List<PieceDTO> blackPieceDTOs = blackPiecesDTO.getPieceDtoList();
        Map<Position, Piece> blackPiecePositions = new HashMap<>();

        for (PieceDTO pieceDTO : blackPieceDTOs) {
            Position position = Position.of(pieceDTO.getPosition());
            Piece piece = PieceConverter.convertToPiece(pieceDTO.getPiece());
            blackPiecePositions.put(position, piece);
        }

        BlackTeam blackTeam = new BlackTeam(blackTeamDTO.getName(), blackTeamDTO.isTurn(), blackPiecePositions);
        return blackTeam;
    }

    private WhiteTeam createWhiteTeam() {
        PiecesDTO whitePiecesDTO = whiteTeamDTO.getPiecesDto();
        List<PieceDTO> whitePieceDTOs = whitePiecesDTO.getPieceDtoList();
        Map<Position, Piece> whitePiecePositions = new HashMap<>();

        for (PieceDTO pieceDTO : whitePieceDTOs) {
            Position position = Position.of(pieceDTO.getPosition());
            Piece piece = PieceConverter.convertToPiece(pieceDTO.getPiece());
            whitePiecePositions.put(position, piece);
        }

        WhiteTeam whiteTeam = new WhiteTeam(whiteTeamDTO.getName(), whiteTeamDTO.isTurn(), whitePiecePositions);
        return whiteTeam;
    }

    public static ChessGameDTO from(ChessGame chessGame) {
        Map<Position, String> blackPieces = convertToBlackPrintName(chessGame.getBlackTeam().getPiecePosition());
        Map<Position, String> whitePieces = convertToWhitePrintName(chessGame.getWhiteTeam().getPiecePosition());

        PiecesDTO blackPiecesDto = createPiecesDTO(blackPieces);
        PiecesDTO whitePiecesDto = createPiecesDTO(whitePieces);

        TeamDTO whiteTeamDTO = new TeamDTO(whitePiecesDto, chessGame.getWhiteTeam().getName(),
                String.valueOf(chessGame.getWhiteTeam().calculateTotalScore()),
                chessGame.getWhiteTeam().isCurrentTurn());

        TeamDTO blackTeamDTO = new TeamDTO(blackPiecesDto, chessGame.getBlackTeam().getName(),
                String.valueOf(chessGame.getBlackTeam().calculateTotalScore()),
                chessGame.getBlackTeam().isCurrentTurn());

        return new ChessGameDTO(blackTeamDTO, whiteTeamDTO, !chessGame.isEnd());
    }

    private static PiecesDTO createPiecesDTO(final Map<Position, String> pieces) {
        List<PieceDTO> pieceDTOs = new ArrayList<>();

        for (Map.Entry<Position, String> entry : pieces.entrySet()) {
            pieceDTOs.add(new PieceDTO(entry.getKey().getKey(), entry.getValue()));
        }

        PiecesDTO piecesDTO = new PiecesDTO(pieceDTOs);
        return piecesDTO;
    }

    private static Map<Position, String> convertToBlackPrintName(final Map<Position, Piece> pieces) {
        Map<Position, String> blackPrintFormat = new HashMap<>();
        for (Position position : pieces.keySet()) {
            final Piece piece = pieces.get(position);
            blackPrintFormat.put(position, PieceConverter.convertToPieceName(piece).toUpperCase());
        }
        return blackPrintFormat;
    }

    private static Map<Position, String> convertToWhitePrintName(final Map<Position, Piece> pieces) {
        Map<Position, String> whitePrintFormat = new HashMap<>();
        for (Position position : pieces.keySet()) {
            final Piece piece = pieces.get(position);
            if (piece == null) continue;
            whitePrintFormat.put(position, PieceConverter.convertToPieceName(piece).toLowerCase());
        }
        return whitePrintFormat;
    }
}
