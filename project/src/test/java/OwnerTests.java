import entity.Owner;
import org.junit.jupiter.api.Test;

public class OwnerTests {
    @Test
    public void createOwner(){
        Owner o1 = new Owner(11,"9403030046","Larry", "Garry");
        Owner o2 = new Owner(12,"8514142256", "Barry","Zarrry");
        Owner o3 = new Owner(13,"0224102256", "Prince","Persia");
        Owner o4 = new Owner(14,"7514102256", "Angelina","Jolie");
        Owner o5 = new Owner(15,"8514102256", "Keira","Knightley");

    }
}
