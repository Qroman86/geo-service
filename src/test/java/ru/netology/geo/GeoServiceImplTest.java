package ru.netology.geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ru.netology.entity.Location;
import ru.netology.entity.Country;

import java.util.stream.Stream;

public class GeoServiceImplTest {


    // Позитивные тесты для российских IP
    @Test
    void byIp_shouldReturnRussianLocationForRussianIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("172.123.45.67");

        assertNotNull(location, "Location не должен быть null");
        assertEquals(Country.RUSSIA, location.getCountry(),
                "Для российского IP должна возвращаться Россия");
        assertEquals("Moscow", location.getCity());
    }

    // Позитивные тесты для американских IP
    @Test
    void byIp_shouldReturnAmericanLocationForAmericanIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("96.44.183.149");

        assertNotNull(location);
        assertEquals(Country.USA, location.getCountry());
        assertEquals("New York", location.getCity());
    }

    // Граничный случай - null IP
    @Test
    void byIp_shouldThrowExceptionForNullIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        assertThrows(NullPointerException.class, () -> geoService.byIp(null));
    }
}
