Приложения для заметок для Лаборатории ШИФТ ЦФТ.

Постарался следовать принципам Clean Architecture, SOLID. Использовал MVVM.
UI написан на классических View - с Compose пока только начал рабираться.
Использовал Room для управления локальной базой данных, где хранятся все созданные заметки. Использовал Coroutines для асинхронного доступа к данным.
Dagger - для инъекции зависимостей. Jetpack Navigation для простой и удобной навигации между фрагментами. SafeArgs для передачи данных между фрагментами.
RecyclerView для отображения списков. SharedPreferences для быстрого сохранения черновика новой заметки.

Приложение написано на 4 фрагментах: экран активных заметок, экран архива, экран корзины и экран просмотра/редактирования/добавления новой заметки.
Использовал AlertDialog, SnackBar для улучшения пользовательского опыта.
  На экране активных заметок можно:
    1. Добавить новую заметку (черновая запись заметки сохарняется при выходе)
    2. При нажатии - просмотреть заявку
    3. При удержании - открыть диалоговое окно (перемещение заявки по категориям, просмотр, редактирование)
    4. При свайпе влево - отправить в архив
    5. При свайпе вправо - отправить в корзину

  На экране архивных заметок можно:
    1. При нажатии - просмотреть заявку
    2. При удержании - открыть диалоговое окно (перемещение заявки по категориям, просмотр, редактирование)
    3. При свайпе влево - отправить в актуальные
    4. При свайпе вправо - отправить в корзину

  На экране корзины можно:
    1. При нажатии на кнопку снизу - очистить всю корзину
    2. При нажатии - просмотреть заявку
    3. При удержании - открыть диалоговое окно (перемещение заявки по категориям, просмотр, редактирование, удаление)
    4. При свайпе влево - отправить в архив
    5. При свайпе вправо - удалить

  На экране заметки можно:
    1. Добавить новую заметку (если запущено с главного экрана)
    2. Просмотреть заметку (можно включить режим редактрования кнопкой снизу)
    3. Редактировать заметку (можно сохранить изменения кнопкой снизу, при попытке выхода без изменений пользователя встретит AlertDialog с предупреждением)

Добавил бы еще больше разных фич, но не успел.

Ссылка на APK: ... ~~(без троянов, чес слово)~~

Инструкция сборки простая: клонируем проект в свою Android Studio, ~~молимся Ктулху~~ и собираем проект.
