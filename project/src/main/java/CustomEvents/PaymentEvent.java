package CustomEvents;

import dao.*;
import entity.Apartment;
import entity.Person;
import entity.Taxes;
import entity.TaxesHistory;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PaymentEvent implements Runnable{

    private void checkPayments() {
        try {
            Thread.sleep(1000 * 60 * 60);
            List<Taxes> taxes = TaxesDAO.readTaxess();
            for(Taxes tax : taxes){
                if(tax.getTaxDay()==LocalDate.now().getDayOfMonth()){
                    List<Apartment> apartments = ApartmentDAO.getAllApartments(tax.getBuildingId());
                    long uid = LocalDate.now().getYear()*100+LocalDate.now().getMonthValue();
                    for(Apartment app : apartments){
                        if(TaxesHistoryDAO.getTaxesHistory(uid,app.getApartmentId(), app.getBuildingId())==null){
                            double amount = BuildingDAO.calculatePrice(app, tax);
                            TaxesHistory th = new TaxesHistory(uid, app.getApartmentId(), app.getBuildingId(),amount,false);
                            TaxesHistoryDAO.saveTaxesHistory(th);
                        }
                    }
                }
            }
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while(true){
            checkPayments();
        }
    }
}
