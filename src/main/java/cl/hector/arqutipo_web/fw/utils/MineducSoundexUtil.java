package cl.hector.arqutipo_web.fw.utils;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MineducSoundexUtil {
    String cadena1;
    String cadena2;

    private String soundex(String s) {
        char[] x = s.toUpperCase().toCharArray();
        char firstLetter = x[0];
        convierteLetrasACodigoNumerico(x);
        StringBuilder output = remueveDuplicados(x, firstLetter);
        return output.substring(0, 4);
    }

    private void convierteLetrasACodigoNumerico(char[] x) {
        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {

                case 'B':
                case 'F':
                case 'P':
                case 'V':
                    x[i] = '1';
                    break;

                case 'C':
                case 'G':
                case 'J':
                case 'K':
                case 'Q':
                case 'S':
                case 'X':
                case 'Z':
                    x[i] = '2';
                    break;

                case 'D':
                case 'T':
                    x[i] = '3';
                    break;

                case 'L':
                    x[i] = '4';
                    break;

                case 'M':
                case 'N':
                    x[i] = '5';
                    break;

                case 'R':
                    x[i] = '6';
                    break;

                default:
                    x[i] = '0';
                    break;
            }
        }
    }

    private StringBuilder remueveDuplicados(char[] x, char firstLetter) {
        StringBuilder output = new StringBuilder("" + firstLetter);
        for (int i = 1; i < x.length; i++)
            if (x[i] != x[i-1] && x[i] != '0')
                output.append(x[i]);
        output.append("0000");
        return output;
    }

    public boolean isSoundex(){
        String code1 = soundex(cadena1);
        String code2 = soundex(cadena2);
        return code1.equals(code2);
    }
}