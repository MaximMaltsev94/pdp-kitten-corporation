package pdp.kitten.corporation.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pdp.kitten.corporation.service.DepartmentService;
import pdp.kitten.corporation.service.JobTitleService;
import pdp.kitten.corporation.service.KittenService;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class KittenControllerTest {

    @Mock
    private KittenService kittenService;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private JobTitleService jobTitleService;

    @InjectMocks
    private KittenController kittenController;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockAnnotationsWorking() {
        assertNotNull(kittenController, "kittenController is null");
        assertNotNull(kittenService, "kittenService is null");
        assertNotNull(departmentService, "departmentService is null");
        assertNotNull(jobTitleService, "jobTitleService is null");
    }

    @Test
    public void testDeleteKitten() {
        String view = kittenController.deleteKitten("779");
        assertEquals(view, "redirect:/kittens");
        verify(kittenService).delete("779");
        verifyNoMoreInteractions(kittenService);
        verifyZeroInteractions(departmentService, jobTitleService);
    }
}
