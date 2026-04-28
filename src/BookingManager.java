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
        // US-02: Invalid input
    if (eventId == null || amount <= 0) {
        return false;
    }
        // US-03: Sold out
    if (repository.isSoldOut(eventId)) {
        return false;
    }
         // Payment
    boolean paymentSuccess = paymentGateway.processPayment(amount);

    if (!paymentSuccess) {
        return false;
    }
        // US-01: Success
    repository.saveBooking(eventId);
    notificationService.sendConfirmation(eventId);

    return true;
}
    }
