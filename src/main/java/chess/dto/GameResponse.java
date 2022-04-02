package chess.dto;

import java.util.Map;

public abstract class GameResponse {

    private final Map<String, String> information;
    private final String metaInformation;

    public GameResponse(Map<String, String> information, String metaInformation) {
        this.information = information;
        this.metaInformation = metaInformation;
    }

    public Map<String, String> getInformation() {
        return information;
    }

    public String getMetaInformation() {
        return metaInformation;
    }
}
