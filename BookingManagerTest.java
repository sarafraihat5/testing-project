import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookingManagerTest {
    private IEventRepository repoMock;
    private IPaymentGateway paymentMock;
    private INotificationService notificationMock;
    private BookingManager bookingManager;

    @BeforeEach
    void setUp() {
        //Mocks 
        repoMock = mock(IEventRepository.class);
        paymentMock = mock(IPaymentGateway.class);
        notificationMock = mock(INotificationService.class);

        // Mocks in main class
        bookingManager = new BookingManager(repoMock, paymentMock, notificationMock);
    }

    @Test
    void test_US01_HappyPath() {
        // test details
    }
}