import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class BookingManagerTest {

    private IEventRepository repository;
    private IPaymentGateway paymentGateway;
    private INotificationService notificationService;
    private BookingManager bookingManager;

    @BeforeEach
     public void setUp() {
        repository = mock(IEventRepository.class);
        paymentGateway = mock(IPaymentGateway.class);
        notificationService = mock(INotificationService.class);

        bookingManager = new BookingManager(repository, paymentGateway, notificationService);
    }

    @Test
     public void US01_HappyPath() {

        String eventId = "E1";
        double amount = 100.0;

        when(repository.isSoldOut(eventId)).thenReturn(false);
        when(paymentGateway.processPayment(amount)).thenReturn(true);

        bookingManager.reserveTicket(eventId, amount);

        verify(repository, times(1)).saveBooking(eventId);
        verify(notificationService, times(1)).sendConfirmation(eventId);
    }

    @Test
    public void US02_InvalidInput() {

        String eventId = null;
        double amount = -10;

        bookingManager.reserveTicket(eventId, amount);

        verify(paymentGateway, never()).processPayment(anyDouble());
        verify(repository, never()).saveBooking(any());
        verify(notificationService, never()).sendConfirmation(any());
    }

    @Test
    public void US03_SoldOut() {

        String eventId = "E1";
        double amount = 100.0;

        when(repository.isSoldOut(eventId)).thenReturn(true);

        bookingManager.reserveTicket(eventId, amount);

        verify(repository, times(1)).isSoldOut(eventId);

        verify(paymentGateway, never()).processPayment(anyDouble());
        verify(repository, never()).saveBooking(any());
        verify(notificationService, never()).sendConfirmation(any());
    }
}