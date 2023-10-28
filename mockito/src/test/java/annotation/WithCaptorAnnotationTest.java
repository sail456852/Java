package annotation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WithCaptorAnnotationTest {
    @BeforeEach
    void init() {
        // to use @Mock this is a must
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    List mockedList;

    // used to capture argument passed to a method
    @Captor
    ArgumentCaptor argCaptor;

    @Test
    public void whenUseCaptorAnnotation_thenTheSam() {
        mockedList.add("one");
        Mockito.verify(mockedList).add(argCaptor.capture());

        assertEquals("one", argCaptor.getValue());
    }
}