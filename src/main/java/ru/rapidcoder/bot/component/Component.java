package ru.rapidcoder.bot.component;

public interface Component {

    /**
     * Получение идентификатора события компонента
     * @return идентификатор события компонента
     */
    String getCallbackData();

    /**
     * Запуск обработки события
     */
    void execute();

    /**
     * Сброс компонента в исходное состояние
     */
    void refresh();

}
