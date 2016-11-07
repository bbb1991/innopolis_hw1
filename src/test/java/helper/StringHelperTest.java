package helper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import readers.AbstractResourceReader;
import readers.FileBasedResourceReader;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bbb1991 on 11/2/16.
 * Тест для
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class StringHelperTest {

    private StringHelper stringHelper;

    /**
     * метод для подготовки данных, треюуемых к тесту
     */
    @Before
    public void setup() {
        stringHelper = new StringHelper();
    }

    /**
     * Тест по проверки корректной разбивке текста на слова
     */
    @Test
    public void testSplitText() {
        Assert.assertArrayEquals(new String[]{"Test", "string"}, stringHelper.split("Test               string"));
    }


    /**
     *  Тест для проверки, что текст с кириллицей отрабатывает
     */
    @Test
    public void testWithoutLatinLetter() {
        Assert.assertFalse(stringHelper.isTextContainForeignSymbol("Это текст без латинских букв"));
    }

    /**
     * Тест для проверки, что символы ин. языка не проходит проверку
     */
    @Test
    public void testWithLatinLetter() {
        Assert.assertTrue(stringHelper.isTextContainForeignSymbol("Этот текст содержит буквы 辞盤レケヱメ投楽6月テ食権ハ越聞せむどち権話びざト人74湖ミ"));
    }

    /**
     * Проверка что детектится дубликаты слов
      */
    @Test
    public void testArrayWithDuplicates() {
        Assert.assertTrue(stringHelper.isTextContainDuplicates(new String[] {"Массив", "который", "содержит", "дубликаты", "дубликаты"}, new HashSet<>()));
    }

    /**
     *Проверка что массив без дубликатов верно отрабатывает
     */
    @Test
    public void testArrayWithoutDuplicates() {
        Assert.assertFalse(stringHelper.isTextContainDuplicates(new String[] {"Массив", "который", "не", "содержит", "дубликаты", }, new HashSet<>()));
    }

    /**
     * Тест для проверки корректного срабатывания на символы пунктуаций
     */
    @Test
    public void testPunctuationSymbols() {
        Assert.assertFalse(stringHelper.isTextContainForeignSymbol("?!:;.,…@&()[]{}-+`'"));
    }

}
