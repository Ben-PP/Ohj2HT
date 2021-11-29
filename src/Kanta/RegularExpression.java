package Kanta;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Regular expression metodeja
 * @author Karel
 * @version 29.11.2021
 */
public class RegularExpression {
    
    
    /**
     * Regular expression helpottamaan elämää
     * @param regex kaava
     * @param tutkittava stringi jota tutkitaan
     * @param caseInSensitive True jos halutaan että isoja ja pieniä ei huomioida, false jos koolla on väliä
     * @return true jos stringi vastaa regular expressionia, muuten false
     * FIXME: Testit: Kanta.RegularExpression.regulaariExp()
     */
    public static boolean regulaariExp(String regex, String tutkittava, boolean caseInSensitive) {
        Pattern p;
        if (caseInSensitive) {
            p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } else {
            p = Pattern.compile(regex);
        }
        Matcher m = p.matcher(tutkittava);
        
        return m.find();
    }
}
