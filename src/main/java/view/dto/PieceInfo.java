package view.dto;

import model.piece.Color;
import model.piece.role.RoleStatus;
import model.position.File;
import model.position.Rank;

public record PieceInfo(
        File file,
        Rank rank,
        RoleStatus roleStatus,
        Color color) {
}
