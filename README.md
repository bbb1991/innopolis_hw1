#Домашнее задание №1

##Вариант 2
  
Необходимо разработать программу, которая получает на вход список 
ресурсов, содержащих текст, и проверяет уникальность каждого слова. 
Каждый ресурс должен быть обработан в отдельном потоке, текст не 
должен содержать инностранных символов, только кириллица, знаки 
препинания и цифры. В случае нахождения дубликата, программа 
должна прекращать выполнение с соответсвующим сообщением. 
Все ошибки должны быть корректно обработаны, 
все API покрыто модульными тестами

##Deadline.
Срок сдачи: до 13 ноября 2016 года

##Сборка и запуск
Для сборки необходимо в консоли набрать:
```bash
mvn compile assembly:single
```
либо
```bash
gradle jar
```

В итоге получится файл **innopolis_hw1-1.0.jar**. А затем запустить со списком файлов. 
Пример:
```bash
java -jar innopolis_hw1-1.0.jar /tmp/file1.txt /tmp/file2.txt http://bbb1991.me/mock.txt file://test.txt
```


##Тесты
Для запуска тестов необходимо набрать в консоли
```bash
mvn test
```
либо
```bash
gradle test
```

#JConsole
Чтобы протестировать производительность, необходимо запустить с параметрами:
```bash
-Dcom.sun.management.jmxremote.port=9005
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl=false
```
а затем подцепиться с помощью jconsole.