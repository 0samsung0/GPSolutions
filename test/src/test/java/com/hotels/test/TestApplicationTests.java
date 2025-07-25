package com.hotels.test;

import com.hotels.test.DTO.HotelCreateRequest;
import com.hotels.test.entities.Hotel;
import com.hotels.test.repositories.HotelRepo;
import com.hotels.test.services.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestApplicationTests {

    @Mock
    private HotelRepo hotelRepo;

    @InjectMocks
    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllHotelsReturnsList() {
        List<Hotel> hotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelRepo.findAll()).thenReturn(hotels);
        assertEquals(2, hotelService.getAllHotels().size());
    }

    @Test
    void testCreateHotelReturnsDto() {
        HotelCreateRequest req = mock(HotelCreateRequest.class);
        when(req.name()).thenReturn("TestHotel");
        when(req.description()).thenReturn("desc");
        when(req.brand()).thenReturn("brand");
        var address = mock(com.hotels.test.DTO.AddressDTO.class);
        when(req.address()).thenReturn(address);
        when(address.houseNumber()).thenReturn(1);
        when(address.street()).thenReturn("street");
        when(address.city()).thenReturn("city");
        when(address.country()).thenReturn("country");
        when(address.postCode()).thenReturn("000");
        var contacts = mock(com.hotels.test.DTO.ContactDTO.class);
        when(req.contacts()).thenReturn(contacts);
        when(contacts.phone()).thenReturn("123");
        when(contacts.email()).thenReturn("mail");
        var arrival = mock(com.hotels.test.DTO.ArrivalTimeDTO.class);
        when(req.arrivalTime()).thenReturn(arrival);
        when(arrival.checkIn()).thenReturn("12:00");
        when(arrival.checkOut()).thenReturn("14:00");
        var dto = hotelService.createHotel(req);
        assertEquals("TestHotel", dto.name());
    }

    @Test
    void testAddAmenitiesToHotel() {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setAmenities(new ArrayList<>());
        when(hotelRepo.findById(1)).thenReturn(Optional.of(hotel));
        List<String> amenities = Arrays.asList("WiFi", "Parking");
        hotelService.addAmenitiesToHotel(1, amenities);
        assertTrue(hotel.getAmenities().contains("WiFi"));
        assertTrue(hotel.getAmenities().contains("Parking"));
    }

    @Test
    void testSearchByArgumentReturnsEmpty() {
        when(hotelRepo.findAll()).thenReturn(Collections.emptyList());
        var result = hotelService.searchByArgument(null, null, null, null, Collections.emptyList());
        assertTrue(result.isEmpty());
    }

    @Test
    void testHistogramByParamBrand() {
        Hotel h1 = new Hotel(); h1.setBrand("A");
        Hotel h2 = new Hotel(); h2.setBrand("B");
        Hotel h3 = new Hotel(); h3.setBrand("A");
        when(hotelRepo.findAll()).thenReturn(Arrays.asList(h1, h2, h3));
        Map<String, Integer> hist = hotelService.histogramByParam("brand");
        assertEquals(2, hist.get("A"));
        assertEquals(1, hist.get("B"));
    }
}
