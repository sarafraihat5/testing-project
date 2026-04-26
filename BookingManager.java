public class BookingManager {
    private final IEventRepository repository;
    private final IPaymentGateway paymentGateway;
    private final INotificationService notificationService;

 
    public BookingManager(IEventRepository repository, IPaymentGateway paymentGateway, INotificationService notificationService) {
        this.repository = repository;
        this.paymentGateway = paymentGateway;
        this.notificationService = notificationService;
    }

    public boolean reserveTicket(String eventId, double amount) {
      

        
        return false;
    }
}