# Tree

### Для запуска приложения

Для запуска приложения нужно открыть терминал, перейти в директорию проекта “TestProjectJob”. 

После того, как перешли в нужную директорию, можно приступать к запуску проекта. Для этого нужно сначала ввести команду: `mvn clean package`, которая сначала очистит проект, а затем сделает готовый к использованию набор файлов. 

Для запуска непосредственно самого приложения нужно также в терминале выполнить команду: `mvn exec:java -Dexec.mainClass=node.test.Main`. 

Должно появиться меню:

![Screenshot 2023-02-21 at 19 09 39](https://user-images.githubusercontent.com/99659178/220398548-03efe306-56be-4b8d-8ab1-33f797a65888.png)

На котором представлены команды и параметры их ввода для работы с деревом. 

С подключением к базе данных могут возникнуть проблемы, я не уверена, что разобралась, как локальный сервер сделать удаленным. 

Каждый раз при запуске программы таблица удаляется, если существовала. И создается новая, которая заполняется тестовыми данными. Что-то подобное можно увидеть при первом запуске и выполнении команды `list`:

![Screenshot 2023-02-21 at 19 10 09](https://user-images.githubusercontent.com/99659178/220398657-f5de7a8b-842d-4db0-9c17-97cfe4c4ec8b.png)
