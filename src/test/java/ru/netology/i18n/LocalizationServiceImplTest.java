package ru.netology.i18n;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ru.netology.entity.Country;

import java.util.stream.Stream;

public class LocalizationServiceImplTest {

    // Основной тест для русского языка
    @Test
    void locale_shouldReturnRussianTextForRussia() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", result);
    }

    // Основной тест для английского языка (США)
    @Test
    void locale_shouldReturnEnglishTextForUSA() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.USA);
        assertEquals("Welcome", result);
    }

}
