package ru.rapidcoder.bot.component;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class CancelCheckBoxButton extends InlineKeyboardButton implements Component {

    private final static String BUTTON_TEXT = "Cancel";

    private final List<ChekBoxButton> checkBoxButtons;

    public CancelCheckBoxButton(String callbackData, List<ChekBoxButton> checkBoxButtons) {
        setText(BUTTON_TEXT);
        setCallbackData(callbackData);
        this.checkBoxButtons = checkBoxButtons;
    }
}
