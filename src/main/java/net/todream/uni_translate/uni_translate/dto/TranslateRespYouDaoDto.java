package net.todream.uni_translate.uni_translate.dto;

import java.util.List;

import lombok.Data;

@Data
public class TranslateRespYouDaoDto {
    private String tSpeakUrl;
    private String requestId;
    private String query;
    private List<String> translation;
    private TerminalDict mTerminalDict;
    private String errorCode;
    private Dict dict;
    private WebDict webdict;
    private String l;
    private boolean isWord;
    private String speakUrl;

    @lombok.Data
    public static class TerminalDict {

        private String url;
        
    }
    
    @lombok.Data
    public static class Dict {

        private String url;
    
    }
    
    @lombok.Data
    public static class WebDict {

        private String url;
        
    }
    
    @Override
    public String toString() {
        return "YouDaoTranslateResponse{" +
               "tSpeakUrl='" + tSpeakUrl + '\'' +
               ", requestId='" + requestId + '\'' +
               ", query='" + query + '\'' +
               ", translation=" + translation +
               ", mTerminalDict=" + (mTerminalDict != null ? mTerminalDict.url : null) +
               ", errorCode='" + errorCode + '\'' +
               ", dict=" + (dict != null ? dict.url : null) +
               ", webdict=" + (webdict != null ? webdict.url : null) +
               ", l='" + l + '\'' +
               ", isWord=" + isWord +
               ", speakUrl='" + speakUrl + '\'' +
               '}';
    }
}