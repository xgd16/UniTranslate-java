package net.todream.uni_translate.uni_translate.dto;

import lombok.Data;

@Data
public class TranslateRespGoogleDto {
        private Data data;
        
        @lombok.Data
        public static class Data {
            private Translation[] translations;
        }
        
        @lombok.Data
        public static class Translation {
            private String translatedText;
            private String detectedSourceLanguage;
        }
}
