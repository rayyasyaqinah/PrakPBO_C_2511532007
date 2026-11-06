package shop.payment;

public interface PaymentMethod {
     String getName();  

    /** @return true if the payment succeeds */     
    boolean pay(long amount); 

}
