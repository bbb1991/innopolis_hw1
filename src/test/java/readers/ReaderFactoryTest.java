package readers;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Created by bbb1991 on 11/11/16.
 *
 * Класс тест для проверки FactoryReader. То есть корректно ли рапознаются протокола
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class ReaderFactoryTest {

    /**
     * Протокол типа file://
     */
    @Test
    public void test1() {
        AbstractResourceReader reader = ReaderFactory.getResourceReader("file://test.txt");
        assertThat(reader, instanceOf(URLBasedResourceReader.class));
    }

    /**
     * Потокол типа http://
     */
    @Test
    public void test2() {
        AbstractResourceReader reader = ReaderFactory.getResourceReader("http://example.com");
        assertThat(reader, instanceOf(URLBasedResourceReader.class));
    }

    /**
     * Потокол типа https://
     */
    @Test
    public void test3() {
        AbstractResourceReader reader = ReaderFactory.getResourceReader("https://example.com");
        assertThat(reader, instanceOf(URLBasedResourceReader.class));
    }

    /**
     * Потокол типа ftp://
     */
    @Test
    public void test4() {
        AbstractResourceReader reader = ReaderFactory.getResourceReader("ftp://example.com:22/test.txt");
        assertThat(reader, instanceOf(URLBasedResourceReader.class));
    }

    /**
     * Потокол типа sftp://
     */
    @Test
    public void test5() {
        AbstractResourceReader reader = ReaderFactory.getResourceReader("sftp://example.com");
        assertThat(reader, instanceOf(URLBasedResourceReader.class));
    }

    /**
     * Без протокола
     */
    // TODO: 11/12/16 Почему то ругается на метод из пакета hamcrest
    // java.lang.NoSuchMethodError: org.hamcrest.Matcher.describeMismatch(Ljava/lang/Object;Lorg/hamcrest/Description;)V

    @Ignore
    @Test
    public void test6() {
        AbstractResourceReader reader = ReaderFactory.getResourceReader("test.txt");
        assertThat(reader, instanceOf(FileBasedResourceReader.class));
    }
}