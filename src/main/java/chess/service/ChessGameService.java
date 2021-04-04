package chess.service;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.Team;
import chess.domain.team.WhiteTeam;
import chess.dto.*;
import chess.repository.ChessRepository;
import chess.view.PieceConverter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameService {
    private ChessRepository chessRepository;
    private Gson gson;


    public ChessGameService(final ChessRepository chessRepository) {
        this.chessRepository = chessRepository;
        this.gson = new Gson();
    }

    public ResponseDTO createChessGame() {
        ChessGameDTO chessGameDTO = createChessGameDTO(new ChessGame(new BlackTeam(), new WhiteTeam()));
        chessRepository.createChessGame(gson.toJson(chessGameDTO));
        return new ResponseDTO(true, gson.toJson(chessGameDTO), "게임이 생성 되었습니다.");
    }

    public ResponseDTO refreshChessGame(final String roomId) {
        RoomDTO roomDTO = chessRepository.findRoomFromId(roomId);
        ChessGameDTO chessGameDTO = createChessGameDTO(new ChessGame(new BlackTeam(), new WhiteTeam()));
        chessRepository.saveChessGame(roomDTO.getGameId(), gson.toJson(chessGameDTO));
        return new ResponseDTO(true, gson.toJson(chessGameDTO), "게임이 재시작 되었습니다.");
    }

    public ResponseDTO loadChessGame(final String roomId) {
        RoomDTO roomDTO = chessRepository.findRoomFromId(roomId);
        String chessData = chessRepository.loadChessGame(roomDTO.getGameId());
        return new ResponseDTO(true, chessData, "게임을 로드 하였습니다.");
    }

    public ResponseDTO selectPiece(final String roomId, final String selected) {
        Position selectedPosition = Position.of(selected);

        RoomDTO roomDTO = chessRepository.findRoomFromId(roomId);
        String chessGameData = chessRepository.loadChessGame(roomDTO.getGameId());
        ChessGameDTO chessGameDTO = gson.fromJson(chessGameData, ChessGameDTO.class);
        ChessGame chessGame = createChessGame(chessGameDTO);

        boolean havePiece = chessGame.havePieceInCurrentTurn(selectedPosition);
        if (havePiece) {
            return new ResponseDTO(true, "", "기물을 선택 하셨습니다.");
        }
        return new ResponseDTO(false, "", "잘못 선택 하셨습니다.");
    }

    public ResponseDTO moveChessGame(final String roomId, final String selected, final String target) {
        Position selectedPosition = Position.of(selected);
        Position targetPosition = Position.of(target);

        RoomDTO roomDTO = chessRepository.findRoomFromId(roomId);
        String chessGameData = chessRepository.loadChessGame(roomDTO.getGameId());
        ChessGameDTO chessGameDTO = gson.fromJson(chessGameData, ChessGameDTO.class);
        ChessGame chessGame = createChessGame(chessGameDTO);

        boolean isSuccess = chessGame.move(selectedPosition, targetPosition);
        if (isSuccess) {
            ChessGameDTO movedChessGameDTO = createChessGameDTO(chessGame);
            chessRepository.saveChessGame(roomDTO.getGameId(), gson.toJson(movedChessGameDTO));
            return new ResponseDTO(true, gson.toJson(movedChessGameDTO), "움직였습니다.");
        }
        return new ResponseDTO(false, "", "움직일 수 없습니다.");
    }

    public ResponseDTO createRoom(final String data) {
        createChessGame();
        RoomDTO roomDTO = gson.fromJson(data, RoomDTO.class);
        RoomsDTO roomsDTO = chessRepository.createRoom(roomDTO.getName(), roomDTO.getPw());
        return new ResponseDTO(true, gson.toJson(roomsDTO), "방을 생성하였습니다.");
    }

    public ResponseDTO getTotalRoom() {
        RoomsDTO roomsDTO = chessRepository.getTotalRoom();
        return new ResponseDTO(true, gson.toJson(roomsDTO), "방을 가져왔습니다.");
    }

    private ChessGame createChessGame(final ChessGameDTO chessGameDTO) {
        TeamDTO blackTeamDTO = chessGameDTO.getBlackTeam();
        BlackTeam blackTeam = createBlackTeam(blackTeamDTO);

        TeamDTO whiteTeamDTO = chessGameDTO.getWhiteTeam();
        WhiteTeam whiteTeam = createWhiteTeam(whiteTeamDTO);

        Team currentTurn = whiteTeam;
        if (blackTeam.isCurrentTurn()) {
            currentTurn = blackTeam;
        }

        blackTeam.setEnemy(whiteTeam);
        whiteTeam.setEnemy(blackTeam);

        return new ChessGame(blackTeam, whiteTeam, currentTurn, !chessGameDTO.isRunning());
    }


    private BlackTeam createBlackTeam(final TeamDTO blackTeamDTO) {
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

    private WhiteTeam createWhiteTeam(final TeamDTO whiteTeamDTO) {
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

    private ChessGameDTO createChessGameDTO(final ChessGame chessGame) {
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

    private PiecesDTO createPiecesDTO(final Map<Position, String> pieces) {
        List<PieceDTO> pieceDTOs = new ArrayList<>();

        for (Map.Entry<Position, String> entry : pieces.entrySet()) {
            pieceDTOs.add(new PieceDTO(entry.getKey().getKey(), entry.getValue()));
        }

        PiecesDTO piecesDTO = new PiecesDTO(pieceDTOs);
        return piecesDTO;
    }

    private Map<Position, String> convertToBlackPrintName(final Map<Position, Piece> pieces) {
        Map<Position, String> blackPrintFormat = new HashMap<>();
        for (Position position : pieces.keySet()) {
            final Piece piece = pieces.get(position);
            blackPrintFormat.put(position, PieceConverter.convertToPieceName(piece).toUpperCase());
        }
        return blackPrintFormat;
    }

    private Map<Position, String> convertToWhitePrintName(final Map<Position, Piece> pieces) {
        Map<Position, String> whitePrintFormat = new HashMap<>();
        for (Position position : pieces.keySet()) {
            final Piece piece = pieces.get(position);
            if (piece == null) continue;
            whitePrintFormat.put(position, PieceConverter.convertToPieceName(piece).toLowerCase());
        }
        return whitePrintFormat;
    }

}
