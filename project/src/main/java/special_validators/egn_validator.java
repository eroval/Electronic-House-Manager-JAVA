package special_validators;

public class egn_validator {
    public static boolean checkId(String id){
        if(id.length()!=10) return false;
        for(int i=0; i<id.length(); i++){
            if(id.charAt(i)<'0'||id.charAt(i)>'9') return false;
        }
        return true;
    }
}
