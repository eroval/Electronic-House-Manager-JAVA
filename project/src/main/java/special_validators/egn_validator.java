package special_validators;

import java.util.HashMap;

public class egn_validator {
    public static final HashMap<String, String> calendar = new HashMap<>(){{
        put("01","31");
        put("02","28");
        put("03","31");
        put("04","30");
        put("05","31");
        put("06","30");
        put("07","31");
        put("08","31");
        put("09","30");
        put("10","31");
        put("11","30");
        put("12","31");
    }};

    private static boolean checkDate(String id){
        String month =id.substring(2,4);
        String day =id.substring(4,6);
        String match = egn_validator.calendar.get(month);
        if(match==null) return false;
        if(day.charAt(0)>match.charAt(0)) return false;
        if(day.charAt(0)==match.charAt(0)&&day.charAt(1)>match.charAt(1)) return false;
        return true;
    }

    public static boolean checkId(String id) {
        if (id.length() != 10) return false;
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) < '0' || id.charAt(i) > '9') return false;
        }
        return egn_validator.checkDate(id);
    }
}
