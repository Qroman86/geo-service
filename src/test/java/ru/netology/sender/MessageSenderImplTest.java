package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;


import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MessageSenderImplTest {


    @Test
    void send_shouldReturnRussianTextForRussianIp() {
        // Arrange
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        when(geoService.byIp("172.123.12.19"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        // Act
        String result = messageSender.send(headers);

        // Assert
        assertEquals("Добро пожаловать", result);
    }

    @Test
    void send_shouldReturnEnglishTextForAmericanIp() {
        // Arrange
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        when(geoService.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, null, 0));
        when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        // Act
        String result = messageSender.send(headers);

        // Assert
        assertEquals("Welcome", result);
    }

}