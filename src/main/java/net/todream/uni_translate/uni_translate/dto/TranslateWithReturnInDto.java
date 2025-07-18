package net.todream.uni_translate.uni_translate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslateWithReturnInDto {

    private TranslateClientInDto in;

    private TranslateClientOutDto out;

    
}
